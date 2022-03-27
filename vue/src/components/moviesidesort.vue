<template>
    <h1 style="font-family: 楷体;font-size: x-large">今日排行榜</h1>


    <div v-for="sort in message" :key="sort">
        <el-link href="https://element.eleme.io" target="_blank"
                 style="border-bottom:  solid 1px #e6e6e6;
                 margin: 10px 10px 10px 10px" >{{sort}}</el-link>


    </div>
</template>

<script>
    import * as rightside from '@element-plus/icons-vue'
    export default {
        name: "side-sort",
        components:rightside,
        data(){
            return{
                message:["1. xx",'2. xx','3. xxx','4. xxx','5. xxx','6. xx','7. xx','8. xx','9. xxx','10. xx']

            }
        }
    }
</script>

<style scoped>
    .el-link {
        margin-right: 8px;
    }
    .el-link .el-icon--right.el-icon {
        vertical-align: text-bottom;
    }

</style>