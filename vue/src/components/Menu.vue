<template>
  <div>
  <el-menu
      :default-active="activeIndex"
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
      style="">

    <!--logo标志-->
    <el-image style="width: 50px; height: 50px"
              :src="img1"
    ></el-image>

    <el-icon
        class="is-loading"
        style=" vertical-align:baseline ">
      <loading />
    </el-icon>
    <!--输入框-->
    <el-col
        :span="6"
        style="margin-top: 12px;margin-right: 9cm">
      <el-input class="i-input"
                placeholder="Please Input"
      >
        <template #suffix>
          <el-icon style="vertical-align: middle"><Search /></el-icon>
        </template>
      </el-input>
    </el-col>

    <!--menu-->


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

    <el-menu-item index="3">Orders</el-menu-item>
    <el-menu-item>

    </el-menu-item>
    <el-button type="text"
               @click="drawer = true"
               style="margin-top: 10px">
      <el-avatar :icon="UserFilled"
                 :size="small"></el-avatar>
    </el-button>

    <el-drawer v-model="drawer" title="I am the title" :with-header="false">
      <el-container>
        <el-header>
          <div>账号:</div>
        </el-header>
        <el-main>
          <div>性别：</div>
        </el-main>
        <el-main>
          <div>浏览记录：</div>
        </el-main>

      </el-container>
    </el-drawer>

  </el-menu>
  </div>
</template>


<script>
import {Search, UserFilled} from "@element-plus/icons-vue";
import {ref} from "vue";
import * as lay from "@element-plus/icons-vue";
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "Menu",
  components: lay,ref,
  data() {
    return {
      Search,
      UserFilled,
      activeIndex: '1',
      activeIndex2: '1',
      drawer:ref(false),
      img1:require("../assets/logo.png")
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

</style>