/**
 * 控制器：管理员删除
 */
'use strict';

function AdminDeleteCtrl($controller) {
    'ngInject';

    var vm = this,
        ctrlOpts = {
            modelName: 'adminManager'
        };
    angular.extend(this, $controller('BaseCrudCtrl', { vm: vm, ctrlOpts: ctrlOpts }));

    vm.getDetail();
}

module.exports = {
    name: 'AdminDeleteCtrl',
    fn: AdminDeleteCtrl
};
