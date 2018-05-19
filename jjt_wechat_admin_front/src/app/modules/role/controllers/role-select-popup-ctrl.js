/**
 * 控制器：角色选择popup
 */
'use strict';

function RoleSelectPopupCtrl($controller, $scope, $uibModalInstance) {
	'ngInject';

	let vm = this;

	let ctrlOpts = {
		modelName: 'role'
	};
	angular.extend(this, $controller('BaseListCtrl', {
		vm: vm,
		ctrlOpts: ctrlOpts
	}));
	vm.tableParams.reload();

	$scope.choose = function() {
		$uibModalInstance.close(vm.selected);
	};
	
}

module.exports = {
	name: 'RoleSelectPopupCtrl',
	fn: RoleSelectPopupCtrl
};