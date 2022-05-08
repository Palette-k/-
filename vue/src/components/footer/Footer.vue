<template>
  <div class="footer">
    <div class="c" v-for="(item,index) in list" :index="index">
      <el-divider direction="vertical" border-style="dashed" />
      <a>{{item}}</a>
      <el-divider direction="vertical" border-style="dashed" />
    </div>
  </div>
</template>

<script>
export default {
  name: "Footer",
  data() {
    return {
      list: ["关于我们","项目代码","意见反馈","帮助中心"]
    }
  }
}
</script>

<style lang="less" scoped>
.footer {
  width: 100%;
  color: #eeeff3;
  background-color: #7058a3;
  opacity: 50%;
  text-align: center;
  .c {
    padding: 20px 10px;
    text-align: center;
    border-right: solid 1px var(--el-border-color);
    display: inline-block;
    width: 20%;
    box-sizing: border-box;
    vertical-align: top;
    a:hover {
      color: black;
      cursor:pointer;
    }
  }
}
</style>