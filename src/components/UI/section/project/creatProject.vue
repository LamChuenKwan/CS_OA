<template>
    <div  id="bigDiv">
        <div>
            <el-button id="newButton" @click="this.creatProject" v-show="showNewButton">NEW</el-button>
            <el-button id="newButton" @click="this.eitdProject" v-show="showUpdateButton">EDIT</el-button>
            <el-button id="newButton" @click="this.clerNewProject">CLER</el-button>
            <el-switch
                v-model="state"
                active-color="#13ce66"
                active-text="已完成"
                inactive-color="#ff4949"
                inactive-text="未完成" 
                style="margin-left:5px">
            </el-switch>
              <!-- <span > -->
                  <!-- <h3>项目ID:{{this.newproject.projectId}}</h3> -->
                <!-- </span> -->
        </div> 
        <!-- <div>
                <span >项目ID:{{this.newproject.projectId}}</span>
        </div> -->
        <div>
            <el-form :model="this.newproject" ref="newproject" :rules="validateProject" v-loading="isFromLoading">
                    <table border="0" id="dataTable" cellpadding="3" cellspacing="3">
                        <tr>
                            <td align="center"><h2>项目ID:{{this.newproject.projectId}}</h2></td>
                            <td align="center"><h2>完成:{{this.newproject.progress}}</h2></td>
                        </tr>
                            <tr>
                                <td>
                                    <table border="0" style="margin: auto;" cellpadding="3" cellspacing="3">
                                        <tr style="height: 10px">
                                            <td text-align="center">项目名:</td>
                                            <td>
                                                <el-form-item prop="projectName" style="margin: auto;">
                                                    <el-input v-model="newproject.projectName"></el-input>
                                                </el-form-item>
                                            </td>
                                        </tr>
                                        <tr>   
                                            <td>开始时间：</td>
                                            <td >
                                                <el-form-item prop="startTime" style="margin: auto;">
                                                    <el-date-picker 
                                                        v-model="newproject.startTime" 
                                                        type="date" 
                                                        placeholder="选择时间"
                                                        value-format="yyyy-MM-dd"
                                                        format="yyyy-MM-dd">
                                                    </el-date-picker>
                                                </el-form-item>
                                            </td>
                                        </tr>
                                         <tr>
                                            <td>目标时间：</td>
                                            <td>
                                                <el-form-item prop="targetTime" style="margin: auto;">
                                                    <el-date-picker 
                                                        v-model="newproject.targetTime" 
                                                        type="date" 
                                                        placeholder="选择时间"
                                                        value-format="yyyy-MM-dd"
                                                        format="yyyy-MM-dd">
                                                    </el-date-picker>
                                                </el-form-item>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td> 
                                                <el-button @click="showProjectTeam()"  :disabled="!this.showProjectTeamButton">组员设置</el-button> 
                                            </td>
                                            <td>
                                                <el-button  :disabled="!this.showMission" @click="showProjectMission()">任务分配</el-button>
                                            </td>
                                        </tr>
                                        <tr>
                                            <!-- <td colspan="2">
                                                <el-upload
                                                 class="upload-demo"
                                                 ref="upload"
                                                 action="doUpload"
                                                 :limit="1"
                                                 :file-list="fileList"
                                                 :before-upload="beforeUpload">
                                                    <el-button slot="trigger" size="small" type="primary">选取文件</el-button> -->
                                                    <!--  <el-button href="./static/moban.xlsx" rel="external nofollow" download="模板"><el-button size="small" type="success">下载模板</el-button></el-button> -->
                                                     <!-- <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传到服务器</el-button> -->
                                                <!--     <div slot="tip" class="el-upload__tip">只能上传excel文件，且不超过5MB</div> -->
                                                <!--     <div slot="tip" class="el-upload-list__item-name">{{fileName}}</div> -->
                                                <!-- <el-input></el-input> -->
                                                <!-- </el-upload>  -->
                                                 <!-- <span slot="footer" class="dialog-footer"> -->
                                                    <!--     <el-button @click="visible = false">取消</el-button> -->
                                                    <!--     <el-button type="primary" @click="submitUpload()">确定</el-button> -->
                                                    <!-- </span> -->
                                            <!-- </td> -->
                                                <!-- <td>
                                                    <span slot="footer" class="dialog-footer">
                                                        <el-button @click="visible = false">取消</el-button>
                                                        <el-button type="primary" @click="submitUpload()">确定</el-button>
                                                    </span>
                                                </td> -->
                                           
                                        </tr>
                                    </table>
                                </td>
                                <td style="width:60%">
                                    <el-upload
                                        class="upload-demo"
                                        ref="upload"
                                        :action="this.doUploadURL"
                                        :limit="1"
                                        :file-list="fileList"
                                        :before-upload="beforeUpload" 
                                        >
                                            <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                                            <!--  <el-button href="./static/moban.xlsx" rel="external nofollow" download="模板"><el-button size="small" type="success">下载模板</el-button></el-button> -->
                                            <!-- <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传到服务器</el-button> -->
                                            <div slot="tip" class="el-upload__tip">只能上传excel文件，且不超过5MB</div>
                                            <div slot="tip" class="el-upload-list__item-name">{{this.fileName}}</div>
                                    </el-upload> 
                                    <el-button @click="visible = false">取消</el-button>
                                    <el-button type="primary" @click="submitUpload()">确定</el-button>

                                    <el-form-item prop="descript" >
                                        <quill-editor v-model="newproject.descript" style="height :200px" ></quill-editor>
                                    </el-form-item>
                                </td>
                            </tr>
                    </table>
            </el-form>
        </div>
        <projectTeam :visible="this.lb_projectTeam_display" :projectPost="projectPost" @close="lb_projectTeam_display = false" />
        <projectMission :MissionVisible="this.lb_projectMission_display" @close="lb_projectMission_display = false"></projectMission>
    </div>
