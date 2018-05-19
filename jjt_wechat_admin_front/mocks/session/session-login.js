/**
 * mock for 用户登录
 * @type {Object}
 */
module.exports = {
    '/api/session/login': {
        POST: {
            data: {
                status: 0,
                result: {
                    id: 1,
                    name: '管理员小王',
                    userImg: '/tmp/img.jpg',
                    permissions: [
                                  'photo.view', 
                                  'photo.edit', 
                                  'luck.view',
                                  'luck.edit',
                                  'gift.view',
                                  'gift.edit',
                                  'certificate.view',
                                  'certificate.edit',
                                  'payment.view',
                                  'payment.edit',
                                  'admin.view',
                                  'admin.edit',
                                  'role.view',
                                  'role.edit'
                    ]
                }
            },
            headers: {
                'x-access-token': 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE0Njg4MTIzNjEsImV4cCI6MTQ2ODg5ODc2MX0.mOAC1oPZ_K_bjeF8YGtInUimWywFhdmOC_nZcF-X4B0'
            }
        }
    }
};
