import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/home',
    children: [{
      path: 'home',
      name: 'Home',
      component: () => import('@/views/home/index'),
      meta: { title: '主页', icon: 'dashboard' }
    }]
  },

  {
    path: '/blog',
    component: Layout,
    redirect: '/blog/list',
    name: 'Blog',
    meta: { title: '文章', icon: 'el-icon-notebook-1' },
    children: [
      {
        path: 'list',
        name: 'List',
        component: () => import('@/views/blog/blogList'),
        meta: { title: '文章列表', icon: 'el-icon-notebook-2' }
      },
      {
        path: 'add',
        name: 'Add',
        component: () => import('@/views/blog/addBlog'),
        meta: { title: '新增文章', icon: 'el-icon-document-add' }
      },
      {
        path: 'comment',
        name: 'Comment',
        component: () => import('@/views/blog/comment'),
        meta: { title: '评论管理', icon: 'el-icon-s-comment' }
      },
    ]
  },
  {
    path: '/tags',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'Index',
        component: () => import('@/views/tags/index'),
        meta: { title: '标签', icon: 'el-icon-price-tag' }
      }
    ]
  },
  {
    path: '/imgs',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'Index',
        component: () => import('@/views/imgs/index'),
        meta: { title: '照片墙', icon: 'el-icon-picture' }
      }
    ]
  },
  {
    path: 'github-link',
    component: Layout,
    children: [
      {
        path: 'https://github.com/JMuSi404',
        meta: { title: 'GitHub', icon: 'link' }
      }
    ]
  },

  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
