/**
 * 控制器：管理员删除
 */
'use strict';

function KeywordDeleteCtrl($controller) {
    'ngInject';

    var vm = this,
        ctrlOpts = {
            modelName: 'keyword'
        };
    angular.extend(this, $controller('BaseCrudCtrl', { vm: vm, ctrlOpts: ctrlOpts }));

    vm.getDetail();
}

module.exports = {
    name: 'KeywordDeleteCtrl',
    fn: KeywordDeleteCtrl
};
