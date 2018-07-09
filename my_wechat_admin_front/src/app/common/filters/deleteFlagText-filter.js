'use strict';
function DeleteFlagTextFilter() {
	'ngInject';
    return function (input) {
	    if (input == '0') {
	        return '待发布';
	    } else if(input == '1'){
	    	return '删除';
	    }else if(input == '2'){
	    	return '有效';
	    }
    };
}

module.exports = {
    name: 'deleteFlagText',
    fn: DeleteFlagTextFilter
};