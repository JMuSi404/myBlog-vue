import Vue from 'vue'
import VueRouter from 'vue-router'
import home from "@/views/home";
import  blogDetails from '@/views/blogDetails'
import archive from '@/views/archive'
import search from '@/views/search'
import tags from '@/views/tags'
import photoWall from '@/views/photoWall'

Vue.use(VueRouter)
//解决vue路由重复导航错误
//获取原型对象上的push函数
const originalPush = VueRouter.prototype.push
//修改原型对象中的push方法
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}
const routes = [
    // 主页
  {
    path: '/',
    name: 'home',
    component: home,
    meta: { isAuth: true, title:'主页' }
  },
  // 文章详情
  {
    path: '/blog',
    name: 'blog',
    component: blogDetails,
    meta: { isAuth: true, title:'文章详情' }
  },
  // 归档
  {
    path: '/archive',
    name: 'archive',
    component: archive,
    meta: { isAuth: true, title:'归档' }
  },
  // 搜索
  {
    path: '/search',
    name: 'search',
    component: search,
    meta: { isAuth: true, title:'搜索结果' }
  },
  // 标签
  {
    path: '/tags',
    name: 'tags',
    component: tags,
    meta: { isAuth: true, title:'标签分类' }
  },
  // 照片墙
  {
    path: '/photoWall',
    name: 'photoWall',
    component: photoWall,
    meta: { isAuth: true, title:'照片墙' }
  },
]
const router = new VueRouter({
  routes
})
//全局前置路由守卫————初始化的时候被调用、每次路由切换之前被调用
router.beforeEach((to, from, next) => {
  //如果路由需要跳转
  if (to.meta.isAuth) {
    window.scrollTo({
      top: 0
    })
    next()
  }
})

export default router
