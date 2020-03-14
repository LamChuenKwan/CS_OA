import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/UI/Home/home'
import Login from '@/components/UI/section/public/login'
import Registered from '@/components/UI/section/public/registered'
import PublicOA from '@/components/UI/section/public/publicOA'
import CreatProject from '@/components/UI/section/project/creatProject'

Vue.use(Router)

const router = new Router({
  mode: 'history',   // 去除路由中的 # 号
  routes: [
    {
      path: '/home',
      name: 'home',
      // meta :{
      //   requireAuth : true
      // },
      component: Home,
      children:[
        {
          path:'/home',
          redirect : '/home/index'
        },
        {
          path:'index',
          name:'publicOA',
          component:PublicOA,
        },
        // {
        //   path:'newProject',
        //   name:'creatProject',
        //   component:CreatProject,
        //   mata:{
        //     requireAuth : true,
        //   }
        // },
        {
          path:'editProject/:projectId',
          name:'creatProject',
          component:CreatProject,
          mata:{
            requireAuth : true,
          }
        }
      ]
    },
    {
      path: '/',
      name: 'login',
      component: Login
    },
    {
      path:'/registered',
      name:'registered',
      component: Registered
    },
  ]
})


// 导航守卫
// 参数1 : to 目标路由对象
// 参数2 : from 来源路由对象
// 参数3 : next() 下一步
router.beforeEach((to, from, next)=>{
  if(to.meta.requireAuth){  // 判断该路由是否需要登录权限
    let token = sessionStorage.getItem('token')
    if(token){   // 通过localStorage.getItem()获取当前的 token 是否存在
        next()
    }else{
      next({
        path: '/',
        query:{
          redirect: to.fullPath  // 将跳转的路由path作为参数，登录成功后跳转到该路由
        }
      })
    }
  }else{
    next()
  }
})

export default router