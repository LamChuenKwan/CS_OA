import {http_api} from '../../../../../util/http-config'

const getProjectList = () =>{
    const url = '/project/getPrjectList'
    return http_api({
        method: 'get',
        url,
    })
}

export default{
    getProjectList
}