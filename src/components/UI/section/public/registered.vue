<template>
    <div id="background">
        <div style="width: 100%; hight:30px;">
            <div id="botton" @click="this.goToHome"><h4>Home</h4></div>
            <div id="botton" @click="this.goToLoginVue"><h4>登录</h4></div>
        </div>
        <div style="padding-top : 10px; text-align:center;">
            <h1>CS-OA注册</h1>
        </div>
        <el-form :model="this.user" ref="user" :rules="userRules">
            <table id="loginTable" border="0" style="margin: auto;" width='50%' cellpadding="3" cellspacing="3" >
                <tr>
                    <td>*工号ID:</td>
                    <td>
                        <el-form-item prop="userId" style="margin: auto;">
                            <el-input v-model.trim="user.userId"></el-input>
                        </el-form-item>
                    </td>
                    <td>*职位:</td>
                    <td>
                        <el-form-item prop="userposition" style="margin: auto;">
                            <el-select v-model="user.userposition" valur-key ="value">
                                <el-option v-for="type in $parent.globalvar.position"
                                :key="type.value"
                                :label="type.label"
                                :value="type.label"       
                                >
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </td>
                </tr>
                <tr>
                    <td>*账号:</td>
                    <td>
                         <el-form-item prop="userName" style="margin: auto;">
                            <el-input v-model.trim="user.userName" type="text"></el-input>
                        </el-form-item>
                    </td>
                    <td>性别:</td>
                    <td>
                        <el-form-item prop="userSex" style="margin: auto;">
                            <el-select v-model="user.userSex" valur-key ="value">
                                <el-option v-for="type in $parent.globalvar.sex"
                                :key="type.value"
                                :label="type.label"
                                :value="type.label"       
                                >
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </td>
                </tr>
                <tr>
                    <td>*密码:</td>
                    <td>
                        <el-form-item  prop="password" style="margin: auto;">
                            <el-input v-model.trim="user.password" show-password></el-input>
                        </el-form-item>
                    </td>
                    <td>*部门:</td>
                    <td>
                        <el-form-item prop="userdepartment" style="margin: auto;">
                            <el-select v-model="user.userdepartment" valur-key ="value">
                                <el-option v-for="type in $parent.globalvar.department"
                                :key="type.value"
                                :label="type.label"
                                :value="type.label"       
                                >
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </td>
                </tr>
                <tr>
                    <td>*重复密码:</td>
                    <td>
                        <el-form-item prop="doublecheckPassword" style="margin: auto;">
                            <el-input v-model.trim="user.doublecheckPassword" show-password></el-input> 
                        </el-form-item>
                    </td>
                    <td>*权限:</td>
                    <td>
                        <el-form-item style="margin: auto;">
                            <el-select v-model="user.permission" valur-key ="value">
                                <el-option v-for="type in $parent.globalvar.permission"
                                :key="type.value"
                                :label="type.label"
                                :value="type.label"       
                                >
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </td>
                </tr>
            </table>
            <div align="center">
                <el-button id="registeredButton"  round @click="this.registeredAction" :loading="this.buttonLogin">注册</el-button>
            </div>        
        </el-form>
    </div>
</template>

<script>
import API from '@/service/index'
import { async } from 'q'

export default {
    name: 'registered',
    data () {
        const  checkName =  (rule,value,callback) => {
                var rgx = /^[A-Z]\w{4,9}$/
                if(value == null || value == ''){
                  return new Error('请必须输入账号名')
                } else{

                    if(!value.match(rgx)){
                        return callback(new Error('请输入首字母大写，长度大于5位小于10位的字符'))
                    }else{
                         this.checkUserNameDouble(value,callback)
                    }
                }
        }
        const DoublecheckPass =(rule, value, callback) => {
            if( this.user.password != null  && this.user.password!='' ){
                if (value != null && value != ''){
                    if(this.user.password == value){
                        callback()
                    }else{
                        return callback(new Error('必须重复密码'))
                    }
                }else{
                    return callback(new Error('请重复密码'))
                }
            }else{
               return callback(new Error('请先填写密码!'))
            }
        }
        return{
            buttonLogin: false,
            user:{
                userId: '',
                userName: '',
                password: '',
                userSex: '',
                userdepartment: '',
                userposition: '',
                permission:'',
                doublecheckPassword: ''
            },
            userRules :{
                userName: [{ required:true , message:'请必须输入账号名' },{validator: checkName ,trigger:'blur'}],
                password: [{ required:true , message:'请必须输入密码', trigger: 'blur' }],
                doublecheckPassword: [{validator: DoublecheckPass,trigger:'blur'}],
                userId: [{ required:true , message:'请必须输入工号', trigger: 'blur' }],
            }
        }
    },
    methods:{
        registeredAction: function (){
            this.$refs.user.validate( (valid)=>{
                if(valid){
                    if(this.user.userdepartment != '' && this.user.userposition != '' && this.user.userSex != '' && this.user.permission != ''
                    && this.user.userdepartment != null && this.user.userposition != null  && this.user.userSex != null && this.user.permission != null){
                        this.buttonLogin = true
                        API.registeredUser(this.user).then( (response) =>{
                            const result = response.data
                            if(response.data === 'error'){
                                return alert('请按资料填写')
                            }else{
                                alert('secces')
                                this.$router.push('/')
                            }
                        })
                         this.buttonLogin = false
                    }else{
                       return alert('请按资料填写')
                    }
                }
            })
        },
        checkUserNameDouble : function(value,callback) {
            if(value != null && value !=""){
                API.checkUserNameDouble(value)
                .then((response)=>{
                    const result = response.data
                    if(result == 'ok'){
                        callback()
                    }else{
                         return callback(new Error('该账号已有人注册，请修改'))
                    }
                })
            }
        },
        goToLoginVue : function (){
            this.$router.push('/')
        },
        goToHome: function (){
            this.$router.push('/home')
        }
    },
    created(){
    }
}
</script>

<style scoped>
#background {
    background-color:palegoldenrod;
    margin: 10px 10px;
    padding-bottom: 10px;
    height: 80%;
}

#botton {
    float: left;
    margin-left: 10px;
    width: 50px;
    height: 20px;
}

#botton :hover{
    background-color:sandybrown;
}
</style>