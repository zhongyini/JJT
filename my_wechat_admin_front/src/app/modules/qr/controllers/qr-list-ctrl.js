/**
 * 控制器：管理员列表
 */
'use strict';

function QrListCtrl($controller) {
    'ngInject';

    // 扩展自list控制器基类
    var vm = this,
        ctrlOpts = {
            modelName: 'qr'
        };
    angular.extend(this, $controller('BaseListCtrl', { vm: vm, ctrlOpts: ctrlOpts }));  
	vm.searchCondition.noData = false;
	vm.searchCondition.counts = 0;
}

module.exports = {
    name: 'QrListCtrl',
    fn: QrListCtrl
};
