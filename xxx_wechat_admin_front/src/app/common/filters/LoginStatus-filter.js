'use strict';
function LoginStatusFilter() {
	'ngInject';
    return function (input) {
	    if (input == '0') {
	        return '否';
	    } else if(input=='1'){
	    	return '是';
	    }
    };
}

module.exports = {
    name: 'loginStatusFilter',
    fn: LoginStatusFilter
};