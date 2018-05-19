'use strict';
function PreviewTypeTextFilter() {
	'ngInject';
    return function (input) {
	    if (input == '1') {
	        return '线下活动';
	    } else {
	    	return '群推';
	    }
    };
}

module.exports = {
    name: 'previewTypeText',
    fn: PreviewTypeTextFilter
};