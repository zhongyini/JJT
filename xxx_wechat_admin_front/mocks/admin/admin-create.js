/**
 * mock for 管理员新增
 * @type {Object}
 *
 * params:
 * {name: "test1", 
 *  password: "123456",
 *  email: "test1@test.com", 
 *  roleId: "1", 
 *  avatarUrl:"/upload/20160906/20160906011221562to.jpg",
 *  avatarSUrl: "/upload/20160906/20160906011221562ts.jpg"}
 */
module.exports = {
    '/api/admin/create': {
        POST: {
            data: {
                status: 0,
                message: '新增管理员成功！'
            }
        }
    }
};
