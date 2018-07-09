'use strict';
function UserStatusFilter() {
	'ngInject';
    return function (input) {
	    if (input == '0') {
	        return '在籍';
	    } else if(input=='1'){
	    	return '非在籍';
	    }
    };
}

module.exports = {
    name: 'userStatus',
    fn: UserStatusFilter
};