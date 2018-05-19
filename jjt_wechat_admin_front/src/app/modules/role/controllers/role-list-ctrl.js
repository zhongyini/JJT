/**
 * 控制器：角色列表
 */
'use strict';

function RoleListCtrl($controller) {
    'ngInject';

    // 扩展自list控制器基类
    let vm = this,
        ctrlOpts = {
            modelName: 'role'
        };
    angular.extend(this, $controller('BaseListCtrl', { vm: vm, ctrlOpts: ctrlOpts }));  
	vm.searchCondition.noData = false;
	vm.searchCondition.counts = 0;
	
	/*vm.myDelete = function(roleId){
		ApiSrv.exec('role/delete', roleId)
	        .then(function() {
	            $state.go('main.role.list');
	        });
	}*/
}

module.exports = {
    name: 'RoleListCtrl',
    fn: RoleListCtrl
};
