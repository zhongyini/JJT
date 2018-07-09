'use strict';

/**
 * 控制器基类：列表控制器
 *
 * @param {[type]} $location     [description]
 * @param {[type]} NgTableParams [description]
 * @param {[type]} ApiSrv        [description]
 * @param {[type]} TableSrv      [description]
 * @param {Object} vm            控制器的value model
 * @param {Object} ctrlOpts	必须	{
 *                               		modelName,	// 必须 String, 模型的名称
 *                               	 	getDataFun	// 可选 Function, 取得数据的自定义处理
 *                                	}
 */
function BaseListCtrl($location, NgTableParams, ApiSrv, TableSrv, vm, ctrlOpts) {
    'ngInject';

    let tableSrv;

    // 检索
    vm.search = function() {
        vm.tableParams.page(1);
        vm.tableParams.reload();
    };

    // 重置
    vm.reset = function() {
        vm.searchCondition = angular.copy({});
        tableSrv.reset(vm.searchCondition);
        vm.search();
    };

    // 取得当前索引
    vm.getIndex = function(index) {
    	if(vm.searchCondition.counts<vm.tableParams.count()){
			return index + 1;
		}
        
        return (vm.tableParams.page() - 1) * vm.tableParams.count() + index + 1;
    };
    
	vm.count = function() {
		if(vm.tableParams.data.length>0){
			return false;
		}
        return true;
    };
	
    // 初始化
    (function init() {
        if (!vm.searchCondition) {
            vm.searchCondition = {};
        }
        let getDataUrl;
        if (ctrlOpts.getDataUrl) {
            getDataUrl = ctrlOpts.modelName + ctrlOpts.getDataUrl;
        } else {
            getDataUrl = ctrlOpts.modelName + '/list';
        }
        tableSrv = new TableSrv({
            searchCondition: vm.searchCondition,
            getDataUrl: getDataUrl
        });
        vm.tableParams = tableSrv.create();
    })();
}

module.exports = {
    name: 'BaseListCtrl',
    fn: BaseListCtrl
};
