'use strict';
function SendFlagOptionsFilter() {
	'ngInject';
    return function (input) {
	     if (input == '1') {
	        return '普通群推';
	    } 
    };
}

module.exports = {
    name: 'sendFlagOptionsFilter',
    fn: SendFlagOptionsFilter
};