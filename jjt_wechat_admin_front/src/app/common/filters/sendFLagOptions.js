'use strict';
function SendFlagOptions() {
	'ngInject';
    return function (input) {
	     return 'key as value for (key, value) in codelist.' + input;
    };
}

module.exports = {
    name: 'sendFlagOptions',
    fn: SendFlagOptions
};