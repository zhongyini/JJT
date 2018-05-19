/**
 * 控制器：个人资料编辑
 */
'use strict';

function MyinfoEdit($controller, UploadImageSrv, $stateParams, ApiSrv, $state, SessionSrv) {
    'ngInject';

    let vm = this;
    let uploader;
    
    function beforeSave() {
        let photo = uploader.getFile() || vm.model.tPhoto;
        if(photo){
        	vm.model.portrait = photo+'?t=' + Math.random();
        	vm.model.avatarSUrl = photo;
        }
    }

    let ctrlOpts = {
            modelName: 'adminManager',
            beforeSave: beforeSave
        };
    angular.extend(this, $controller('BaseCrudCtrl', { vm: vm, ctrlOpts}));

    vm.getDetail();
	vm.model.portrait = vm.model.portrait+'?t=' + Math.random();
    // 上传组件
    uploader = UploadImageSrv.createImageUploader();
    uploader.setOption('multipart_params', {
		'id':$stateParams.id
	});
    vm.uploader = uploader;    
    
	// 更新
	vm.mySave = function() {
		beforeSave();
		ApiSrv.exec('adminManager/user/update', vm.model)
			.then(function() {
				if($stateParams.id === SessionSrv.getCurrentUserId()){
					var t = Math.random();
					$('#adminPortrait1').attr('src',vm.model.portrait+'?t=' + t); 
					$('#adminPortrait2').attr('src',vm.model.portrait+'?t=' + t); 
				}
				$state.go('main.welcome');
				
			});
	};
}

module.exports = {
    name: 'MyinfoEdit',
    fn: MyinfoEdit
};
