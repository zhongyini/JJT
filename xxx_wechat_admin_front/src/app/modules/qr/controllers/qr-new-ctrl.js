/**
 * 控制器：场景新增
 */
'use strict';

function QrNewCtrl($controller) {
       'ngInject';
    
  
    let vm = this;
    let ctrlOpts = {
            modelName: 'qr'
        };

    angular.extend(this, $controller('BaseCrudCtrl', { vm: vm, ctrlOpts}));
    vm.model.tempFlag = '1';
   	vm.mySave = function() {
   		
		vm.save();
	};
	
}

module.exports = {
    name: 'QrNewCtrl',
    fn: QrNewCtrl
};
