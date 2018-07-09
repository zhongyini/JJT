/**
 * 控制器：URL编辑
 */
'use strict';

function UrlEditCtrl($controller) {
    'ngInject';

    let vm = this;
    let ctrlOpts = {
            modelName: 'url'
        };
    angular.extend(this, $controller('BaseCrudCtrl', { vm: vm, ctrlOpts}));

    vm.getDetail();
}

module.exports = {
    name: 'UrlEditCtrl',
    fn: UrlEditCtrl
};
