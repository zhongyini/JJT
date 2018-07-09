/**
 * 控制器：个人资料
 */
'use strict';

function MyinfoDetail($controller) {
    'ngInject';

    var vm = this,
        ctrlOpts = {
            modelName: 'adminManager'
        };
    angular.extend(this, $controller('BaseCrudCtrl', { vm: vm, ctrlOpts: ctrlOpts }));

   	vm.getDetail();
}

module.exports = {
    name: 'MyinfoDetail',
    fn: MyinfoDetail
};
