/**
 * 控制器：角色删除
 */
'use strict';

function RoleDeleteCtrl($controller) {
    'ngInject';

    /*let vm = this,
        ctrlOpts = {
            modelName: 'role'
        };
    angular.extend(this, $controller('BaseCrudCtrl', { vm: vm, ctrlOpts: ctrlOpts }));

    vm.getDetail();*/
   
   let vm = this;

	// 取得详情后处理
	function afterGetDetail(data) {
		vm.permissions = data.permissions;
		return data;
	}

	let ctrlOpts = {
		modelName: 'role',
		afterGetDetail
	
	};
	angular.extend(this, $controller('BaseCrudCtrl', {
		vm: vm,
		ctrlOpts
	}));
	
	vm.getDetail();
}

module.exports = {
    name: 'RoleDeleteCtrl',
    fn: RoleDeleteCtrl
};
