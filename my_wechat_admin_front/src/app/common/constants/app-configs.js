'use strict';
let AppConfigs = {
	ENV: '${ENV}',
	API_BASE_URL: '${API.BASE}/',
	IMG_BASE_URL: '${IMG.BASE}',
	WX_IMG_BASE_URL: '${WX.IMG.BASE}',
	WX_MATERIAL_IMG_BASE_URL: '${WX.MATERIAL.IMG.BASE}',
	WX_QR_IMG_BASE_URL: '${WX.QR.IMG.BASE}',
	USER_TOKEN_KEY: 'x-access-token'
};

module.exports = {
     name: 'AppConfigs',
     fn: AppConfigs
};
