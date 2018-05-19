'use strict';
function KeyTypeFilter() {
	'ngInject';
    return function (input) {
	    if (input == '1') {
	        return '关键字';
	    } else {
	    	return '常见问题';
	    }
    };
}

module.exports = {
    name: 'keyTypeText',
    fn: KeyTypeFilter
};