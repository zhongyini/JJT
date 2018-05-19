'use strict';
function SendTypeOptions() {
	'ngInject';
    return function (input) {
	     return 'key as value for (key, value) in codelist.' + input;
    };
}

module.exports = {
    name: 'sendTypeOptions',
    fn: SendTypeOptions
};