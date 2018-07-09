'use strict';
function SendTypeFilter() {
	'ngInject';
    return function (input) {
	    if (input == '1') {
	        return '普通群推';
	    } else if(input=='2'){
	    	return 'AB推送';
	    }else if(input=='3'){
	    	return '发送次数变动群推';
	    }else if(input=='4'){
	    	return '非会员群推';
	    }else if(input=='5'){
	    	return 'csv群推';
	    }else if(input=='6'){
	    	return '特定用户群推';
	    }else if(input=='7'){
	    	return '区间月龄群推';
	    }else if(input=='8'){
	    	return '一般月龄群推';
	    }else if(input=='9'){
	    	return '非月龄群推';
	    }else if(input=='10'){
	    	return '主题商品系统自动化群推';
	    }
    };
}

module.exports = {
    name: 'sendType',
    fn: SendTypeFilter
};
