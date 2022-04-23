<template>
 <div style="font-size: 12px;line-height: 60px; display: flex">
   <div style="flex: 1; ">
     <span :class="collapseBtnClass" style="cursor: pointer;font-size:18px" @click="collapse"></span>
       <el-breadcrumb separator="/" style="display: inline-block;margin-left:10px">
         <el-breadcrumb-item :to="{path:'item.path'}" v-for="item in paths" :key="item.name">{{item.name}}</el-breadcrumb-item>
         <el-breadcrumb-item>用户资料-历史评论</el-breadcrumb-item>
       </el-breadcrumb>
   </div>

   <el-header style="text-align: right; font-size: 12px">
     <div class="toolbar">
       <el-dropdown>
         <el-icon style="margin-right: 8px; margin-top: 23px"
         ><setting
         /></el-icon>
         <template #dropdown>
           <el-dropdown-menu>
             <el-dropdown-item>退出</el-dropdown-item>
           </el-dropdown-menu>
         </template>
       </el-dropdown>
       <span>管理员</span>
     </div>
   </el-header>
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