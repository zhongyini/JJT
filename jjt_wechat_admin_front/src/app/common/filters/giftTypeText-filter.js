'use strict';
function GiftTypeTextFilter() {
	'ngInject';
    return function (input) {
	    if (input == '0') {
	        return '有效';
	    } else {
	    	return '无效';
	    }
    };
}

module.exports = {
    name: 'giftTypeText',
    fn: GiftTypeTextFilter
};