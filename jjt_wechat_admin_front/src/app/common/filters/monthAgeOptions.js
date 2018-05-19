'use strict';
function MonthAgeOptions() {
	'ngInject';
    return function (input) {
	    return 'key as value for (key, value) in codelist.' + input;
    };
}

module.exports = {
    name: 'monthAgeOptions',
    fn: MonthAgeOptions
};