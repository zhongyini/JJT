'use strict';
function ReplyTypeFilter() {
	'ngInject';
    return function (input) {
	    if (input == '1') {
	        return '文字';
	    }else if(input=='2'){
	    	return '图文';
	    } else {
	    	return '图片';
	    }
    };
}

module.exports = {
    name: 'replyTypeText',
    fn: ReplyTypeFilter
};