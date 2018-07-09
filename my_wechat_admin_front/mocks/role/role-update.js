/**
 * mock for 角色变更
 * @type {Object}
 *
 * params:
 * {
 * id:1,
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
    '/api/role/update': {
        POST: {
            data: {
                status: 0,
                message: '变更角色信息成功！'
            }
        }
    }
};
