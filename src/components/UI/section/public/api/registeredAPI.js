import {http_api} from '../../../../../util/http-config'

const registeredUser = (user) => {
    const url = '/OA_registered/registeredUser'
    return http_api({
        method: 'post',
        url,
        data : user
    }) 
}

const checkUserNameDouble = (userName) =>{
    const url = '/OA_registered/checkUserNameDouble'
    return http_api({
        method : 'get',
        url,
        params:{
            userName
        }
    })
}

export default{
    registeredUser,
    checkUserNameDouble
}