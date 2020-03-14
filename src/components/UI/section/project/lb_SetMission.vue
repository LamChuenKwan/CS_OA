<template>
    <el-dialog 
    title='Mission From'
    :visible.sync="show"
    width="70%"
    append-to-body
    center>
        <div align="center">
            <div>

            </div>
            <div>

                
            </div>

        </div>
    </el-dialog>
</template>

<script>
export default {
    name: 'setMissionFrom',
    props: ['FromVisible'],
    data(){
        return{
            
        }
    },
    methods:{

    },
    computed:{
         show:{
            get(){
                return this.FromVisible
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

</style>