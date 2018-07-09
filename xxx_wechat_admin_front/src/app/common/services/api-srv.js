'use strict';

function ApiSrv($q, $http, $httpParamSerializer, AppConfigs, MessageSrv, $state) {
	'ngInject';
	const tokenKey = AppConfigs.USER_TOKEN_KEY;

	function exec(url, params, options) {
		//cfpLoadingBar.start();
		let d = $q.defer();

		// prepare request
		options = options || {};
		let req = {
			method: options.method || 'POST',
			url: AppConfigs.API_BASE_URL + url,
			data: params
		};
		req.headers = {};
		req.headers[tokenKey] = localStorage.getItem(tokenKey);
		// execute request
		console.log(req);
		$http(req)
			.then(function(res) {
				//cfpLoadingBar.complete();
				// request ok
				console.log(res);
				let data = res.data;
				let headers = res.headers;

				let isJson = headers('content-type').match(/json/);
				if(isJson) {
					if(data.status === 0) {
						let accessToken = headers(tokenKey);
						if(accessToken) {
							localStorage.setItem(tokenKey, accessToken);
						}
						// result ok
						if(data.message) {
							MessageSrv.success(data.message);
						}
						return d.resolve(data.result);
					} else {
						// result error
						if(data.message) {
							MessageSrv.error(data.message);
							return;
						}
						if(data.result) {
							if(data.result.errorcd === 99) {
								// 返回登录画面
								$state.go('login');
							}
						}
						return d.reject(data);
					}
				} else {
					return d.resolve(data);
				}
			})
			.catch(function(reason) {
				//cfpLoadingBar.complete();
				// request error
				if(reason.data) {
					if(reason.data.status === 110) {
						MessageSrv.error('发生未知错误，请重新操作或联系管理员');
					} else if(reason.data.status === 111) {
						MessageSrv.error('您还没有登录，请登录！');
					} else if(reason.data.status === 112) {
						MessageSrv.error('登录失败，请重新登录！');
						$state.go('login');
					} else if(reason.data.status === 113) {
						MessageSrv.error('您没有权限，请联系管理员！');
					} else if(reason.data.status === 114) {
						MessageSrv.error('非法请求，请重新登录！');
						$state.go('login');
					} else if(reason.data.status === 115) {
						MessageSrv.error('您已在其它地方登录，请重新登录！');
						$state.go('login');
					} else if(reason.data.status === 116) {
						MessageSrv.error('登录超时，请重新登录！');
						$state.go('login');
					} else if(reason.data.status === 404) {
						MessageSrv.error('发生错误，请重试或联系管理员！');
					}
				} else if(reason.status === 500) {
					MessageSrv.error('发生错误，请重试或联系管理员！');
				}

				//              if (AppConfigs.ENV === 'dev') {
				//                  console.error('API调用失败:' + url);
				//                  console.error(reason);
				//              }
				//d.reject(reason);
			});
		return d.promise;
	}

	return {
		exec
	};
}

module.exports = {
	name: 'ApiSrv',
	fn: ApiSrv
};