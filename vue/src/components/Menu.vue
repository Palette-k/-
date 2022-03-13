<template>
<!--还在设计ing-->
  <el-menu
      :default-active="activeIndex"
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
  >
    <div class="mingzi">
      <el-image src="src/image/image1.jpg"></el-image>
    </div>
    <el-icon class="is-loading" style="vertical-align: middle;">
      <loading />
    </el-icon>
    <el-col :span="6">
    <el-input class="i-input" placeholder="Please Input" >
      <el-button :icon="Search" circle></el-button>
    </el-input>

    </el-col>
    <el-menu-item index="1">Processing Center</el-menu-item>
    <el-sub-menu index="2">
      <template #title>Workspace</template>
      <el-menu-item index="2-1">item one</el-menu-item>
      <el-menu-item index="2-2">item two</el-menu-item>
      <el-menu-item index="2-3">item three</el-menu-item>
      <el-sub-menu index="2-4">
        <template #title>item four</template>
        <el-menu-item index="2-4-1">item one</el-menu-item>
        <el-menu-item index="2-4-2">item two</el-menu-item>
        <el-menu-item index="2-4-3">item three</el-menu-item>
      </el-sub-menu>
    </el-sub-menu>

    <el-menu-item index="3" >
      <el-link href="/login">登录</el-link>
    </el-menu-item>
    <el-menu-item index="4">Orders</el-menu-item>
  </el-menu>
  <div class="h-6"></div>

</template>


<script>

export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "Menu",
  data() {
    return {
      activeIndex: '1',
      activeIndex2: '1',
    };
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    }
  }
}
</script>

<style>
.i-input {
  margin: 10px auto auto auto;
}
</style>