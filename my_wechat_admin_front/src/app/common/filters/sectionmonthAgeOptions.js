'use strict';
function SectionmonthAgeOptions() {
	'ngInject';
   return function (input) {
	     return 'key as value for (key, value) in codelist.' + input;
    };
}

module.exports = {
    name: 'sectionmonthAgeOptions',
    fn: SectionmonthAgeOptions
};