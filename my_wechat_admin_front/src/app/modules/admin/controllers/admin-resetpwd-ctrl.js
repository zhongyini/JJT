/**
 * 控制器：管理员编辑
 */
'use strict';

function AdminRestPwdCtrl($controller, $state, $uibModal, ApiSrv) {
    'ngInject';

    let vm = this;
    angular.extend(this, $controller('BaseCrudCtrl', { vm: vm, ctrlOpts}));
    
    
    // 更新
    vm.mySave = function() {
        ApiSrv.exec('adminManager/restPwd', vm.model)
	        .then(function() {
	            $state.go('main.adminManager.list');
	        });
	};
}

module.exports = {
    name: 'AdminRestPwdCtrl',
    fn: AdminRestPwdCtrl
};
