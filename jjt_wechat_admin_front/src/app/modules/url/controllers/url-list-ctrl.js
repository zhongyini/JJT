/**
 * 控制器：URL列表
 */
'use strict';

function UrlListCtrl($controller) {
    'ngInject';

    // 扩展自list控制器基类
    let vm = this,
        ctrlOpts = {
            modelName: 'url'
        };
    angular.extend(this, $controller('BaseListCtrl', { vm: vm, ctrlOpts: ctrlOpts }));  
	vm.searchCondition.noData = false;
	vm.searchCondition.counts = 0;
}

module.exports = {
    name: 'UrlListCtrl',
    fn: UrlListCtrl
};
