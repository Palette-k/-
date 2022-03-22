<template>
  <h1 style="font-family: 楷体;
                              font-size: x-large;
                              text-align: center">影片分区</h1>
  <div style="text-align: center ;margin: 2px 2px 2px 2px">
    <el-link href="https://element.eleme.io" target="_blank">科幻</el-link>
    <el-link type="primary">动作</el-link>
    <el-link type="success">爱情</el-link>
    <el-link type="warning">悬疑</el-link>
  </div>
  <div
      style="text-align: center">
    <el-link href="https://element.eleme.io" target="_blank">搞笑</el-link>
    <el-link type="primary">文艺</el-link>
    <el-link type="success">青春</el-link>
    <el-link type="warning">{{message}}</el-link>
  </div>
</template>

<script>
export default {
  name: "Fenqu"
}
</script>

<style scoped>

</style>