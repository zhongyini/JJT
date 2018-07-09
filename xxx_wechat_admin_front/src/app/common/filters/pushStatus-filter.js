'use strict';
function PushStatusFilter() {
	'ngInject';
    return function (input) {
	    if (input == '0') {
	        return '待处理';
	    }else if(input=='2'){
	    	return '发布成功';
	    }
    };
}

module.exports = {
    name: 'puhsStatus',
    fn: PushStatusFilter
};