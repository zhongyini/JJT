import axios from './axios'
import mock from '../mock/mock'

let instance = process.env.NODE_ENV === 'local' ? mock.api : process.env.NODE_ENV === 'production' ? axios() : axios()

let obj = {
  get (url, params, headers) {
    let options = {}
    if (params) {
      options.params = params
    }
    if (headers) {
      options.headers = headers
    }
    return instance.get(url, options)
  },
  post (url, params, headers) {
    let options = {}
    if (headers) {
      options.headers = headers
    }
    return instance.post(url, params, options)
  },
  put  (url, params, headers) {
    let options = {}
    if (headers) {
      options.headers = headers
    }
    return instance.put(url, params, options)
  },
  delete (url, params, headers) {
    let options = {}
    if (params) {
      options.params = params
    }
    if (headers) {
      options.headers = headers
    }
    return instance.delete(url, options)
  }
}
export default obj
