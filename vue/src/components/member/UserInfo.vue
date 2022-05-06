<template>

  <!--头部菜单-->
  <el-header>
    <menu1 style="width: 100%"></menu1>
  </el-header>

  <!-- header -->
  <div class="nav-container page-component">
    <!--左侧导航 #start -->
    <user-aside class="left-nav"></user-aside>
    <!-- 左侧导航 #end -->
    <!-- 右侧内容 #start -->
    <div class="page-container">
      <div>
        <div class="title">修改账户信息</div>
        <div class="status-bar">
          <div class="status-wrapper">
            <el-icon><user-filled /></el-icon>修改账户信息
          </div>
        </div>
        <div class="tips">
          <el-icon><home-filled /></el-icon>
         本站承诺会保护您的隐私信息，您的联系方式不会被泄露，请放心填写
        </div>
        <div class="form-wrapper" >
          <div>
            <el-form
                :model="userAuah"
                label-width="110px"
                label-position="left"
            >
              <el-form-item prop="username" label="昵称：" class="form-normal">
                <div class="name-input">
                  <el-input
                      v-model="userAuah.username"
                      placeholder="请输入您的昵称"
                      class="input v-input"
                  />
                </div>
              </el-form-item>
              <el-form-item label="性别">
                <el-select placeholder="请选择" v-model="userAuah.gender" filterable>
                  <el-option
                      v-for="item in options"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value"
                  ></el-option>
                </el-select>
              </el-form-item>
              <el-form-item prop="age" label="年龄：">
                <el-input
                    v-model="userAuah.age"
                    placeholder="请输入您的年龄"
                    class="input v-input"
                />
              </el-form-item>
              <el-form-item prop="account" label="手机号码：">
                <el-input
                    v-model="userAuah.account"
                    placeholder="请输入您的手机号码"
                    class="input v-input"
                />
              </el-form-item>
              <el-form-item prop="email" label="邮箱：">
                <el-input
                    v-model="userAuah.email"
                    placeholder="请输入您的邮箱"
                    class="input v-input"
                />
              </el-form-item>
              <el-form-item prop="img" label="上传头像：">
                <div class="upload-wrapper" >
                  <div class="avatar-uploader">
                    <el-upload
                        :data="dataObj"
                        class="avatar-uploader"
                        :action="fileUrl"
                        :show-file-list="false"
                        :on-success="onUploadSuccess"
                        :before-upload="beforeUpload"
                    >
                      <div class="upload-inner-wrapper" >
                        <img
                            v-if="userAuah.img"
                            :src="userAuah.img"
                            class="avatar"
                        />
                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                        <div v-if="!userAuah.img" class="text">
                          上传头像
                        </div>
                      </div>
                    </el-upload>
                  </div>
<!--                  <img
                      src="//img.114yygh.com/static/web/auth_example.png"
                      class="example"
                  />-->
                </div>
              </el-form-item>
            </el-form>

            <div class="submitButton" style="text-align: center">
            <el-button type="primary" plain @click="saveUserAuah()" >保存</el-button>
            </div>
          </div>
        </div>

      </div>
    </div>
    <!-- 右侧内容 #end -->
    <!-- 登录弹出框 -->
  </div>
  <!-- footer -->
</template>

<script>
import "@/assets/css/hospital_personal.css";
import "@/assets/css/hospital.css";
import "@/assets/css/personal.css";

import menu1 from "@/components/menu1";
import singleUpload from "@/components/upload/singleUpload"
import request from "@/utils/request"
import {policy} from "@/components/upload/policy";
import {getUUID} from "@/utils";
import UserAside from "@/components/member/UserAside";
const defaultForm = {
  id:"",
  username: "",
  account: "",
  age:"",
  email: "",
  gender:0,
  img:""
};


export default {
  components: {
     menu1,singleUpload,UserAside
  },
  data() {
    return {
      dataObj: {
        policy: '',
        signature: '',
        key: '',
        ossaccessKeyId: '',
        dir: '',
        host: '',
        // callback:'',
      },
      userAuah: defaultForm,
      genderList: ['男','女'],
      fileUrl: "http://commentsystem-community.oss-cn-shenzhen.aliyuncs.com",
      userInfo: {
        param: {},
      },
      submitBnt: "提交",
      options : [
        {
          value:1,
          label:'女'
        },
        {
          value:0,
          label:'男'
        }
      ]

    };
  },
  created() {
   this.init();
  },
  methods: {
    init() {

      this.getUserInfo();
    },
    getUserInfo() {
      if(this.$store.state.id){
        request.get("/member/user/showInfo/" + this.$store.state.id).then(res =>{
          console.log(res);
          this.userAuah = res.data;
        })

      }

    },

    saveUserAuah() {
      console.log("要提交的userAuah:",this.userAuah);
      request.post("/member/user/update",this.userAuah).then(res =>{
          console.log(res);
          this.userAuah = res.data;
          if(res.code == 200){
            this.getUserInfo();
          }
      })

    },
    beforeUpload(file) {
      const _self = this
      return new Promise((resolve, reject) => {
        policy().then(response => {
          console.log("响应的数据",response);
          _self.dataObj.policy = response.data.policy;
          _self.dataObj.signature = response.data.signature;
          _self.dataObj.ossaccessKeyId = response.data.accessid;
          _self.dataObj.key = response.data.dir +getUUID()+'_${filename}';
          _self.dataObj.dir = response.data.dir;
          _self.dataObj.host = response.data.host;
          console.log("响应的数据222。。。",_self.dataObj);
          resolve(true)
        }).catch(err => {
          reject(false)
        })
      })
    },

    onUploadSuccess(response, file) {
    /*  if (response.code !== 200) {
        this.$message.error("上传失败");
        return;
      }*/
      // 填充上传文件列表
      this.userAuah.img = this.dataObj.host + "/" + this.dataObj.key.replace("${filename}",file.name);
    },
  },
};
</script>
<style>
.status-wrapper {
  margin: auto;
  top: 0; left: 0; bottom: 0; right: 0;
}
.submitButton{
  margin: auto;
  top: 0; left: 0; bottom: 0; right: 0;
}
.header-wrapper .title {
  font-size: 16px;
  margin-top: 0;
}
.content-wrapper {
  margin-left: 0;
}
.patient-card .el-card__header .detail {
  font-size: 14px;
}
.page-container .title {
  letter-spacing: 1px;
  font-weight: 700;
  color: #333;
  font-size: 16px;
  margin-top: 0;
  margin-bottom: 20px;
}
.page-container .tips {
  width: 100%;
  padding-left: 0;
}
.page-container .form-wrapper {
  padding-left: 92px;
  width: 580px;
}
.form-normal {
  height: 40px;
}
.bottom-wrapper {
  width: 100%;
  padding: 0;
  margin-top: 0;
}
</style>
