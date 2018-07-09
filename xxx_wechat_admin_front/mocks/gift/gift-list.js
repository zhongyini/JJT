/**
 * mock for 礼品列表
 * 
 * @type {Object}
 * 
 * params: {"id" : 100001,"giftName":"qiupai","giftPattern":1,'deleteFlg':'0'}
 */
module.exports = {
	'/api/gift/list': {
		POST: {
			data: {
				"status": 0,
				"result": {
					"count": 3,
					"list": [{
						"id": 100001,
						"giftName": "球拍",
						"giftPattern": 2,
						"giftAmount": 100,
						"giftStock": 99996,
						"giftCurrencyNum": 500,
						"deleteFlg": '0',
						"tPhoto": {
							"id": 1,
							"photoPartition": 2,
							"photoUrl": "/photo/20160816/20160816024713to.png",
							"photoSUrl": "/photo/20160816/20160816024713to.png",
							"uploadTime": "2016-07-22T06:32:55.000Z",
							"updateDatetime": "2016-08-22T07:04:15.000Z",
							"deleteFlg": "0"
						}
					}, {
						"id": 100002,
						"giftName": "球帽",
						"giftPattern": 2,
						"giftAmount": 200,
						"giftStock": 99996,
						"giftCurrencyNum": 500,
						"deleteFlg": '1',
						"tPhoto": {
							"id": 1,
							"photoPartition": 2,
							"photoUrl": "/photo/20160816/20160816024713to.png",
							"photoSUrl": "/photo/20160816/20160816024713to.png",
							"uploadTime": "2016-07-22T06:32:55.000Z",
							"updateDatetime": "2016-08-22T07:04:15.000Z",
							"deleteFlg": "0"
						}
					}, {
						"id": 100003,
						"giftName": "3000赛豆",
						"giftPattern": 1,
						"giftAmount": 300,
						"giftStock": 99996,
						"giftCurrencyNum": 3000,
						"deleteFlg": '0',
						"tPhoto": {
							"id": 1,
							"photoPartition": 2,
							"photoUrl": "/photo/20160816/20160816024713to.png",
							"photoSUrl": "/photo/20160816/20160816024713to.png",
							"uploadTime": "2016-07-22T06:32:55.000Z",
							"updateDatetime": "2016-08-22T07:04:15.000Z",
							"deleteFlg": "0"
						}
					}]
				}
			}
		}
	}
};