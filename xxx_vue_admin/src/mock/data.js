export default {
  'post': {
    '/login': {
      'name': 'admin',
      'roleId': '0',
      'authority': [{
        'authorityId': '/config',
        'authorityName': '配置管理'
      }]
    }
  },
  'get': {
    '/logout': {
      'name': ''
    }
  }
}
