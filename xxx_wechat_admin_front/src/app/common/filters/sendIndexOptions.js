'use strict';
function SendIndexOptions() {
	'ngInject';
   return function (input) {
	     return 'key as value for (key, value) in codelist.' + input;
    };
}

module.exports = {
    name: 'sendIndexOptions',
    fn: SendIndexOptions
};