'use strict';
function SendTypeOptionsFilter() {
	'ngInject';
    return function (input) {
	     if (input == '1') {
	        return '非会员';
	    } 
    };
}

module.exports = {
    name: 'sendTypeOptionsFilter',
    fn: SendTypeOptionsFilter
};