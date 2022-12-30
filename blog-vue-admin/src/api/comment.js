import request from '@/utils/request'

export function queryComment(data) {
  return request({
    url: '/admin/getComment',
    method: 'get',
    params:{
      limit:data.pageSize,
      pageNum:data.pageNum,
      blogId:data.blogId,
      name:data.nickname
    }
  })
}

export function editComment(data) {
  return request({
    url: '/admin/editComment',
    method: 'post',
    data
  })
}


export function removeComment(data) {
  return request({
    url: '/admin/removeComment/'+data,
    method: 'post'
  })
}
