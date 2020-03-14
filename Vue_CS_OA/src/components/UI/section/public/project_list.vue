<template>
    <div>
        <el-button type="primary" @click="this.goToCreatProject">NEW</el-button>
        <div id="dataList">
            <el-table :data="this.tableData" style="width:100%"  v-loading="this.tableLoading">
                <el-table-column prop="projectId" label="项目ID" width="100px">
                </el-table-column>

                <el-table-column prop="projectName" label="项目名" width="140px">
                </el-table-column>

                <el-table-column prop="progress" label="进度" width="140px">
                    <!-- <template slot-scope="list">
                        <div>{{list}}</div>
                    </template> -->
                </el-table-column>
                
                <el-table-column prop="startTime" label="开始时间" width="140px">
                </el-table-column>
                <el-table-column prop="targetTime" label="目标时间" width="140px">
                </el-table-column>

                <el-table-column prop="projectState" label="状态" width="140px">
                    <template slot-scope="list">
                        <div v-if="list.row.projectState =='F'" id="finishDIV">已完成</div>
                        <div v-if="list.row.projectState =='N'" id="unFinishDIV"> 未完成</div>
                    </template>
                </el-table-column>

                <el-table-column prop="projectState" label="功能" >
                    <template slot-scope="list">
                        <el-button @click="editProject(list.row.projectId)"  icon="el-icon-edit"></el-button>
                        <el-button @click="previewProject(list.row.projectId)"  >Preview</el-button>
                        <el-button @click="deleteProjectById(list.row.projectId)"  icon="el-icon-delete"></el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>
    </div>
</template>

<script>
import serviceAPI from '@/service/index'

export default {
    name : 'project_list',
    data (){
        return{
            tableLoading: false,
            tableData:[]
        }
    },
    methods :{
        goToCreatProject: function(){
         this.$router.push('editProject/0') 
        },
        getProjectList : function () {
            this.tableLoading = true
            serviceAPI.getProjectList()
            .then( (response) =>{
                if(response != null){
                    const result =response.data
                    if(result.restMessage == 'ok'){
                        this.tableData = result.projectTOList
                        console.log(result)
                    }else{
                        alert('获取项目失败')
                    }
                }
            })
            this.tableLoading = false
        },
        deleteProjectById: function(id){

        },
        editProject: function(projectId){
            this.$router.push('editProject/'+projectId)
        },
        previewProject: function(id){

        }
    },
    mounted(){
        this.getProjectList()
    }

}
</script>

<style  scoped>
#dataList{
    margin-top:5px;
}

#finishDIV{
    width: 70px;  
    height: 30px;
    background-color:blue; 
    text-align:center;
    color:floralwhite;
}

#unFinishDIV{
    width: 70px;
    height: 30px; 
    background-color:red;
    text-align:center;
    color:floralwhite;
}
</style>