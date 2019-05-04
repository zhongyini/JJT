const servicePath = 'http://localhost:8082'

class utilObject {
	async request(url, params, method) {
		return await this._request(url, params, method)
	}

	_request(url, params, method = 'GET') {
		return new Promise((resolve, reject) => {
			uni.request({
				url: servicePath + url,
				// url,
				method,
				header: {
					'content-type': 'application/x-www-form-urlencoded;charset=utf8'
				},
				data: params,
				success: res => {
					if (res.statusCode.toString().startsWith('2')) {
						resolve(res.data)
					} else {
						this.tips('网络错误')
					}
				},
				fail: () => {},
				complete: () => {}
			});
		})
	}

	tips(title) {
		uni.showToast({
			title
		});
	}

	userAction() {
		uni.showModal({
			title: '',
			content: '',
			showCancel: false,
			cancelText: '',
			confirmText: '',
			success: res => {},
			fail: () => {},
			complete: () => {}
		});
	}
}

export default new utilObject()
