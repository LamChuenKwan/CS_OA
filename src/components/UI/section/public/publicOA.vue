<template>
    <div id="bigDiv">
        <el-container>
            <!-- <el-header>header</el-header> -->
            <el-container style="height:500px; border:1px solid #eee">
                <el-aside width="15%" style="background-color:white">
                   <el-menu :default-openeds="['1','2']">
                       <el-submenu index="1" style="border:1px solid #eee">
                           <template slot="title"><i class="el-icon-menu"></i>综合</template>
                           <el-menu-item-group >
                                <el-menu-item index="1-1" @click="this.openProject" >项目</el-menu-item>
                                <el-menu-item index="1-2" >个人任务</el-menu-item>
                                <el-menu-item index="1-3">部门情况</el-menu-item>
                           </el-menu-item-group>
                       </el-submenu>
                      <el-submenu index="2">
                           <template slot="title"><i class="el-icon-menu"></i>简讯</template>
                           <el-menu-item-group>
                                <el-menu-item index="2-1">公告</el-menu-item>
                                <el-menu-item index="2-2">留言</el-menu-item>
                                <el-menu-item index="2-3">备忘</el-menu-item>
                           </el-menu-item-group>
                       </el-submenu>
                   </el-menu>
                </el-aside>
                <el-main>
                    <projectList></projectList>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
import projectList from './project_list'
export default {
     name:'publicOA', 
     components: {projectList},
     data () {
         return {
              user: [
                  {userName : 'carl'},
                  {userName : 'Alex'}
                  ]
         }
     },
     methods :{
         openProject : function (){
             alert('ok')
         }
     }
}
</script>

<style scoped>
#bigDiv{
    margin: 5px;
    background-color:beige;
}
</style>