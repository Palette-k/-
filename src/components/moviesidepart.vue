<template>
    <h1 style="font-family: 楷体;
                              font-size: x-large;
                              text-align: center">影片分区</h1>
    <div style="text-align: center ;margin: 2px 2px 2px 2px">
        <el-link href="https://element.eleme.io" target="_blank">科幻</el-link>
        <el-link type="primary">动作</el-link>
        <el-link type="success">爱情</el-link>
        <el-link type="warning">悬疑</el-link>
        <el-link type="warning">经典</el-link>

    </div>
    <div
            style="text-align: center">
        <el-link href="https://element.eleme.io" target="_blank">喜剧</el-link>
        <el-link type="primary">文艺</el-link>
        <el-link type="success">青春</el-link>
        <el-link type="warning">{{message}}</el-link>
        <el-link type="warning">犯罪</el-link>
    </div>
    <div style="text-align: center ;margin: 2px 2px 2px 2px">
        <el-link href="https://element.eleme.io" target="_blank">动画</el-link>
        <el-link type="primary">剧情</el-link>
        <el-link type="success">惊悚</el-link>
        <el-link type="warning">家庭</el-link>
        <el-link type="warning">纪录片</el-link>

    </div>
</template>

<script>
    import * as leftside from '@element-plus/icons-vue'
    export default {
        name: "side-part",
        components:leftside,
        data(){
            return{
                message:"励志"
            }
        }
    }
</script>

<style scoped>

</style>