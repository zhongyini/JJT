'use strict';
function WxMaterialImgBaseUrlFilter(AppConfigs) {
	'ngInject';
    return function (input) {
	    if (!input) {
	        return null;
	    }

	    return AppConfigs.WX_MATERIAL_IMG_BASE_URL + input;
    };
}

module.exports = {
    name: 'wxMaterialImgBaseUrl',
    fn: WxMaterialImgBaseUrlFilter
};