/**
 * mock for 管理员列表
 * 
 * @type {Object}
 * 
 * params: {page: "1", count: "10", name: "t", email: "a@a.com", deleteFlg: "0"}
 */
module.exports = {
	'/api/admin/list': {
		POST: {
			data: {
				"status": 0,
				"result": {
					"count": 7,
					"list": [{
						"id": 2,
						"name": "chengd1",
						"displayFlg": "1",
						"email": "chengd1@infogreat.cn",
						"roleId": 1,
						"avatarPhotoId": 1,
						"updateDatetime": "2016-08-30T07:05:22.000Z",
						"deleteFlg": "0",
						"tPhoto": {
							"id": 1,
							"photoPartition": 2,
							"photoUrl": "/photo/20160816/20160816024713to.png",
							"photoSUrl": "/photo/20160816/20160816024713to.png",
							"uploadTime": "2016-07-22T06:32:55.000Z",
							"updateDatetime": "2016-08-22T07:04:15.000Z",
							"deleteFlg": "0"
						},
						"tAdminRole": {
							"id": 1,
							"name": "管理员",
							"instruction": "整体系统的管理员， 用户最高权限",
							"updateDatetime": "2016-08-30T07:04:31.000Z",
							"deleteFlg": "0"
						}
					}, {
						"id": 3,
						"name": "果冻111",
						"displayFlg": "1",
						"email": "chengd7@infogreat.cn",
						"roleId": 2,
						"avatarPhotoId": 350,
						"updateDatetime": "2016-09-08T07:36:44.000Z",
						"deleteFlg": "0",
						"tPhoto": {
							"id": 350,
							"photoPartition": 2,
							"photoUrl": "/photo/20160908/20160908110833206to.jpg",
							"photoSUrl": "/photo/20160908/20160908110833206ts.jpg",
							"uploadTime": "2016-09-08T07:36:44.000Z",
							"updateDatetime": "2016-09-08T07:36:44.000Z",
							"deleteFlg": "0"
						},
						"tAdminRole": {
							"id": 2,
							"name": "操作员",
							"instruction": "只可以操作，不可以变更数据",
							"updateDatetime": "2016-08-30T07:04:31.000Z",
							"deleteFlg": "0"
						}
					}, {
						"id": 9,
						"name": "果冻111",
						"displayFlg": "1",
						"email": "chengd3@infogreat.cn",
						"roleId": 1,
						"avatarPhotoId": 345,
						"updateDatetime": "2016-09-08T03:26:10.000Z",
						"deleteFlg": "0",
						"tPhoto": {
							"id": 345,
							"photoPartition": 2,
							"photoUrl": "/photo/20160908/20160908110833206to.jpg",
							"photoSUrl": "/photo/20160908/20160908110833206ts.jpg",
							"uploadTime": "2016-09-08T03:26:10.000Z",
							"updateDatetime": "2016-09-08T03:26:10.000Z",
							"deleteFlg": "0"
						},
						"tAdminRole": {
							"id": 1,
							"name": "管理员",
							"instruction": "整体系统的管理员， 用户最高权限",
							"updateDatetime": "2016-08-30T07:04:31.000Z",
							"deleteFlg": "0"
						}
					}, {
						"id": 10,
						"name": "果冻111",
						"displayFlg": "1",
						"email": "chengd4@infogreat.cn",
						"roleId": 1,
						"avatarPhotoId": 346,
						"updateDatetime": "2016-09-08T03:26:38.000Z",
						"deleteFlg": "0",
						"tPhoto": {
							"id": 346,
							"photoPartition": 2,
							"photoUrl": "/photo/20160908/20160908110833206to.jpg",
							"photoSUrl": "/photo/20160908/20160908110833206ts.jpg",
							"uploadTime": "2016-09-08T03:26:38.000Z",
							"updateDatetime": "2016-09-08T03:26:38.000Z",
							"deleteFlg": "0"
						},
						"tAdminRole": {
							"id": 1,
							"name": "管理员",
							"instruction": "整体系统的管理员， 用户最高权限",
							"updateDatetime": "2016-08-30T07:04:31.000Z",
							"deleteFlg": "0"
						}
					}, {
						"id": 11,
						"name": "果冻111",
						"displayFlg": "1",
						"email": "chengd5@infogreat.cn",
						"roleId": 1,
						"avatarPhotoId": 347,
						"updateDatetime": "2016-09-08T03:28:40.000Z",
						"deleteFlg": "0",
						"tPhoto": {
							"id": 347,
							"photoPartition": 2,
							"photoUrl": "/photo/20160908/20160908110833206to.jpg",
							"photoSUrl": "/photo/20160908/20160908110833206ts.jpg",
							"uploadTime": "2016-09-08T03:28:40.000Z",
							"updateDatetime": "2016-09-08T03:28:40.000Z",
							"deleteFlg": "0"
						},
						"tAdminRole": {
							"id": 1,
							"name": "管理员",
							"instruction": "整体系统的管理员， 用户最高权限",
							"updateDatetime": "2016-08-30T07:04:31.000Z",
							"deleteFlg": "0"
						}
					}, {
						"id": 13,
						"name": "果冻111",
						"displayFlg": "1",
						"email": "chengd14@infogreat.cn",
						"roleId": 1,
						"avatarPhotoId": 349,
						"updateDatetime": "2016-09-08T05:58:12.000Z",
						"deleteFlg": "0",
						"tPhoto": {
							"id": 349,
							"photoPartition": 2,
							"photoUrl": "/photo/20160908/20160908110833206to.jpg",
							"photoSUrl": "/photo/20160908/20160908110833206ts.jpg",
							"uploadTime": "2016-09-08T05:58:12.000Z",
							"updateDatetime": "2016-09-08T05:58:12.000Z",
							"deleteFlg": "0"
						},
						"tAdminRole": {
							"id": 1,
							"name": "管理员",
							"instruction": "整体系统的管理员， 用户最高权限",
							"updateDatetime": "2016-08-30T07:04:31.000Z",
							"deleteFlg": "0"
						}
					}, {
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
					}]
				}
			}
		}
	}
};