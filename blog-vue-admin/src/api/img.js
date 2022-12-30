import request from '@/utils/request'

export function getImage(data) {
  return request({
    url: '/getImageList',
    method: 'get',
    params:{
      pageNum:data.pageNum,
      limit:data.limit
    }
  })
}

export function deleteImage(data) {
  return request({
    url: '/admin/deleteImage/'+data,
    method: 'post'
  })
}


