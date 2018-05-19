/**
 * 控制器：角色详情
 */
'use strict';

function RoleDetailCtrl($controller,RoleSrv) {
    'ngInject';
 // 取得详情后处理
	function afterGetDetail(data) {
		vm.items = data.permissions;
		vm.permissionList = RoleSrv.convPermissionForView(data.permissions);
		
		return data;
	}

    let vm = this,
        ctrlOpts = {
            modelName: 'role',
            afterGetDetail
        };
   
    angular.extend(this, $controller('BaseCrudCtrl', { vm: vm, ctrlOpts: ctrlOpts }));

   	vm.getDetail();
		
   	vm.show = function (text, key) {
		if (text.indexOf('.') >= 0)
        text = text.split('.')[1];
		  if(text === key)
			  return true;
		return false;
	};
}

module.exports = {
    name: 'RoleDetailCtrl',
    fn: RoleDetailCtrl
};
