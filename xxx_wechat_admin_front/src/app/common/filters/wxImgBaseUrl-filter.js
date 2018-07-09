'use strict';
function WxImgBaseUrlFilter(AppConfigs) {
	'ngInject';
    return function (input) {
	    if (!input) {
	        return null;
	    }

	    return AppConfigs.WX_IMG_BASE_URL + input;
    };
}

module.exports = {
    name: 'wxImgBaseUrl',
    fn: WxImgBaseUrlFilter
};