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
    <el-menu-item index="/admin/home">
      <template #title>
        <i class="el-icon-house"></i>
        <span>主页</span>
      </template>
    </el-menu-item>
    <el-sub-menu index="1">
      <template #title>
        <el-icon><message /></el-icon>用户资料
      </template>
      <el-menu-item-group>
        <el-menu-item index="/admin/comment">历史评论</el-menu-item>
        <el-menu-item index="1-2">历史评分</el-menu-item>
      </el-menu-item-group>
        <el-menu-item index="/admin/user">个人资料</el-menu-item>
    </el-sub-menu>
    <el-sub-menu index="2">
      <template #title>
        <el-icon><setting /></el-icon>作品资料
      </template>
      <el-menu-item-group>
        <el-menu-item index="/admin/movie">详细信息</el-menu-item>
      </el-menu-item-group>
    </el-sub-menu>
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