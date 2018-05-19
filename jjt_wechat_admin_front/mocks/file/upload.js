/**
 * mock for 获取上传Token
 * @type {Object}
 *
 * params: none
 */
module.exports = {
    '/api/file/upload': {
        POST: {
            data: {
            	  "status": 0,
            	  "message": "照片上传成功",
            	  "result": {
            	    "photo": {
            	      "photoUrl": "/upload/20160907/20160907051928588to.jpg",
            	      "photoSUrl": "/upload/20160907/20160907051928589ts.jpg"
            	    }
            	  }
            }
        }
    }
};
