import Login_API from '@/components/UI/section/public/api/loginApi'
import Registered_API from '@/components/UI/section/public/api/registeredAPI'
import loginApi from '../components/UI/section/public/api/loginApi'
import ProjectAPI from '@/components/UI/section/project/API/projectAPI'
import PublicOAAPI from '@/components/UI/section/public/api/publicOAAPI'
import projectAPI from '../components/UI/section/project/API/projectAPI'

const getLogin = (user) =>{
    const result = Login_API.getLogin(user)
    return result
}

const testGet = (name, pass) =>{
    const result = Login_API.testGet(name, pass)
    return result
}

const registeredUser = (user) =>{
    const result = Registered_API.registeredUser(user)
    return result
}

const checkUserNameDouble = (userName) => {
    const result = Registered_API.checkUserNameDouble(userName)
    return result
}

const getUserByToken = (token) =>{
    const result = loginApi.getUserByToken(token)
    return result
}

const creatProject = (newproject) => {
    const result = ProjectAPI.creatProject(newproject)
    return result
}

const getProjectList = () =>{
    const result = PublicOAAPI.getProjectList()
    return result
}

const getAlluserList = () =>{
    const result  = ProjectAPI.getAlluserList()
    return result
}

const getUserListByDepartment = (department) => {
    const result = ProjectAPI.getUserListByDepartment(department)
    return result
}

const getKXUserListByDepartment = (projectId,department) =>{
    const result = projectAPI.getKXUserListByDepartment(projectId,department)
    return result
}

const getYXUserListByProjectId = (projectId) =>{
    const result = projectAPI.getYXUserListByProjectId(projectId)
    return result
}

const textCreateProject = (newProject) =>{
    const result = projectAPI.textCreateProject(newProject)
    return result
}

const getProjectById = (projectId) =>{
    const result = projectAPI.getProjectById(projectId)
    return result
}

const deleteTeamByUserId = (projectId, userId) =>{
    const result = projectAPI.deleteTeamByUserId(projectId, userId)
    return result
}

const insertTeamByUserId = (projectTeamTO) =>{
    const result = projectAPI.insertTeamByUserId(projectTeamTO)
    return result
}

const editProjectById = (projectTO) =>{
    const result = projectAPI.editProjectById(projectTO)
    return result
}

export default{
    getLogin : getLogin,
    testGet : testGet,
    registeredUser: registeredUser,
    checkUserNameDouble:checkUserNameDouble,
    getUserByToken: getUserByToken,
    creatProject: creatProject,
    getProjectList: getProjectList,
    getAlluserList: getAlluserList,
    getUserListByDepartment: getUserListByDepartment,
    getKXUserListByDepartment: getKXUserListByDepartment,
    getYXUserListByProjectId: getYXUserListByProjectId,
    textCreateProject: textCreateProject,
    getProjectById: getProjectById,
    deleteTeamByUserId: deleteTeamByUserId,
    insertTeamByUserId: insertTeamByUserId,
    editProjectById: editProjectById
}