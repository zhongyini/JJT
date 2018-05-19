'use strict';

function RunStatusFilter() {
	'ngInject';
	return function(input) {
		if(input == '0') {
			return '运行开始';
		} else if(input == '1') {
			return '正常完成';
		} else if(input == '2') {
   			return '异常';
		}
	};
}

module.exports = {
	name: 'runStatusFilter',
	fn: RunStatusFilter
};