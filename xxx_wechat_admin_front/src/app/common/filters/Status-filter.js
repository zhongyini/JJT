'use strict';
function StatusFilter() {
	'ngInject';
    return function (input) {
	    if (input == '0') {
	        return '待处理';
	    } else if(input=='1'){
	    	return '已发送';
	    } else if(input=='2'){
	    	return '发布成功';
	    } else if(input=='3'){
	    	return '发布失败';
	    }
    };
}

module.exports = {
    name: 'statusFilter',
    fn: StatusFilter
};