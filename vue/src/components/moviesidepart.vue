<template>
    <h1 style="font-family: 楷体;
                              font-size: x-large;
                              text-align: center;
                              color: #c45656">影片分区</h1>
    <div style="text-align: center ;margin: 10px 0 10px 0">
        <el-link href="https://element.eleme.io" target="_blank"
                 style="margin: 2px 2px 2px 2px">科幻</el-link>
        <el-link type="primary"
                 style="margin: 2px 2px 2px 2px">动作</el-link>
        <el-link type="success"
                 style="margin: 2px 2px 2px 2px">爱情</el-link>
        <el-link type="warning"
                 style="margin: 2px 2px 2px 2px">悬疑</el-link>
        <el-link type="warning"
                 style="margin: 2px 2px 2px 2px">经典</el-link>

    </div>
    <div
            style="text-align: center;margin: 10px 0 10px 0">
        <el-link href="https://element.eleme.io" target="_blank"
                 style="margin: 2px 2px 2px 2px">喜剧</el-link>
        <el-link type="primary"
                 style="margin: 2px 2px 2px 2px">文艺</el-link>
        <el-link type="success"
                 style="margin: 2px 2px 2px 2px">青春</el-link>
        <el-link type="warning"
                 style="margin: 2px 2px 2px 2px">{{message}}</el-link>
        <el-link type="warning"
                 style="margin: 2px 2px 2px 2px">犯罪</el-link>
    </div>
    <div style="text-align: center ;margin: 10px 0 10px 0">
        <el-link href="https://element.eleme.io" target="_blank"
        style="margin: 2px 2px 2px 2px">动画</el-link>
        <el-link type="primary"
                 style="margin: 2px 2px 2px 2px">剧情</el-link>
        <el-link type="success"
                 style="margin: 2px 2px 2px 2px">惊悚</el-link>
        <el-link type="warning"
                 style="margin: 2px 2px 2px 2px">家庭</el-link>
        <el-link type="warning"
                 style="margin: 2px 2px 2px 2px">纪录片</el-link>

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
* {


  color: var(--el-text-color-primary);
  background: #fff !important;

  box-sizing: border-box;
  text-align: center;
}
</style>