/**
 * mock for 管理员详情
 * @type {Object}
 *
 * params:
 * {id: "1"}
 */
module.exports = {
    '/api/admin/detail': {
        POST: {
            data: {
            	  "status": 0,
            	  "message": "管理员詳情取得成功",
            	  "result": {
            	    "id": 12,
            	    "name": "果冻111",
            	    "displayFlg": "1",
            	    "email": "chengd6@infogreat.cn",
            	    "roleId": 1,
            	    "avatarPhotoId": 348,
            	    "updateDatetime": "2016-09-08T07:27:39.000Z",
            	    "deleteFlg": "1",
            	    "tPhoto": {
            	      "id": 348,
            	      "photoPartition": 2,
            	      "photoUrl": "/photo/20160908/20160908110833206to.jpg",
            	      "photoSUrl": "/photo/20160908/20160908110833206ts.jpg",
            	      "uploadTime": "2016-09-08T03:29:42.000Z",
            	      "updateDatetime": "2016-09-08T05:16:50.000Z",
            	      "deleteFlg": "0"
            	    },
            	    "tAdminRole": {
            	      "id": 1,
            	      "name": "管理员",
            	      "instruction": "整体系统的管理员， 用户最高权限",
            	      "updateDatetime": "2016-08-30T07:04:31.000Z",
            	      "deleteFlg": "0"
            	    }
            	  }
            }
        }
    }
};
