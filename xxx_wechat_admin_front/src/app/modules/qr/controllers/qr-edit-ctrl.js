/**
 * 控制器：管理员编辑
 */
'use strict';

function QrEditCtrl($controller, $state, ApiSrv, growl) {
    'ngInject';

    let vm = this;

    let ctrlOpts = {
            modelName: 'qr'
        };
    angular.extend(this, $controller('BaseCrudCtrl', { vm: vm, ctrlOpts}));

    vm.getDetail();

    // 更新
    vm.mySave = function() {
        if(vm.model.tempFlag==='0'){
			if(!$('#invalidDate').val()){
				return growl.error('请输入失效日期');
			}
		}
        vm.model.invalidDate = $('#invalidDate').val();
        ApiSrv.exec('qr/update', vm.model)
	        .then(function() {
	            $state.go('main.qr.list');
	        });
	};
}

module.exports = {
    name: 'QrEditCtrl',
    fn: QrEditCtrl
};
