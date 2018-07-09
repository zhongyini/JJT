/**
 * 控制器：管理员详情
 */
'use strict';

function AdminDetailCtrl($controller) {
    'ngInject';

    var vm = this,
        ctrlOpts = {
            modelName: 'adminManager'
        };
    angular.extend(this, $controller('BaseCrudCtrl', { vm: vm, ctrlOpts: ctrlOpts }));

   	vm.getDetail();
}

module.exports = {
    name: 'AdminDetailCtrl',
    fn: AdminDetailCtrl
};
