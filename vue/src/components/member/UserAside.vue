<template>
  <el-row class="tac">
    <el-col :span="150">
      <h2 class="mb-2">个人中心</h2>
      <el-menu
          :default-active="this.$route.path"
          class="el-menu-vertical-demo"
          router
      >
        <el-menu-item index="/userInfo">
          <template #title>
            <el-icon><user /></el-icon>
            <span>修改账户信息</span>
          </template>
        </el-menu-item>
        <el-menu-item index="/notification">
          <template #title>
            <el-icon><message /></el-icon>
          <span>消息中心</span>
          </template>
        </el-menu-item>
        <el-menu-item index="/chat">
          <template #title>
            <el-icon><chat-dot-round /></el-icon>
            <span>聊天记录</span>
          </template>
        </el-menu-item>
      </el-menu>
    </el-col>

  </el-row>
</template>

<script>

/*const handleOpen = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}
const handleClose = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}*/
</script>
