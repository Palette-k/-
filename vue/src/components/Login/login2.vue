<template>
  <div class="loginDialog" >
    <el-dialog
        v-model="Visible"
        width="25%"
    >
        <div class="bg" style="height: 360px;padding: 20px 5px 20px 20px;">
          <el-tabs id="box">
            <el-tab-pane label="密码登录">
              <el-form ref="loginForm" :model="user1" :rules="rules1" label-width="60px" class="login-box">
                <el-form-item label="账号" prop="username">
                  <el-input type="text" placeholder="请输入账号" v-model="user1.username"/>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                  <el-input type="password" placeholder="请输入密码" show-password v-model="user1.password"/>
                </el-form-item>

                <el-button  class="bt" @click="onSubmit('loginForm')">登录</el-button>
                <el-link class="forgett" type="info" fontsize="small"  :underline="false">忘记密码？</el-link>
              </el-form>



            </el-tab-pane>
            <el-tab-pane label="注册">
              <el-form status-icon ref="RegisterForm" :model="user2" :rules="rules2" label-width="80px" class="register-box">
                <el-form-item label="账号申请" prop="account">
                  <el-input type="text" placeholder="请输入邮箱/手机号" v-model="user2.account"/>
                </el-form-item>
                <el-form-item label="用户名" prop="username">
                  <el-input type="text" placeholder="请输入用户名" v-model="user2.username"/>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                  <el-input v-model="user2.password" type="password" show-password autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="确认密码" prop="checkPass">
                  <el-input v-model="user2.checkPass" type="password"  autocomplete="off"></el-input>
                </el-form-item>

                <el-button class="bt" @click="registerSubmit('RegisterForm')" >注册</el-button>

              </el-form>

            </el-tab-pane>
          </el-tabs>


          <!--    <el-dialog title="温馨提示" v-model:visible="dialogVisiable" width="30%" :before-close="handleClose">
                <span>请输入账号和密码</span>
                <template #footer>
                <span class="dialog-footer">
                  <el-button @click="dialogVisible = false">取消</el-button>
                  <el-button id="loginSubmit" type="primary" @click="dialogVisible = false">确定</el-button>
                </span>
                </template>
              </el-dialog>-->
        </div>


    </el-dialog>
  </div>

</template>

<script>
import request from "@/utils/request";
import cookie from "js-cookie";
export default {
  name: "login2",
  props:["dialogVisible"],
  emits:['update:dialogVisible'],
  computed:{
    Visible() {
      return this.dialogVisible
    }
  },
  data(){
    const validateCheckPass =(rule, value,callback) => {
      if(!value){
        callback(new Error("请再次输入密码"));
      }else if(value !== this.user2.password) {
        callback(new Error("两次输入的密码不一致"));
      }else {
        callback();
      }
    }
    return{
      user1:{},
      //表单验证，需要在 el-form-item 元素中增加prop属性
      rules1:{
        username:[
          {required:true,message:"账号不可为空",trigger:"blur"}
        ],
        password:[
          {required:true,message:"密码不可为空",trigger:"blur"}
        ]
      },
      user2:{
        username:'',
        account: '',
        password:'',
        checkPass:''
      },
      rules2:{
        username:[
          {required:true,message:"用户名不可为空",trigger:"blur"}
        ],
        account: [{
          required: true,
          message: '请输入邮箱或手机号',
          trigger: 'blur',
        },
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
      },

      //对话框显示和隐藏
      /*dialogVisible:false*/
    }
  },
  methods:{
    onSubmit(formName){
      //为表单绑定验证功能
      this.$refs[formName].validate((valid)=>{
        if(valid){
          console.log(this.user1)
          request.post("/member/user/login",this.user1).then(res=> {
            if (res.code === 200) {
              console.log(res);

              let tokenData = res.data.token;
              this.$store.commit('setToken',tokenData);
              // this.$store.commit('setID',res.data.username);
              this.setCookies(res.data.username);
              this.getID(cookie.get('username'))
              window.location.reload()
             /* if(res.data.admin === 1){
                this.$router.push('/admin');
              }else{
                //使用vue-router路由到指定界面，该方式称为编程式导航
                this.$router.push('/layout');
              }*/
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
          request.post("/member/user/register", this.user2).then(data => {
            if (data.code === 200) {
              console.log(data);
              localStorage.setItem('token', data.data.token)
              this.$message.success("注册成功")
              window.location.reload()
              this.user1.username = data.data.username
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

    },
    setCookies(username) {
      cookie.set('username', username)
      // window.location.reload()
    },
    //获取id
    getID(name) {
      console.log(name)
      request.get("/member/user/finduserId?username=" + name).then(res => {
        this.$store.commit('setID',res.data)
      })
    },

  }


}
</script>


<style lang="less" scoped>
.loginDialog {
  display: flex;

  /deep/.el-dialog__header{
    display: none;
  }
  /deep/ .el-dialog__footer {
    display: none;
  }

  /deep/ .el-dialog {
    border-radius: 25px;
  }
  /deep/ .el-dialog__wrapper {
    opacity: 0.2;
  }

}
@width:300px;
@height:380px;
@color:#7058a3;

// #b0a4e3;
#box {
  display: flex;
  width: @width;
  height: @height;
  //border: #DCDFE6 solid 1px;
  //margin: 150px 250px 150px auto;
  flex-direction: column;
  justify-content: flex-start;
  border-radius: 5px;
  //box-shadow: 0 0 25px #909399;
  //padding: 35px 35px 15px 35px;
  /deep/ .el-tabs__item {
    color: #c2ccd0;
    font-size: 18px;
    height: 40px;
    line-height: 25px;
    text-align: center;
    width: 100%;
    //padding: 0 18px;
  }
  /deep/ .el-tabs__item.is-active {
    color: @color;
  }
  /deep/ .el-tabs__nav-wrap {
    margin-bottom: 22px;
  }
  /deep/ .el-tabs__active-bar {
    background-color: @color;
  }
  /deep/ .el-tabs__nav-wrap::after {
    display: none;
    width: 80%;
  }

  /deep/ .el-form-item__label {
    color: @color !important;
  }
  /deep/ .el-form-item__label::before {
    display: none;
  }
  /deep/ .el-input {
    width: 90%;
    --el-input-focus-border-color:@color;
  }
  .bt {
    width: 88%;
    margin-left: 0px !important;
    text-align: center;
    height: 36px;
    background-color: white;
    border: 1px solid @color;
    margin-top: 10px;
    color:@color ;
    font-size: 15px;
    margin-bottom: 10px;
  }
  .bt:hover {
    color: white;
    opacity: 0.8;
    background-color: @color;
    box-shadow: 0 1px 5px 0 rgb(95, 95, 96), 0 1px 5px 0 rgb(95, 95, 96);
  }

}
.el-link {
  font-size: small;
  justify-content:flex-end;
  float: right;
  padding-right: 20px;
  padding-top: 10px;
}

</style>