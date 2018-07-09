/**
 * 控制器：管理员列表
 */
'use strict';

function AdminListCtrl($controller, $q, ApiSrv, SessionSrv,growl,$state) {
	'ngInject';

	// 扩展自list控制器基类
	var vm = this,
		ctrlOpts = {
			modelName: 'adminManager'
		};

	angular.extend(this, $controller('BaseListCtrl', {
		vm: vm,
		ctrlOpts: ctrlOpts
	}));
	vm.searchCondition.noData = false;
	vm.searchCondition.counts = 0;
	vm.currentUserId = SessionSrv.getCurrentUserId();
	vm.currentUserRoleId = SessionSrv.getCurrentUserRoleId();
	// 取得CSV数据
	vm.getCsvData = function() {
		return ApiSrv.exec('/adminManager/download', vm.searchCondition)
			.then(function(data) {
				return data;
			});
	};
	
	vm.mySearch =function(){
		if(vm.searchCondition.name){
			if(!vm.searchCondition.name.match(/^[a-z0-9A-Z\u4e00-\u9fa5]+$/)){
				return growl.error('管理员名格式不正确，请输入中文或英数字！');
			}
		}
		
		if(vm.searchCondition.mail){
			if(!vm.searchCondition.mail.match(/^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/)){
				return growl.error('邮箱格式不正确，请输入中文或英数字！');
			}
		}
		
		
	}
	vm.resetPwd = function(id) {
		ApiSrv.exec('/adminManager/restPassword?id=' + id + '&adminId=' + vm.currentUserId, null, {
				method: 'GET'
			})
			.then(function() {
				$state.go('main.adminManager.list');
			});
	};
}

module.exports = {
	name: 'AdminListCtrl',
	fn: AdminListCtrl
};