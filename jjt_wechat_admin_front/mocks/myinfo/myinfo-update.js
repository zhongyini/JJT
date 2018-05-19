/**
 * mock for 编辑个人资料
 * @type {Object}
 * params: 
 * {
 * id: 1,
 * name: 'test1',
 * email: 'test1@test.com',
 * avatarUrl: '/upload/20160906/20160906011221562to.jpg',
 * avatarSUrl: '/upload/20160906/20160906011221562ts.jpg',
 * deleteFlg: '0'
 * }
 */
module.exports = {
    '/api/myinfo/update': {
        POST: {
            data: {
                status: 0,
                message: '编辑个人资料成功！'
            }
        }
    }
};
