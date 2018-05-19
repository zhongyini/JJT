'use strict';
function TypeOptions() {
	'ngInject';
    return function (input) {
	    return 'key as value for (key, value) in codelist.' + input;
    };
}

module.exports = {
    name: 'typeOptions',
    fn: TypeOptions
};