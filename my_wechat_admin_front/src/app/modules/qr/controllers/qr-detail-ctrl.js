/**
 * 控制器：管理员详情
 */
'use strict';

function QrDetailCtrl($controller) {
    'ngInject';

    var vm = this,
        ctrlOpts = {
            modelName: 'qr'
        };
    angular.extend(this, $controller('BaseCrudCtrl', { vm: vm, ctrlOpts: ctrlOpts }));

   	vm.getDetail();
}

module.exports = {
    name: 'QrDetailCtrl',
    fn: QrDetailCtrl
};
