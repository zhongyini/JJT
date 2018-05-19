/**
 * mock for 礼品详情
 * @type {Object}
 *
 * params:
 * {id: 1}
 */
module.exports = {
	'/api/gift/detail': {
		POST: {
			data: {
			  "status": 0,
			  "message": "礼品詳情取得成功",
			  "result": {
			    "id": 2,
			    "giftPicId": 2,
			    "giftName": "网球拍",
			    "giftPattern": 2,
			    "giftAmount": 60,
			    "giftStock": 60,
			    "giftCurrencyNum": 0,
			    "giftType": 0,
			    "updateDatetime": "2016-08-29T09:36:57.000Z",
			    "deleteFlg": "0",
			    "tPhoto": {
			      "id": 2,
			      "photoPartition": 2,
			      "photoUrl": "/photo/20160816/20160816024713to.png",
			      "photoSUrl": "/photo/20160816/20160816024713to.png",
			      "uploadTime": "2016-07-22T06:40:44.000Z",
			      "updateDatetime": "2016-07-22T06:40:44.000Z",
			      "deleteFlg": "0"
			    }
			  }
			}
		}
	}
};