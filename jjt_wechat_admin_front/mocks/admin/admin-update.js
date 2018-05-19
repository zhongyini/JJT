/**
 * mock for 管理员变更
 * @type {Object}
 * params: 
 * {
 * id: 1,
 * name: 'test1',
 * email: 'test1@test.com',
 * roleId:1,
 * avatarUrl: '/upload/20160906/20160906011221562to.jpg',
 * avatarSUrl: '/upload/20160906/20160906011221562ts.jpg',
 * deleteFlg: '0'
 * }
 */
module.exports = {
    '/api/admin/update': {
        POST: {
            data: {
                status: 0,
                message: '变更管理员信息成功！'
            }
        }
    }
};
