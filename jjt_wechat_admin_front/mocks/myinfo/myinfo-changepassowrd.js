/**
 * mock for 变更个人密码
 * @type {Object}
 * params: 
 * {
 * id:1,
 * oldPassword: '旧密码',
 * newPassword: '新密码',
 * confirmPassword: '确认密码',
 * }
 */
module.exports = {
    '/api/myinfo/changePassword': {
        POST: {
            data: {
                status: 0,
                message: '密码修改成功,请重新登录！'
            }
        }
    }
};
