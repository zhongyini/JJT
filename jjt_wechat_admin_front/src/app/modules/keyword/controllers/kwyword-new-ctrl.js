/**
 * 控制器：关键字新增
 */
'use strict';
//增加上传图片的参数
function KeywordNewCtrl($controller, $scope, UploadImageSrv, MessageSrv, $uibModal) {
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
	        	vm.model.title = fileName;
	    	}
      	}
    }
	
	
	
	let ctrlOpts = {
		modelName: 'keyword',
		validate:validate,
		beforeSave:beforeSave
		
	};

	angular.extend(this, $controller('BaseCrudCtrl', {
		vm: vm,
		ctrlOpts
	}));
	
	vm.model.replyType = '1';
	vm.model.type = '1';
	// 图文选择popup
	vm.showPopUp = function() {
		$uibModal.open({
			templateUrl: 'material/views/material-select-popup.html',
			controller: 'MaterialSelectPopupCtrl as vm',
			size: 'lg',
			resolve: {}
		}).result.then(function(returnParam) {
			vm.model.replyContent = returnParam.media_id;
			vm.model.title = returnParam.firTitle;
			vm.model.image = returnParam.firImage;
			$scope.sonItemList =returnParam.sonItemList;
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
	name: 'KeywordNewCtrl',
	fn: KeywordNewCtrl
};