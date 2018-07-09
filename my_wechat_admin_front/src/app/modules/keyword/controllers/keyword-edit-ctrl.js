/**
 * 控制器：管理员编辑
 */
'use strict';

function KeywordEditCtrl($controller, $scope, UploadImageSrv, MessageSrv, $state, AppConfigs,ApiSrv, $uibModal) {
    'ngInject';

    let vm = this;
	let uploader;
	
	function validate() {
		if(vm.model.replyType==4){
        	let photo = uploader.getFile() || null;
        	if (!photo) {
        		MessageSrv.error('请上传图片');
        		return false;
        	}
        } 
    }
    
  	 function beforeSave() {
    	if(vm.model.replyType==4){
        	let photo = uploader.getFile() || null;
        	if (photo) {
        		let fileName= uploader.getFileNameExt();
            	vm.model.portrait = photo;
	        	vm.model.avatarSUrl = photo;
	        	vm.model.image = photo;
	        	vm.model.title=fileName;
	    	}
        }
    }
    
    let ctrlOpts = {
		modelName: 'keyword',
		validate: validate,
        beforeSave: beforeSave
	};
	
    angular.extend(this, $controller('BaseCrudCtrl', { 
    	vm: vm, 
    	ctrlOpts
    }));
    
	vm.model.type = '1';
    vm.getDetail();
	vm.model.updateMaterial = false;
	   
    // 图文选择popup
    vm.showPopUp = function() {
		$uibModal.open({
			templateUrl: 'material/views/material-select-popup.html',
			controller: 'MaterialSelectPopupCtrl as vm',
			size: 'lg',
			resolve: {}
		}).result.then(function (returnParam){
				vm.model.replyContent = returnParam.media_id;
				vm.model.title = returnParam.firTitle;
				vm.model.image = returnParam.firImage;
				vm.model.updateMaterial = true;
				$scope.sonItemList =returnParam.sonItemList;
				$scope.flag=2;
		});
	};
	
	// 上传组件
    uploader = UploadImageSrv.createImageUploader();
	uploader.setOption("multipart_params", {
			"id":''
		});
    vm.uploader = uploader;
    
}

module.exports = {
    name: 'KeywordEditCtrl',
    fn: KeywordEditCtrl
};
