/**
 * mock for 礼品变更
 * @type {Object}
 * params: 
 * {id: 100005, 
 *  giftName: '越前龙马套装',
 *  giftName： 0,
 *  photoUrl:"/upload/20160906/20160906011221562to.jpg",
 *  photoSUrl: "/upload/20160906/20160906011221562ts.jpg"}
 */
module.exports = {
    '/api/gift/update': {
        POST: {
            data: {
                status: 0,
                message: '变更礼品信息成功！'
            }
        }
    }
};
