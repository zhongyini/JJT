/**
 * mock for 礼品列表
 * 
 * @type {Object}
 * 
 * params: {"giftCode" : SJKoAOjn --礼券编码}
 */
module.exports = {
	'/api/certificate/detail': {
		POST: {
			data: {
				"status": 0,
				"result": {
				    "id": 66,
				    "giftId": 2,
				    "userId": 63,
				    "giftCode": "SJKoAOjn",
				    "gainDatetime": "2016-09-18T02:40:32.000Z",
				    "giftAwardStatus": 0,
				    "exchangeDatetime": "2016-09-18T02:46:20.000Z",
				    "exchangeStartDatetime": "2016-09-16T06:00:00.000Z",
				    "exchangeEndDatetime": "2016-09-30T06:00:00.000Z",
				    "updateDatetime": "2016-09-18T05:45:03.000Z",
				    "deleteFlg": "0",
				    "giftName": "网球拍"
				}
			}
		}
	}
};