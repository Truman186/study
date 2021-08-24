import request from '../util/request'
export function addUserTest() {
  return request({
    url: '/add/addUserTest',
    method: 'post',
    // params: orderNo
  })
}
