'use strict';

function SexFilter() {
	'ngInject';
	return function(input) {
		if(input == '0') {
			return '未知';
		} else if(input == '1') {
			return '男';
		} else if(input == '2') {
   			return '女';
		}
	};
}

module.exports = {
	name: 'sexFilter',
	fn: SexFilter
};