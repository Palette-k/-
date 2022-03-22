<template>
  <div>
    <el-form status-icon ref="RegisterForm" :model="user" :rules="rules" label-width="80px" class="register-box">
      <h2 class="register-title">注册</h2>
      <el-form-item label="账号申请" prop="account">
        <el-input type="text" placeholder="请输入邮箱/手机号" v-model="user.account"/>
      </el-form-item>
      <el-form-item label="用户名" prop="username">
        <el-input type="text" placeholder="请输入用户名" v-model="user.username"/>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="user.password" type="password" show-password autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="checkPass">
        <el-input v-model="user.checkPass" type="password"  autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item>
        <el-col :span="6"><el-button type="warning" @click="registerSubmit('RegisterForm')" style="width:100%;">注册</el-button></el-col>
          <el-col :span="11" :offset="7">
            <el-link href="/login" target= "_self"
                     type="primary" fontsize="small"  :underline="false">
            已有账号>>请登录
            </el-link>
          </el-col>
      </el-form-item>
    </el-form>

  </div>
</template>

<script>
//这里导入接口？
//import {register} from '@/service/api';
import request from "@/utils/request";

export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "Register",
  data(){
    const validateCheckPass =(rule, value,callback) => {
      if(!value){
        callback(new Error("请再次输入密码"));
      }else if(value != this.user.password) {
        callback(new Error("两次输入的密码不一致"));
      }else {
        callback();
      }
    }
    return{
      user:{
        username:'',
        account: '',
        password:'',
        checkPass:''
      },
      rules:{
        username:[
          {required:true,message:"用户名不可为空",trigger:"blur"}
        ],
        account: [{
          required: true,
          message: '请输入邮箱或手机号',
          trigger: 'blur',
        },
          /*{
            type: 'email',
            message: 'Please input correct email address',
            trigger: ['blur', 'change'],
          },*/
          {
            pattern: /^1[34578]\d{9}|(([a-zA-Z0-9]+[-_.]?)+@[a-zA-Z0-9]+\.[a-z]+)$/ ,
            message: '请输入正确的手机号或邮箱'

          }],
        password:[{
          required:true,
          message: "请输入密码",
          trigger: 'blur'
        },{
          pattern: /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,20}$/, message: '密码必须同时包含数字与字母,且长度为8-20位'
        }],
        checkPass: [{
          validator: validateCheckPass,
          trigger:'blur'
        }],
      }
      }},
      methods: {
    //这里方法要测试改改
        registerSubmit(formName) {
          this.$refs[formName].validate((valid) => {
            if (valid) {
             /*  request.post({
                account:this.user.account,
                name: this.user.name,
                password:this.user.checkPass
              }).then((data)=> {
                console.log(data)
                if(data.code === 200) {
                  localStorage.setItem('token',data.data.token)
                  this.$router.push('/login');
                }else if(data.code === 400){
                  this.$message.error(data.msg) //返回错误信息，即CommonResult里的msg
                }
              })*/
              request.post("/user/register", this.user).then(data => {
                if (data.code === 200) {
                  console.log(data);
                  localStorage.setItem('token', data.data.token)
                  this.$router.push('/login');
                } else {
                  console.log(data);
                  this.$message.error(data.msg) //返回错误信息，即CommonResult里的msg
                }
              })
            } else {
              console.log('error submit!!');
              return false;
            }
          })

        }
      }
    }


</script>

<style scoped>
/*样式还没写*/
.register-box{
  border:1px solid #DCDFE6;
  width: 350px;
  margin:180px auto;
  padding: 35px 35px 15px 35px;
  border-radius: 5px;
  -webkit-border-radius: 5px;
  -moz-border-radius: 5px;
  box-shadow: 0 0 25px #909399;
}
.register-title{
  text-align:center;
  margin: 0 auto 40px auto;
  color: #2955ad;
}


</style>