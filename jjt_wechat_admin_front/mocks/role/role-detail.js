/**
 * mock for 角色详情
 * @type {Object}
 *
 * params:
 * {id: "1"}
 */
module.exports = {
    '/api/role/detail': {
        POST: {
            data: {
                status: 0,
                result: {
                    id: 1,
                    name: '管理员',
                    instruction: '整体系统的管理员，\n用户最高权限',
                    permissions: ['admin.view', 'admin.edit', 'role.view', 'role.edit']
                }
            }
        }
    }
};
