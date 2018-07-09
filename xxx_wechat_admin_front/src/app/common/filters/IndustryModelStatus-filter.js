'use strict';
function IndustryModelStatusFilter() {
	'ngInject';
    return function (input) {
	    if (input == '0') {
	        return '未同步';
	    } else if(input=='1'){
	    	return '已同步';
	    }
    };
}

module.exports = {
    name: 'industryModelStatusFilter',
    fn: IndustryModelStatusFilter
};