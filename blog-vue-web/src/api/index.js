const BASEURL = 'http://127.0.0.1:8888/api'
const URL = 'http://127.0.0.1:8888'
export const api = {
  URL:`${URL}`,
  // 获取主页一言
  getTitle: 'https://v1.hitokoto.cn/',
  // 获取主页博客列表
  getIndexBlogList:`${BASEURL}/indexBlogList`,
  // 获取主页标签列表
  getIndexTagList:`${BASEURL}/indexTagList`,
  //获取博客数,标签数,评论数,浏览量
  getIndexBlogsCount:`${BASEURL}/indexBlogsCount`,
  //获取博客详情
  getBlogDetails:`${BASEURL}/getBlogDetails`,
  //获取文章详情(友联,关于我)
  getDetailsByType:`${BASEURL}/getDetailsByType`,
  //获取全部标签
  getTags:`${BASEURL}/getTags`,
  //根据标签获取博客列表
  getBlogByTag:`${BASEURL}/getBlogByTag/`,
  //获取博客归档信息
  getBlogArchive:`${BASEURL}/getBlogArchive/`,
  //获取照片墙信息
  getImageList:`${BASEURL}/getImageList`,
  //搜索博客
  getBlogByKeyed:`${BASEURL}/getBlogByKeyed`,
  //查询博客评论
  getComment:`${BASEURL}/commentList`,
  //添加博客评论
  addComment:`${BASEURL}/addComment`,
  //评论邮件提醒
  emailAlert:`${BASEURL}/emailAlert`,
  //拉取qq头像
  getQQInfo:`${BASEURL}/getQQInfo/`,
}
export default api
