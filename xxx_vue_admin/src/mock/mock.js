import data from './data'

let method = ['get', 'post', 'put', 'delete']
function result (url, params, method) {
  return data[method][url]
}
let res = (url, params, method) => {
  return {
    'result': {
      'data': result(url, params, method),
      'message': 'success',
      'status': '0'
    }
  }
}
let api = {
  get (url) {
    if (url === '/logout') {
      return res(url, {}, method[0])
    }
  },
  post (url, params) {
    if (url === '/login') {
      return res(url, params, method[1])
    }
  }
}
export default {
  api
}
