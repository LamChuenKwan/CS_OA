import {http_api} from '../../../../../util/http-config'

const creatProject = (newproject) => {
   const url = 'project/creatProject'
   return http_api({
       method: 'post',
       url,
       data: newproject
   })
}

const getAlluserList = ()=>{
    const url = 'project/getAllUserList'
    return http_api({
        method: 'get',
        url,
        params: {}
    })    
}

const getUserListByDepartment = (department) =>{
    const url  = 'project/getUserListByDepartment'
    return http_api({
        method: 'get',
        url,
        params:{
            department
        }
    })
}

const getKXUserListByDepartment = (projectId,department) =>{
    const url = '/project/getKXUserListByDepartment'
    return http_api({
        method: 'get',
        url,
        params:{
            projectId,
            department
        }
    })
}

const getYXUserListByProjectId = (projectId) =>{
    const url = '/project/getYXUserListByProjectId'
    return http_api({
        method: 'get',
        url,
        params:{
            projectId
        }
    })
}

const textCreateProject = ( newproject) =>{
    const url = '/project/testCreateProject'
    return http_api({
        method: 'post',
        url,
        data:newproject
    })
}

const getProjectById = (projectId) => {
    const url = '/project/getProjectById'
    return http_api({
        method: 'get',
        url,
        params:{
            projectId
        }
    })
}

const deleteTeamByUserId = (projectId, userId) =>{
    const url = '/project/deleteTeamByUserId'
    return http_api({
        method : 'get',
        url,
        params:{
            projectId,
            userId
        }
    })
}

const insertTeamByUserId = (projectTeamTo) =>{
    const url = '/project/insertTeamByUserId'
    return http_api({
        method:'post',
        url,
        data:projectTeamTo
    })
}

const editProjectById = (projectTO) =>{
    const url = '/project/editProjectById'
    return http_api({
        method: 'post',
        url,
        data: projectTO
    })
}

export default {
    creatProject,
    getAlluserList,
    getUserListByDepartment,
    getKXUserListByDepartment,
    getYXUserListByProjectId,
    textCreateProject,
    getProjectById,
    deleteTeamByUserId,
    insertTeamByUserId,
    editProjectById
}