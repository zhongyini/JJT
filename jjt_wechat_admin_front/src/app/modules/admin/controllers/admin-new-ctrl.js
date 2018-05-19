/**
 * 控制器：管理员新增
 */
'use strict';

function AdminNewCtrl($controller, $scope, UploadImageSrv, MessageSrv, $uibModal) {
    'ngInject';

    let vm = this;
    let uploader;
    
    function validate() {
        let photo = uploader.getFile() || null;
        if (!photo) {
        	MessageSrv.error('请上传头像');
        	return false;
        }
    }
    
    function beforeSave() {
        let photo = uploader.getFile() || null;
        if (photo) {
            vm.model.portrait = photo;
	        vm.model.avatarSUrl = photo;
	    }
    }
    
    let ctrlOpts = {
            modelName: 'adminManager',
            validate: validate,
            beforeSave: beforeSave
        };
    angular.extend(this, $controller('BaseCrudCtrl', { vm: vm, ctrlOpts }));
    
    // 角色选择popup
    vm.showPopUp = function() {
		$uibModal.open({
			templateUrl: 'role/views/role-select-popup.html',
			controller: 'RoleSelectPopupCtrl as vm',
			size: 'lg',
			resolve: {}
		}).result.then(function (returnParam){
				vm.model.roleId = returnParam.roleId;
				vm.model.roleName = returnParam.roleName;
		});
	};
		
		
		uploader = UploadImageSrv.createImageUploader();
    			uploader.setOption('multipart_params', {
				'id':''
			});
   		vm.uploader = uploader;
		
	
    
}

module.exports = {
    name: 'AdminNewCtrl',
    fn: AdminNewCtrl
};
