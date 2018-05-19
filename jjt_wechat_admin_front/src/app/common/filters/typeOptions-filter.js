'use strict';
function TypeOptionsFilter() {
	'ngInject';
    return function (input) {
	    if (input == '1') {
	        return '图文';
	    } else if(input=='2'){
	    	return '文章';
	    }
    };
}

module.exports = {
    name: 'typeOptionsFilter',
    fn: TypeOptionsFilter
};