</template>


<script>
import projectAPI from '@/service/index'
import projectTeam from './lb_projectTeam'
import projectMission from './lb_projectMission'

export default {
    name: 'creatProject',
    components:{ projectTeam ,projectMission},
    data(){
        return{
            projectPost: {
                projectId:''
            },
            lb_projectTeam_display: false,
            lb_projectMission_display: false,
            testtime:null,
            state: false,
            file: null,
            fileName: '',
            fileList:[],
            doUploadURL: 'http://localhost:18080//project',
            newproject:{
                projectId:'',
                projectName: '',
                startTime: null,
                targetTime: null,
                descript: '',
                projectState:'',
                progress:''
            },
            validateProject: {
                projectName:[{ required: true, message:'不能为空', trigger: 'blur' }],
                startTime:[{ required:true, message:'不能为空', trigger: 'change' }],
                targetTime:[{ required:true, message:'不能为空', trigger: 'change' }],
                descript: [{ required:true, message:'不能为空', trigger: 'blur'  }]
            },

            showMission: false,
            showNewButton: false,
            showUpdateButton: false,
            showProjectTeamButton: false,
            isFromLoading: false
        }
    },
    methods:{
        creatProject: function(){
            if(this.state){
                this.newproject.projectState = 'F'
            }else{
                this.newproject.projectState = 'N'
            }
            this.$refs.newproject.validate( (valid)=>{
                if(valid){
                    this.isFromLoading = true
                    projectAPI.creatProject(this.newproject).then( (response)=>{
                        const result = response.data
                        if(result.restMessage == 'ok'){
                            alert('创建成功')
                        }
                    })
                }else{
                    return alert('按要求填写')
                }
                isFromLoading = false
            })
        },
        eitdProject: function (){
            if(this.state){
                this.newproject.projectState = 'F'
            }else{
                this.newproject.projectState = 'N'
            }
            this.$refs.newproject.validate((valid)=>{
                if(valid){
                    this.isFromLoading = true
                    console.log(this.newproject)
                    projectAPI.editProjectById(this.newproject).then((response)=>{
                        const result = response.data
                        if(result.restMessage == 'ok'){
                            if(result.projectId == this.newproject.projectId){
                                this.getProjectById(this.newproject.projectId)
                            }
                            alert('修改成功')
                        }
                    })
                }else{
                    return alert('按要求填写')
                }
                this.isFromLoading = false
            })
        },
        getProjectById: function (id){
            this.isFromLoading = true
            projectAPI.getProjectById(id).then( (response)=>{
                const result = response.data
                if(result.restMessage == 'ok'){
                    this.newproject.projectId = result.projectTO.projectId
                    this.newproject.projectName = result.projectTO.projectName
                    this.newproject.startTime = result.projectTO.startTime
                    this.newproject.targetTime = result.projectTO.targetTime
                    this.newproject.descript = result.projectTO.descript
                    this.newproject.projectState = result.projectTO.projectState
                    this.newproject.progress = result.projectTO.progress
                    if( this.newproject.projectState == 'F'){
                        this.state = true
                    }else if(this.newproject.projectState == 'N'){
                        this.state = false
                    }

                }
            })
            this.isFromLoading = false
        },
        showProjectTeam : function(){
            this.lb_projectTeam_display = true
        },
        textCreateProject: function (){
            if(this.state){
                this.newproject.projectState = 'F'
            }else{
                this.newproject.projectState = 'N'
            }
            this.$refs.newproject.validate( (valid)=>{
                if(valid){
                    projectAPI.textCreateProject(this.newproject).then( (response)=>{
                        const result = response.data
                        if(result.restMessage == 'ok'){
                             console.log(result.projectId)
                        }else{
                            console.log(result.restMessage)
                        }
                    })
                }
            })
        },
        init : function (){
            let id = this.$route.params.projectId
            if(id == 0){
                this.showNewButton = true
                this.showUpdateButton =false
                this.showProjectTeamButton = false
                this.showMission = false
            }
            if(id > 0){
                this.showNewButton = false
                this.showUpdateButton = true
                this.showProjectTeamButton =true
                this.showMission = true
                this.projectPost.projectId = id
                this.getProjectById(id)
            }
        },
        clerNewProject : function(){
            this.newproject.projectName = ''
            this.newproject.startTime = ''
            this.newproject.targetTime = ''
            this.newproject.descript = ''
        },
        showProjectMission: function(){
            this.lb_projectMission_display = true
        },
        beforeUpload : function (file){
            this.file = file
            const extent = file.name.split('.')[1] === 'xls'
            const extent2 = file.name.split('.')[1] === 'txt'
            const isLt2M = file.size / 1024 / 1024 < 5
            if(!extent && !extent2){
                this.$message.warning('上传模板只能是 xls、xlsx格式!')
                return 
            }
            if (!isLt2M) {
                this.$message.warning('上传模板大小不能超过 5MB!')
                return
            }
            this.fileName = file.name
            return false // 返回false不会自动上传
        },
        submitUpload: function (){
            
        }
    },
    created (){
        this.init()
    },
    mounted () {
     
    }
}
</script>

<style scoped>
#newButton{
    width: 90px;
    height: 40px;
    background-color: cornsilk;
    margin:5px;
}
#datatable{
     table-layout:fixed;
     height: 500px;
     width:100%；
}
#bigDiv{
    height: 570px;
}
</style>