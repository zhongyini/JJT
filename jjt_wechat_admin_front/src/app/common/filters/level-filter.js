'use strict';
function LevelStatusFilter() {
	'ngInject';
    return function (input) {
	    if (input == '1') {
	        return '新晋';
	    } else if(input=='2'){
	    	return '进阶';
	    }else if(input=='3'){
	    	return '高级';
	    }else if(input=='4'){
	    	return 'VIP';
	    }
    };
}

module.exports = {
    name: 'levelStatus',
    fn: LevelStatusFilter
};