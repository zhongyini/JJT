'use strict';

function GiftAwardStatusTextFilter() {
	'ngInject';
	return function(input) {
		if (input == 0) {
			return '未兑换';
		}
		if (input == 1) {
			return '已兑换';
		}
		if (input == 9) {
			return '礼品已失效';
		}
	};
}

module.exports = {
	name: 'giftAwardStatusText',
	fn: GiftAwardStatusTextFilter
};