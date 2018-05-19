/**
 * 控制器：管理员详情
 */
'use strict';

function KeywordDetailCtrl($controller) {
    'ngInject';

    var vm = this,
        ctrlOpts = {
            modelName: 'keyword'
        };
    angular.extend(this, $controller('BaseCrudCtrl', { vm: vm, ctrlOpts: ctrlOpts }));

   	vm.getDetail();
}

module.exports = {
    name: 'KeywordDetailCtrl',
    fn: KeywordDetailCtrl
};
