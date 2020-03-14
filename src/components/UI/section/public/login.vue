<template>
    <div id="background">
        <div style="width: 100%; hight:30px;">
            <!-- <div id="botton" @click="this.goToHome"><h4>Home</h4></div> -->
            <div id="botton" @click="this.goToRegistered"><h4>注册</h4></div>
        </div>
        <div style="padding-top : 10px; text-align:center;" >
            <h1>CS-OA登录</h1>
        </div>
        <el-form :model="this.user" ref="user" :rules="userRules">
            <table id="loginTable" border="0" style="margin: auto;" width='30%' cellpadding="3" cellspacing="3" >
                <tr>
                    <td>账号:</td>
                    <td>
                         <el-form-item prop="userName" style="margin: auto;">
                            <el-input v-model.trim="user.userName" type="text"></el-input>
                        </el-form-item>
                    </td>
                </tr>
                <tr>
                    <td>密码:</td>
                    <td>
                        <el-form-item  prop="password" style="margin: auto;">
                            <el-input v-model.trim="user.password" show-password></el-input>
                        </el-form-item>
                    </td>
                </tr>
            </table>
            <div align="center">
           <el-button id="loginButton" round @click="this.loginAction" :loading="this.buttonLogin">登录</el-button>
           <!-- <el-button id="loginButton" round  :loading="this.buttonLogin">注册</el-button> -->
            </div>
        </el-form>
    </div>
</template>

<script>
import LOGIN_API from '@/service/index'
import publicStore  from '../../../../store/public/publicStore'

export default {
    name: 'login',
    data () {
        const checkName = (rule,value,callback) => {
                var rgx = /^[A-Z]\w{4,9}$/
                if(value == null){
                  return new Error('请输入账号名')
                } else{

                    if(!value.match(rgx)){
                        return callback(new Error('请输入首字母大写，长度大于5位小于10位的字符'))
                    }else{
                        callback()
                    }
                }
            }
        return {
            user:{
                userName:'',
                password:''
            },
            buttonLogin: false,
            userRules: {
                userName: [{ required:true , message:'请输入账号名', trigger:'blur'}],
                password: [{ required:true , message:'请输入密码', trigger: 'blur' }]
            }
        }
    },
    methods :{
        loginAction : function(){

            this.$refs.user.validate( (valid) => {
                if(valid){
                    this.buttonLogin = true
                    LOGIN_API.getLogin( this.user )
                    .then((response) =>{
                        const result = response.data
                        console.log(result)
                        if(result.restMessage === 'ok'){
                            if(result.user != null){
                                sessionStorage.setItem('token',result.user.token)
                                sessionStorage.setItem('userName',result.user.userName)
                                sessionStorage.setItem('permission',result.user.permission)
                                sessionStorage.setItem('userdepartment',result.user.userdepartment)
                                sessionStorage.setItem('userposition',result.user.userposition)
                                this.$router.push('/home')
                            }
                        }else{
                            alert('密码错误请重新输入')
                            this.user.userName =''
                            this.user.password =''
                        }
                    })
                }
                this.buttonLogin = false
            })
            
        },
        goToRegistered : function (){
            this.$router.push('/registered')
        },
        goToHome: function (){
            this.$router.push('/home')
        }
    },
    // mounted (){
    //     this.$store.state.publicStore.user =1
    //     console.log(this.$store.publicStore)
    // }
}
</script>

<style scoped>
#background {
    background-color:palegoldenrod;
    margin: 10px 10px;
    padding-bottom: 10px;
    height: 80%;
}

/* #loginTable {
    
} */

#loginButtonm {
 width: 60px;
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