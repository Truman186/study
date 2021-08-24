import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/Login.vue'
import success from '../components/Success.vue'
import Home from '../components/Home.vue'
import test from '../components/test.vue'
import Welcome from '../components/Welcome.vue'
import User from '../components/admin/User.vue'
import Rights from '../components/admin/Rights'
import Sport from '../components/admin/sport'
import goods from '../components/admin/goods'
import course from '../components/admin/Course'
import verify from '../components/admin/verify' 
import source from '../components/admin/source' 
import mycourse from '../components/admin/mycourse' 
import mysourse from '../components/admin/mysourse' 
import info from '../components/admin/info' 
import plan from '../components/admin/plan' 
/*import {setCookie,getCookie} from 'src/js'*/
Vue.use(VueRouter)

const routes = [
  {
    path: '/success',
    component: success
  },
  {
    path: '/login',
    component: Login
  },
  {
    path: '/test',
    component: test
  },
  {
    path: '/home',
    component: Home,
    redirect: '/welcome',
    children: [
      { path:'/welcome', component: Welcome},{ path:'/user', component: User},{path:'/rights',component:Rights},{path:'/sport',component:Sport},
      {path:'/goods',component:goods}, {path:'/course',component:course},{path:'/verify',component:verify},{path:'/source',component:source},
      {path:'/mycourse',component:mycourse},{path:'/mysourse',component:mysourse},{path:'/info',component:info}
      ,  {path:'/plan',component:plan}
    ],
  },
]

const router = new VueRouter({
  routes
})
// 挂载路由导航守卫
router.beforeEach((to, from, next) => {
  // to:将要访问的路径
  // from:从哪里访问的路径
  // next:之后要做的任务，是一个函数
  //    next（）放行， next（'/URL'）强制跳转的路径。
  if (to.path == '/login') return next();// 访问路径为登录
  // 获取flag
  const flagStr = window.sessionStorage.getItem("flag");// session取值
  if (!flagStr) return next('/login');// 没登录去登录
  next();
})

export default router// 暴露出去
