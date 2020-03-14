<template>
    <el-dialog 
    title='Mission'
    :visible.sync= "show"
    width="80%"
    height="400px"
    center>
        <div align="center">

            <div align="center">
                <el-button id="buttonShow" @click="showSETFrom()">New Mission</el-button>
            </div>

            <div align="center">
                <el-table style="width:100%" :data="this.missionList">
                    <el-table-column prop="missionId" label="编号"></el-table-column>
                    <el-table-column prop="missionTitle" label="标题"></el-table-column>
                    <el-table-column prop="userName" label="负责人"></el-table-column>

                    <el-table-column prop="missionState" label="状态">
                        <template slot-scope="list">
                            <div id="FDiv" v-if="list.row.missionState == 'F'">已完成</div>
                            <div id="NDiv" v-if="list.row.missionState == 'N'">未完成</div>
                        </template>
                    </el-table-column>

                    <el-table-column prop="missionEndTime" label="目标完成时间"></el-table-column>
                    <el-table-column prop="missionId" label="操作"  width="300px">
                        <template slot-scope="list">
                            <el-button calss="buttonTable" @click="showSetMissionFrom(list.row.missionId)">修改</el-button>
                            <el-button calss="buttonTable" @click="showPrivewMission(list.row.missionId)">查看</el-button>
                            <el-button calss="buttonTable" @click="deletecMission(list.row.missionId)">删除</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </div>

            <SetMissionFrom :FromVisible ="this.missionFrom_display" @close="missionFrom_display = false" ></SetMissionFrom>
        </div>
    </el-dialog>
</template>

<script>
import SetMissionFrom from './lb_SetMission'

export default {
    name: 'projectMission',
    props: ['MissionVisible'],
    components: {SetMissionFrom},
    data(){
        return{ 
            missionFrom_display: false,
            missionList:[{missionId: '1',missionTitle:'水泥', userName:'Carl', missionState:'F',missionEndTime:'2019-02-01',rpjectId: 1},
                {missionId: '2',missionTitle:'水泥', userName:'Carl', missionState:'N',missionEndTime:'2019-02-01',rpjectId: 1},
                {missionId: '3',missionTitle:'水泥', userName:'Carl', missionState:'F',missionEndTime:'2019-02-01',rpjectId: 1}],
        }
    },
    methods:{
        showSETFrom: function(){
            this.missionFrom_display = true
        }
    },
    computed:{
         show:{
            get(){
                return this.MissionVisible
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
#buttonShow{
    margin: 10px;
    float: left;
}

#FDiv{
    width: 70px;  
    height: 30px;
    background-color:blue; 
    text-align:center;
    color:floralwhite;
}

#NDiv{
    width: 70px;
    height: 30px; 
    background-color:red;
    text-align:center;
    color:floralwhite;    
}
</style>