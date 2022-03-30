<template>
  <div>
    <el-form ref="loginForm" :model="user" :rules="rules" label-width="80px" class="login-box">
      <h2 class="login-title">欢迎登录</h2>
      <el-form-item label="账号" prop="username">
<!--          <span>
            <user theme="outline" size="12" fill="#333" :strokeWidth="3"/>
          </span>-->
        <el-input type="text" placeholder="请输入账号" v-model="user.username"/>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input type="password" placeholder="请输入密码" show-password v-model="user.password"/>
      </el-form-item>

      <el-form-item>
        <el-col :span="12">
          <el-link class="forgett" type="info" fontsize="small"  :underline="false">忘记密码？</el-link>
        </el-col>
        <el-button type="primary" @click="onSubmit('loginForm')">登录</el-button>
        <el-button type="warning" @click="register">注册</el-button>

      </el-form-item>
    </el-form>

    <el-dialog title="温馨提示" v-model:visible="dialogVisiable" width="30%" :before-close="handleClose">
      <span>请输入账号和密码</span>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button id="loginSubmit" type="primary" @click="dialogVisible = false">确定</el-button>
      </span>
      </template>
    </el-dialog>
  </div>
</template>

<script>
import request from "@/utils/request";
import cookie from 'js-cookie';
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "Login",
  data(){
    return{
      user:{},
      //表单验证，需要在 el-form-item 元素中增加prop属性
      rules:{
        username:[
          {required:true,message:"账号不可为空",trigger:"blur"}
        ],
        password:[
          {required:true,message:"密码不可为空",tigger:"blur"}
        ]
      },

      //对话框显示和隐藏
      dialogVisible:false
    }
  },
  methods:{
    onSubmit(formName){
      //为表单绑定验证功能
     this.$refs[formName].validate((valid)=>{
        if(valid){
           request.post("/member/user/login",this.user).then(res=> {
            if (res.code === 200) {
              console.log(res);
              this.setCookies(res.data.username,res.data.token);
              //使用vue-router路由到指定界面，该方式称为编程式导航
              this.$router.push('/layout');
            } else {
              console.log(res);
              this.$message.error("用户名或密码错误")
            }
          })
        }else{ //这里演示不了弹窗效果
          this.dialogVisible=true;
          return false;
        }
      });
    },
    register() {
      this.$router.push('/register');
    },
    setCookies(username, token) {
      cookie.set('token', token, { domain: 'localhost' })
      cookie.set('username', username, { domain: 'localhost' })
      window.location.reload()
    },





  },


}
</script>

<style lang="scss" scoped>
.login-box{
  border:1px solid #DCDFE6;
  width: 350px;
  margin:180px auto;
  padding: 35px 35px 15px 35px;
  border-radius: 5px;
  -webkit-border-radius: 5px;
  -moz-border-radius: 5px;
  box-shadow: 0 0 25px #909399;
}
.login-title{
  text-align:center;
  margin: 0 auto 40px auto;
  color: #2955ad;
}
.el-link {
  font-size: small;
}

</style>
