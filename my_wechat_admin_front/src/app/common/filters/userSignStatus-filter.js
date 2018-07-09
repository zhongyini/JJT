'use strict';
function UserSignStatusFilter() {
	'ngInject';
    return function (input) {
	    if (input == '1') {
	        return '签到成功';
	    } else if (input == '0') {
	    	return '签到失败';
	    } else if (input == '2') {
	    	return '异常';
	    } else if (input == '3') {
	    	return '调用接口错误';
	    }
    };
}

module.exports = {
    name: 'userSignStatus',
    fn: UserSignStatusFilter
};