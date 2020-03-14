import {http_api} from '../../../../../util/http-config'

const getLogin = (user) => {
    const url ='/user/login'
    return http_api({
        method: 'post',
        url,
        data: user
    })
}

const getUserByToken = (token) =>{
    const url='/user/getUserByToken'
    return http_api({
        method: 'get',
        url,
        params:{
            token
        }
    })
}

const testGet = ( name ,pass) =>{
    const url ='/user/testGet'
    return http_api({
        method: 'get',
        url,
        params:{
            name,
            pass
        }
    })
}

export default{
    getLogin,
    testGet,
    getUserByToken
}