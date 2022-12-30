import request from '@/utils/request'

export function getTags() {
  return request({
    url: '/getTags',
    method: 'get'
  })
}

export function addTags(data) {
  return request({
    url: '/admin/addTags',
    method: 'post',
    params:{
      tagName:data
    }
  })
}

export function getTagsQuery(data) {
  return request({
    url: '/admin/getTags',
    method: 'get',
    params:{
      limit:data.limit,
      pageNum:data.pageNum
    }
  })
}


export function removeTags(data) {
  return request({
    url: '/admin/removeTags/'+data,
    method: 'post'
  })
}


export function editTags(data) {
  return request({
    url: '/admin/editTags',
    method: 'post',
    data
  })
}
