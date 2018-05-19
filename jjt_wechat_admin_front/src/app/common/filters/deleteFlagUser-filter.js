'use strict';
function DeleteFlagUserFilter() {
	'ngInject';
    return function (input) {
	    if (input == '0') {
	        return '已关注';
	    } else if(input=='1'){
	    	return '未关注';
	    }
    };
}

module.exports = {
    name: 'deleteFlagUser',
    fn: DeleteFlagUserFilter
};