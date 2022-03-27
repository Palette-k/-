<template>
    <h1 style="font-family: 楷体;
                              font-size: x-large;
                              text-align: center;color: #b88230">音乐分区</h1>
    <div style="margin:0 auto">
        <el-link href="https://element.eleme.io" target="_blank" style="margin: 2px 2px 2px 2px">纯音乐</el-link>
        <el-link type="primary" style="margin: 2px 2px 2px 2px">民谣</el-link>
        <el-link type="success" style="margin: 2px 2px 2px 2px">hip-hop</el-link>
        <el-link type="warning" style="margin: 2px 2px 2px 2px">pop</el-link>
    </div>
    <div
            style="margin: 0 auto">
        <el-link href="https://element.eleme.io" target="_blank" style="margin: 2px 2px 2px 2px">jazz</el-link>
        <el-link type="primary" style="margin: 2px 2px 2px 2px">OST</el-link>
        <el-link type="success" style="margin: 2px 2px 2px 2px">电影原声</el-link>
        <el-link type="warning" style="margin: 2px 2px 2px 2px">古典</el-link>
    </div>
    <div
            style="margin: 0 auto">
        <el-link href="https://element.eleme.io" target="_blank" style="margin: 2px 2px 2px 2px">摇滚</el-link>
        <el-link type="primary" style="margin: 2px 2px 2px 2px">电子</el-link>
        <el-link type="success" style="margin: 2px 2px 2px 2px">newage</el-link>
        <el-link type="warning" style="margin: 2px 2px 2px 2px">Soul</el-link>
    </div>

</template>

<script>
    import * as mleftside from '@element-plus/icons-vue'
    export default {
        name: "musicside-part",
        components:mleftside,
        data(){
            return{
                message:"恐怖"
            }
        }
    }
</script>

<style scoped>

</style>