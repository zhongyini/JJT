/**
 * 控制器：角色选择popup
 */
'use strict';

function MaterialSelectPopupCtrl($controller, $scope, $uibModalInstance) {
	'ngInject';

	let vm = this;
	let ctrlOpts = {
		modelName: 'material'
	};
	angular.extend(this, $controller('BaseListCtrl', {
		vm: vm,
		ctrlOpts: ctrlOpts
	}));
	//vm.tableParams.reload();

	$scope.choose = function() {
		$uibModalInstance.close(vm.selected);
	};
	vm.mySearch = function() {
		vm.searchCondition.firTitle = $('#firTitle').val();
		vm.search();
	};
	vm.myRest = function() {
		vm.reset();
	};
	vm.dismiss =function(){
		vm.reset();
		$uibModalInstance.dismiss('cancel');
	}
	
	vm.tableParams;
}

module.exports = {
	name: 'MaterialSelectPopupCtrl',
	fn: MaterialSelectPopupCtrl
};