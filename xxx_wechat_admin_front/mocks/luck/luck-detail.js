/**
 * mock for 编辑抽奖
 * @type {Object}
 *
 * params:{}
 */
module.exports = {
	'/api/luck/detail': {
		POST: {
			data: {
				status: 0,
				result: {
			        "id": 1,
			        "giftId": 2,
			        "giftName": '套装',
			        "giftAmount": 60,
			        "odds": 30,
			        "dailyWinningTimes": 20,
			        "eventStartDate": "2016-08-30T07:04:31.000Z",
			        "eventStartTime": "2017-08-30T07:04:31.000Z",
			        "eventEndDate": "2016-08-30T07:04:31.000Z",
			        "eventEndTime": "2017-08-30T07:04:31.000Z",
			        "exchangeStartDate": "2016-08-30T07:04:31.000Z",
			        "exchangeStartTime": "2017-09-30T07:04:31.000Z",
			        "exchangeEndDatetime": "2016-08-30T07:04:31.000Z",
			        "exchangeEndtime": "2017-08-30T07:04:31.000Z",
			        "dailyLuckyDrawTimes": 10,
			        "luckyDrawCost": 300,
			        "loseDescription": "打死也抽不bububu",
			        "winDescription": "代码写错了",
			        "updateDatetime": "2016-09-18T06:15:56.000Z",
			        "deleteFlg": "0",
			        "photoSUrl": "/photo/20160908/20160908110833206ts.jpg"
				}
			}
		}
	}
};