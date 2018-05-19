'use strict';

function QrTempFlag() {
	'ngInject';
	return function(input) {
		if(input == '0') {
			return '临时';
		} else if(input == '1') {
			return '永久';
		} 
	};
}

module.exports = {
	name: 'QrTempFlag',
	fn: QrTempFlag
};