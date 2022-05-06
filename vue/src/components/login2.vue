<template>
  <div class="loginDialog">
    <el-dialog
        v-model="dialogVisible"
        width="22%"
    >
      <div style="height: 340px;">
        <login1></login1>
      </div>

    </el-dialog>
  </div>

</template>

<script>
import login1 from "./login1"
export default {
  name: "login2",
  components:{ login1 },
  props:["dialogVisible"],
  computed:{
    dialogVisible() {
      return this.dialogVisible
    }
  },

}
</script>


<style lang="less" scoped>
.loginDialog {
  /deep/ .el-dialog {
    border-radius: 25px;
  }
  /deep/ .el-dialog__wrapper {
    opacity: 0.2;
  }

}

</style>