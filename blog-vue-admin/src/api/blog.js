import request from '@/utils/request'

export function blogList(data) {
  return request({
    url: '/admin/getBlogList',
    method: 'post',
    data
  })
}

export function getBlogListIdAndName() {
  return request({
    url: '/admin/getBlogListIdAndName',
    method: 'get'
  })
}

export function addBlog(data) {
  return request({
    url: '/admin/addBlog',
    method: 'POST',
    data
  })
}


export function editBlog(data) {
  return request({
    url: '/admin/editBlog',
    method: 'POST',
    data
  })
}

export function getBlogDetails(data) {
  return request({
    url: '/admin/getBlogDetails',
    method: 'get',
    params:{
      blogId:data
    }
  })
}

export function updatePublished(data) {
  return request({
    url: '/admin/updatePublished',
    method: 'post',
    data
  })
}


export function deleteBlogs(data) {
  return request({
    url: '/admin/deleteBlogs',
    method: 'post',
    params:{
      ids:data
    }
  })
}
