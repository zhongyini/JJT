'use strict';

function AccessFlag() {
	'ngInject';
	return function(input) {
		if(input == '0') {
			return '未接入';
		} else if(input == '1') {
			return '接入中';
		} else if(input == '2') {
   			return '已关闭';
		}
	};
}

module.exports = {
	name: 'AccessFlag',
	fn: AccessFlag
};