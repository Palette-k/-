<template>
  <el-menu :default-openeds="['1', '3']" style="min-height: 100%; overflow-x: hidden"
           background-color="rgb(48, 65, 86)"
           text-color="#fff"
           active-text-color="#ffd04b"
           :collapse-transition="false"
           :collapse="isCollapse"
           router
           @select="handleSelect"
  >
    <div style="height: 60px; line-height: 60px; text-align: center">
      <img src="../assets/logo.png" alt="" style="width: 20px; position: relative; top: 5px; right: 5px">
      <b style="color: white" v-show="logoTextShow">后台管理系统</b>
    </div>
    <el-menu-item index="/home">
      <template slot="title">
        <i class="el-icon-house"></i>
        <span slot="title">主页</span>
      </template>
    </el-menu-item>
    <el-submenu index="1">
      <template slot="title">
        <i class="el-icon-message"></i>
        <span slot="title">用户资料</span>
      </template>
      <el-menu-item index="/comment">历史评论</el-menu-item>
      <el-menu-item index="1-2">历史评分</el-menu-item>
      <el-menu-item index="/user">个人资料</el-menu-item>

    </el-submenu>
    <el-submenu index="2">
      <template slot="title">
        <i class="el-icon-menu"></i>
        <span slot="title">电影资料</span>
      </template>
      <el-submenu index="2-4">
        <template slot="title">电影详细信息</template>
        <el-menu-item index="/poster">海报</el-menu-item>
        <el-menu-item index="/movie">详细信息</el-menu-item>
      </el-submenu>
    </el-submenu>
  </el-menu>
</template>

<script>
export default {
  name: "Aside",
  props:{
    isCollapse: Boolean,
    logoTextShow: Boolean,
  },
  methods:{
    handleSelect(index){
      console.log(index)
    }
  }
}
</script>

<style scoped>

</style>