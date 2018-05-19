/**
 * mock for 管理员删除
 * @type {Object}
 *
 * params:
 * {id: "1"}
 */
module.exports = {
    '/api/admin/delete': {
        POST: {
            data: {
                status: 0,
                message: '删除管理员成功！'
            }
        }
    }
};
