import  service_API from '@/service/index'

export const publicStore={
    state:{
    //   user:{
    //       userId:'',
    //       userName: '',
    //       userdepartment: '',
    //       userposition:'',
    //       permission:''
    //   }
    user:{}

    },
    mutations:{
        setUser(state,user){
            state.user = user
            // state.user.userName = user.userName
            // state.user.userdepartment = user.userdepartment
            // state.user.userposition = user.userposition
            // state.user.permission = user.permission
        }
    },
    getters:{
        getUser:state =>state.user
    },
    antion:{
        setStoreUser:({commit})=>{
            let token = localStorage.getItem('token')
            console.log('setStoreUser:'+token)
            if(token !=nul && token != ''){
                // let arry = token.split('|')
                // console.log(arry)
                // if(arry.length >0){
                //     let id = arry[0]
                //     let name = arry[1]
                //     let permission = arry[2]
                    service_API.getUserByToken(token)
                    .then((response)=>{
                         const result = response.date 
                         console.log(result+'--------------------------result')
                         const statuCode = result.restMessage
                         if(statuCode == 'ok'){
                            commit('setUser',result.user)
                         }else{
                             throw statuCode
                         }
                    })
                // }
            }

        }
    }
}