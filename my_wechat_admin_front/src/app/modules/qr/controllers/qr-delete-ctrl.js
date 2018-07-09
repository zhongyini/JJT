/**
 * 控制器：管理员删除
 */
'use strict';

function QrDeleteCtrl($controller) {
    'ngInject';

    var vm = this,
        ctrlOpts = {
            modelName: 'qr'
        };
    angular.extend(this, $controller('BaseCrudCtrl', { vm: vm, ctrlOpts: ctrlOpts }));

    vm.getDetail();
}

module.exports = {
    name: 'QrDeleteCtrl',
    fn: QrDeleteCtrl
};
