<template>
        <el-dialog 
        title='Project Team' 
        :visible.sync="show" 
        width="70%"
         center>
            <div align="center">
                <div align="center" id = "tiltDiv"><h3>已选</h3></div>
                <div align="center" >
                    <el-table style="width:100%" :data="this.YXUserListData" v-loading="isTableLoading">
                        <el-table-column prop="userId" label="工号"> </el-table-column>
                        <el-table-column prop="userName" label="名字"></el-table-column>
                        <el-table-column prop="userSex" label="性别"></el-table-column>
                        <el-table-column prop="userdepartment" label="部门"></el-table-column>
                        <el-table-column prop="userposition" label="职位"></el-table-column>
                        <el-table-column prop="userId" label="操作">
                            <template slot-scope="list">
                                <el-button @click="deleteTeamByUserId(list.row.userId)">删除</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </div>

                <div align="center" id = "tiltDiv"> <h3>可选</h3></div>

                <div align="center">
                    <el-table  :data="this.KXUserListData"  >
                        <el-table-column prop="userId" label="工号">
                            <template slot-scope="list">
                                <el-checkbox v-model="KXUserList" :key="list.row.userId" :label="list.row.userId" ></el-checkbox>
                            </template>   
                        </el-table-column>
                        <el-table-column prop="userName" label="名字"></el-table-column>
                        <el-table-column prop="userSex" label="性别"></el-table-column>
                        <el-table-column prop="userdepartment" label="部门"></el-table-column>
                        <el-table-column prop="userposition" label="职位"></el-table-column>
                    </el-table>
                    <!-- <el-transfer
                        style="text-align:left; display: inline-block"
                        v-model="checklike"
                        filterable
                        :title="['开发部人选','项目进组']"
                        :button-texts="['到左边','到右边']"
                        :data="userlistData"
                    > -->
                    <!-- </el-transfer> -->
                </div>
                <div id="buttonId" align="center">
                    <el-button @click="this.setProjectTeam">OK</el-button>
                </div>
            </div>
        </el-dialog>
</template>

<script>

import service from '@/service/index'
export default {
    name: 'lb_projectTeam',
    props:['visible','projectPost'],
    data(){
        return {
            projectId:'',
            KXUserListData : [],
            YXUserListData:[],
            projectMembersTO:{
                projectId: '',
                userList:[],
            },
            KXUserList:[],
            isTableLoading: false
        }
    },
    methods:{
        init: function (){

        },
        clear: function (){

        },
        setProjectTeam: function(){
            // this.projectMembersTO.userList = JSON.stringify(this.KXUserList)  这里将字符串转换为json对象
            this.projectMembersTO.projectId = this.projectId
            this.projectMembersTO.userList=this.KXUserList
            console.log(this.projectMembersTO)
            service.insertTeamByUserId(this.projectMembersTO).then( (response)=>{
                    //    console.log(response)
                const result = response.data
                if(result.restMessage == 'ok'){
                    this.getYXUserListByProjectId(this.projectId)
                    this.getKXUserListByDepartment(this.projectId,'开发部')
                }
            })
        },
        getKXUserListByDepartment : function (projectId,department){
            JSON.stringify
            service.getKXUserListByDepartment(projectId,department).then( (response) =>{
                const result = response.data
                if(result.restMessage == 'ok'){
                    this.KXUserListData = result.userTOList

                }
            })
        },
        getYXUserListByProjectId : function(projectId){
            service.getYXUserListByProjectId(projectId).then( (response) =>{
                const result = response.data
                if(result.restMessage == 'ok'){
                    this.YXUserListData = result.userTOList
                }
            })
        },
        deleteTeamByUserId: function(userId){
            if(this.projectId != ''){
                this.isTableLoading = true
                service.deleteTeamByUserId(this.projectId,userId).then( (response)=>{
                     const result = response.data
                     if(result.restMessage == 'ok'){
                        this.getYXUserListByProjectId(this.projectId)     
                        this.getKXUserListByDepartment(this.projectId,'开发部')    
                     }
                })
            }
            this.isTableLoading = false
        }

    },
    mounted (){
        if(this.projectPost.projectId != ''){
            this.getKXUserListByDepartment(this.projectPost.projectId,'开发部')
            this.getYXUserListByProjectId(this.projectPost.projectId)
            this.projectId =  this.projectPost.projectId
        }
    },
    computed:{
        show:{
            get(){
                return this.visible
            },
            set(value){
                if(!value){
                    this.$emit('close')
                }
            }
        }
    }
}
</script>

<style scoped>
#buttonId{
    margin: 10px;
    
}

#tiltDiv{
    margin: 10px;
    width: 100%;
    height: 30px;
}
</style>