'use strict';
function LockFlagFilter() {
	'ngInject';
    return function (input) {
	    if (input == '0') {
	        return '未锁定';
	    } else if (input == '1'){
	    	return '锁定';
	    }
    };
}

module.exports = {
    name: 'lockFlagFilter',
    fn: LockFlagFilter
};