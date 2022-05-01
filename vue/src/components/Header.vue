<template>
  <div style="font-size: 12px;line-height: 60px; display: flex">
    <div style="flex: 1; ">
      <span :class="collapseBtnClass" style="cursor: pointer;font-size:18px" @click="collapse"></span>
      <el-breadcrumb separator="/" style="display: inline-block;margin-left:10px">
        <el-breadcrumb-item :to="item.path" v-for="item in paths" :key="item.name">{{item.name}}</el-breadcrumb-item>
        <el-breadcrumb-item>用户资料-历史评论</el-breadcrumb-item>
      </el-breadcrumb>
    </div>

    <el-dropdown style="width: 70px; cursor: pointer">
      <span>管理员</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
      <template #dropdown>
      <el-dropdown-menu slot="dropdown" >
        <el-dropdown-item style="font-size: 14px; padding: 5px 0">个人信息</el-dropdown-item>
        <el-dropdown-item style="font-size: 14px; padding: 5px 0">退出</el-dropdown-item>
      </el-dropdown-menu>
      </template>
    </el-dropdown>

  </div>
</template>

<script>
export default {
  name: "Header",
  props:{
    collapseBtnClass: String,
    collapse: Boolean,
  },
  data(){
    return{
      paths:[]
    }
  },
  create(){
    console.log(this.$route)
  }

}
</script>

<style scoped>

</style>