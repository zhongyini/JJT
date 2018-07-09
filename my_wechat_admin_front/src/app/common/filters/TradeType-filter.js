'use strict';

function TradeTypeFilter() {
	'ngInject';
	return function(input) {
		if(input == '0') {
			return '主题商品';
		} else if(input == '1') {
			return '周边商品';
		} else if(input == '2') {
   			return '单品';
		}
	};
}

module.exports = {
	name: 'TradeTypeFilter',
	fn: TradeTypeFilter
};