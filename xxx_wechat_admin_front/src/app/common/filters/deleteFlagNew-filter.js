'use strict';
function DeleteFlagNewFilter() {
	'ngInject';
    return function (input) {
	    if (input == '0') {
	        return '有效';
	    } else if(input == '1'){
	    	return '删除';
	    }
    };
}

module.exports = {
    name: 'deleteFlagNew',
    fn: DeleteFlagNewFilter
};