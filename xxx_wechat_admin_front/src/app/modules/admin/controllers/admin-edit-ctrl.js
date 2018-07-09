/**
 * 控制器：管理员编辑
 */
'use strict';

function AdminEditCtrl($controller, $stateParams, $state, UploadImageSrv, $uibModal, ApiSrv, SessionSrv,MessageSrv) {
	'ngInject';

	let vm = this;
	let uploader;

	function beforeSave() {
		let photo = uploader.getFile() || vm.model.portrait;
		if(photo) {
			vm.model.portrait = photo+'?t=' + Math.random();
			vm.model.avatarSUrl = photo;
		}

	}

	let ctrlOpts = {
		modelName: 'adminManager',
		beforeSave: beforeSave
	};
	angular.extend(this, $controller('BaseCrudCtrl', {
		vm: vm,
		ctrlOpts
	}));

	vm.getDetail();
	vm.model.portrait = vm.model.portrait+'?t=' + Math.random();
	// 角色选择popup
	vm.showPopUp = function() {
		$uibModal.open({
			templateUrl: 'role/views/role-select-popup.html',
			controller: 'RoleSelectPopupCtrl as vm',
			size: 'lg',
			resolve: {}
		}).result.then(function(returnParam) {
			vm.model.roleId = returnParam.roleId;
			vm.model.roleName = returnParam.roleName;
		});
	};

	// 上传组件
	uploader = UploadImageSrv.createImageUploader();
	uploader.setOption('multipart_params', {
		'id':$stateParams.id
	});
	vm.uploader = uploader;

	// 更新
	vm.mySave = function() {
		
		var value =$('#mail').val();
		if(!value.match(/^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/)){
			return MessageSrv.error('邮箱箱格式不正确，请重新输入！');
		}
		
		beforeSave();
		ApiSrv.exec('adminManager/update', vm.model)
			.then(function() {
				if($stateParams.id == SessionSrv.getCurrentUserId()){
					var t = Math.random();
					$('#adminPortrait1').attr('src',vm.model.portrait+'?t=' + t); 
					$('#adminPortrait2').attr('src',vm.model.portrait+'?t=' + t); 
				}
				$state.go('main.adminManager.list');
				
			});
	};
}

module.exports = {
	name: 'AdminEditCtrl',
	fn: AdminEditCtrl
};