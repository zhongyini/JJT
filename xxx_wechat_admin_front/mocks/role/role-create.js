/**
 * mock for 角色新增
 * @type {Object}
 *
 * params:
 * {
 * name: "角色名", 
 * instruction: "角色名描述", 
 * permissions: [
 * 'photo.view', 
 * 'photo.edit', 
 * 'luck.view',
 * 'luck.edit',
 * 'gift.view',
 * 'gift.edit',
 * 'certificate.view',
 * 'certificate.edit',
 * 'admin.view',
 * 'admin.edit',
 * 'role.view',
 * 'role.edit'
 * ]}
 */
module.exports = {
    '/api/role/create': {
        POST: {
            data: {
                status: 0,
                message: '新增角色成功！'
            }
        }
    }
};
