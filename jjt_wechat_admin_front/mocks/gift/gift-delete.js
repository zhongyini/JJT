/**
 * mock for 礼品删除
 * @type {Object}
 *
 * params:
 * {id: 1}
 */
module.exports = {
    '/api/gift/delete': {
        POST: {
            data: {
                status: 0,
                message: '删除礼品成功！'
            }
        }
    }
};
