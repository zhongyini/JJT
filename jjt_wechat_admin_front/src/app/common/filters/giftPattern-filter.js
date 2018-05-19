'use strict';

function GiftPatternTextFilter() {
	'ngInject';
	return function(input) {
		if (input == 2) {
			return '实体礼品';
		} else {
			return '赛豆';
		}
	};
}

module.exports = {
	name: 'GiftPatternTextFilter',
	fn: GiftPatternTextFilter
};