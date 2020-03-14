<template>
    <div>
        <div id="headerId">
            <!-- <div id="botton" @click="this.goTologin"><h4>登录</h4></div> -->
            <!-- <div id="botton" @click="this.goToRegistered"><h4>注册</h4></div> -->
            <div id="nameDiv" > <h4  v-show="this.showName">{{this.userName}}</h4></div>
        </div>
    </div>
</template>

<script>
import publicStore from '@/store/public/publicStore'

export default {
    name: 'baseheader',
    data(){
        return {
            show:'show',
            userName: '',
            showName: false
        }
    },
    methods:{
        goToRegistered : function (){
            this.$router.push('/registered')
        },
        goTologin: function (){
            this.$router.push('/')
        }
    },
    mounted (){
        //   console.log('------------------------localStorage-----------------------------')
        //   console.log(localStorage.getItem('token'))
        //   console.log('------------------------sessionStorage-----------------------------')
        //   console.log(sessionStorage.getItem('token'))
        if(sessionStorage.getItem('token')){
            this.userName = '用户：'+sessionStorage.getItem('userName')
            this.showName = true
        }else{
           this.showName = false
        }
    }
}
</script>

<style scoped>

#headerId {
    background-color:dodgerblue;
    margin: 5px;
    width:100%;
    height: 50px;
}

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
   border: 1px solid white
}

#nameDiv {
    float: left;
    margin-left: 10px;
    width: 200px;
    height: 20px;
}

</style>
