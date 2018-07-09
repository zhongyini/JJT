'use strict';
function MonthMarkFilter() {
	'ngInject';
    return function (input) {
	    if (input == 'A1') {
	        return '第一个月';
	    } else if(input=='A2'){
	    	return '第二个月';
	    }else if(input=='A3'){
	    	return '第三个月';
	    }else if (input == 'B1') {
	        return '第一个月';
	    } else if(input=='B2'){
	    	return '第二个月';
	    }else if(input=='B3'){
	    	return '第三个月';
	    }else if (input == 'C1') {
	        return '第一个月';
	    } else if(input=='C2'){
	    	return '第二个月';
	    }else if(input=='C3'){
	    	return '第三个月';
	    }else if (input == 'D1') {
	        return '第一个月';
	    } else if(input=='D2'){
	    	return '第二个月';
	    }else if(input=='D3'){
	    	return '第三个月';
	    }
    };
}

module.exports = {
    name: 'monthMark',
    fn: MonthMarkFilter
};

