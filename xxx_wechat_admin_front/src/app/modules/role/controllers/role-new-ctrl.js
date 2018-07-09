/**
 * 控制器：角色新增
 */
'use strict';

function RoleNewCtrl($controller, RoleSrv) {
    'ngInject';

    let vm = this;

    // 更新前处理
    function beforeSave() {
        // 编辑权限列表成为字符串数组形式
        vm.model.permissions = RoleSrv.convPermissionForSave(vm.permissionList);
    }

    let ctrlOpts = {
        modelName: 'role',
        beforeSave
    };

    angular.extend(this, $controller('BaseCrudCtrl', { vm: vm, ctrlOpts }));

    // 编辑权限列表成为画面表示形式
    vm.permissionList = RoleSrv.convPermissionForView();
    vm.show = function (text, key) {
      if (text.indexOf('.') >= 0)
        text = text.split('.')[1];
		  if(text === key)
			  return true;
      return false;
		};
    
   vm.showAll= function(){
   	var checklist = document.getElementsByName('selectCheckBox');
   		if(document.getElementById('selectAll').checked){
   			for(var i=0;i<vm.permissionList.length;i++){
          vm.permissionList[i].selected=true;
   			} 
   		}else{
  			for(var j=0;j<vm.permissionList.length;j++){
          vm.permissionList[j].selected=false;
  			}
   		}	  
	};
}

module.exports = {
    name: 'RoleNewCtrl',
    fn: RoleNewCtrl
};
