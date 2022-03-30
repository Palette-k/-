<template>
    <h1 style="font-family: 楷体;
                              font-size: x-large;
                              text-align: center;color: #3a8ee6">书籍分区</h1>
    <h2 style="text-align: center;font-size: large;color: #85ce61">文学</h2>
    <div style="text-align: center ;margin: 2px 2px 2px 2px">
        <el-link href="https://element.eleme.io" target="_blank"  style="margin: 2px 2px 2px 2px">小说</el-link>
        <el-link type="primary"  style="margin: 2px 2px 2px 2px">随笔</el-link>
        <el-link type="success"  style="margin: 2px 2px 2px 2px">散文</el-link>
        <el-link type="warning"  style="margin: 2px 2px 2px 2px">诗歌</el-link>
    </div>
    <h2 style="text-align: center;font-size: large;color: #b3d8ff">流行</h2>
    <div
            style="text-align: center">
        <el-link href="https://element.eleme.io" target="_blank" style="margin: 0 auto">漫画</el-link>
        <el-link type="primary"  style="margin: 2px 2px 2px 2px">推理</el-link>
        <el-link type="success" style="margin: 2px 2px 2px 2px">青春</el-link>
        <el-link type="warning"  style="margin: 2px 2px 2px 2px">绘本</el-link>
    </div>
    <h2 style="text-align: center;font-size: large ;color: #c45656">文化</h2>
    <div
            style="text-align: center">
        <el-link href="https://element.eleme.io" target="_blank"  style="margin: 2px 2px 2px 2px">历史</el-link>
        <el-link type="primary"  style="margin: 2px 2px 2px 2px">哲学</el-link>
        <el-link type="success"  style="margin: 2px 2px 2px 2px">传记</el-link>
        <el-link type="warning"  style="margin: 2px 2px 2px 2px">电影</el-link>
    </div>


</template>

<script>
    import * as bleftside from '@element-plus/icons-vue'
    export default {
        name: "bside-part",
        components:bleftside,
        data(){
            return{
                message:"恐怖"
            }
        }
    }
</script>

<style scoped>

</style>