/**
 * 控制器：商品列表
 */
'use strict';

function UserListCtrl($controller, ApiSrv,growl,$uibModal,$scope) {
	'ngInject';
	$('.date').datetimepicker({
		format: 'YYYY-MM-DD',
		locale: moment.locale('zh-cn')
	});
	
	// 扩展自list控制器基类
	let vm = this,
		ctrlOpts = {
			modelName: 'user'
		};
	angular.extend(this, $controller('BaseListCtrl', { vm: vm, ctrlOpts: ctrlOpts }));
	vm.searchCondition.noData = false;
	vm.searchCondition.counts = 0;
	
	//获取csv数据
	/*vm.getCsvData = function() {
		return ApiSrv.exec('/user/download', vm.searchCondition)
			.then(function(data) {
				return data;
			});
	};
	*/
	
	
	
	vm.csvcreate= function() {
		ApiSrv.exec('/user/download', vm.searchCondition,{method:'POST'}).then(function(data) {
			});
		/*$("#downloadcsv").attr("disabled","true");
		setTimeout(function(){  //使用  setTimeout（）方法设定定时2000毫秒
			window.location.reload();//页面刷新
		},1000*60*5);*/
		
	};
	
	ApiSrv.exec('/user/count', null)
			.then(function(data) {
				console.log('总数量'+data.dataCount);
				$scope.num =data.dataCount;
				
			});
	//获取qr数据
	ApiSrv.exec('/qr/all', vm.searchCondition).then(function(data) {
					vm.qrs = data.list;
			});
	
	vm.search1 = function() {
		
		
		if(vm.searchCondition.webId){
			if(!vm.searchCondition.webId.match(/^[a-z0-9A-Z\u4e00-\u9fa5]+$/)){
				return growl.error('会员webId格式不正确，请输入中文或英数字！');
			}
		}
		
		if(vm.searchCondition.ucode){
			if(!vm.searchCondition.ucode.match(/^[a-z0-9A-Z\u4e00-\u9fa5]+$/)){
				return growl.error('会员ucode格式不正确，请输入中文或英数字！');
			}
		}
		
		if(vm.searchCondition.nickname){
			if(!vm.searchCondition.nickname.match(/^[a-z0-9A-Z\u4e00-\u9fa5]+$/)){
				return growl.error('昵称格式不正确，请输入中文或英数字！');
			}
		}
		
		
		vm.searchCondition.startTime = $('#startTime').val();
		vm.searchCondition.endTime = $('#endTime').val();
		if(vm.searchCondition.startTime && vm.searchCondition.endTime && (vm.searchCondition.startTime >vm.searchCondition.endTime)) {
			return growl.error('开始时间不能大于等于结束时间');
		}
		vm.search();
	};
	vm.reset1 = function() {
		$('.date input').val('');
		vm.reset();
	};
}

module.exports = {
	name: 'UserListCtrl',
	fn: UserListCtrl
};