/**
 * mock for 礼品新增
 * @type {Object}
 *
 * params:
 * {
 *  giftName: '越前龙马套装', // 礼品名
 *  giftPattern : 2, // 礼品区分 1_赛豆，2_实体物品
 *  giftStock: 50, // 库存
 *  giftCurrencyNum: 60 // 礼品货币数量 
 *  photoUrl:"/upload/20160906/20160906011221562to.jpg",
 *  photoSUrl: "/upload/20160906/20160906011221562ts.jpg"}
 */
module.exports = {
    '/api/gift/create': {
        POST: {
            data: {
                status: 0,
                message: '新增礼品成功！'
            }
        }
    }
};
