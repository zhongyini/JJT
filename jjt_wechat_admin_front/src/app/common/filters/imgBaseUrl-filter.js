'use strict';
function ImgBaseUrlFilter(AppConfigs) {
	'ngInject';
    return function (input) {
	    if (!input) {
	        return null;
	    }

	    return AppConfigs.IMG_BASE_URL + input;
    };
}

module.exports = {
    name: 'imgBaseUrl',
    fn: ImgBaseUrlFilter
};