/**
 * mock for 抽奖上传图片
 * 
 * @type {Object}
 * 
 * params: {"list": [{"photo": 
 * {"id": 350, 
 * "photoPartition": 2,
 * "photoUrl": "/photo/20160908/20160908110833206to.jpg",
 * "photoSUrl": "/photo/20160908/20160908110833206ts.jpg",
 * "uploadTime": "2016-09-08T07:36:44.000Z",
 * "updateDatetime": "2016-09-08T07:36:44.000Z",
 * "deleteFlg": "0"
 * }
 * 。。。
 * }

 * }
 */
module.exports = {
	'/api/luck/photoUpdate': {
		POST: {
			data: {
				status: 0,
				message: '变更抽奖图片信息成功！'
			}
		}
	}
};