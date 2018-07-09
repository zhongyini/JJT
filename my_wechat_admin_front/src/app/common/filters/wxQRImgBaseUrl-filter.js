'use strict';
function WxQRImgBaseUrlFilter(AppConfigs) {
	'ngInject';
    return function (input) {
	    if (!input) {
	        return null;
	    }

	    return AppConfigs.WX_QR_IMG_BASE_URL + input;
    };
}

module.exports = {
    name: 'wxQRImgBaseUrl',
    fn: WxQRImgBaseUrlFilter
};