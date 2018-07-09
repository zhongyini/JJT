/**
 * 控制器：管理员列表
 */
'use strict';

function KeywordListCtrl($controller, $q, ApiSrv) {
    'ngInject';

    // 扩展自list控制器基类
    var vm = this,
        ctrlOpts = {
            modelName: 'keyword'
        };
    angular.extend(this, $controller('BaseListCtrl', { vm: vm, ctrlOpts: ctrlOpts }));  
	vm.searchCondition.noData = false;
	vm.searchCondition.counts = 0;
    // 取得CSV数据
    vm.getCsvData = function() {
        return ApiSrv.exec('/Keyword/download', vm.searchCondition);
	};
}

module.exports = {
    name: 'KeywordListCtrl',
    fn: KeywordListCtrl
};
