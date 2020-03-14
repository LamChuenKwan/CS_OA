import axios from 'axios'

export const http_api = axios.create({
    baseURL: '/apis'
})

//response 拦截器
http_api.interceptors.response.use(
    function (response){
        //对响应数据做点什么
        return response
    },
    function (error){
        //出错的时候做点什么
        if(error.response){
            switch(error.response.status){
                case 401:{
                    // 401 清除 token 信息并跳转到登录页面
                    localStorage.removeItem('token')   //  清除本地的token
                    // 只有在当前路由不是登录页面才跳转
                    router.currentRoute.path !== '/' &&
                    router.replace({
                        path: '/',
                        query: {
                        redirect: router.currentRoute.path
                        }
                    })
                }
            }
        }
        return Promise.reject(error)
    }
)

// rquest 拦截器
http_api.interceptors.request.use(
    function(config){
        //对请求前做点什么
        if(localStorage.getItem('token')){
            config.headers.Authorization = localStorage.getItem('token')
        }
        return config
    },
    function(error){
        //出错的时候做点什么
        return Promise.reject(error)
    }
)