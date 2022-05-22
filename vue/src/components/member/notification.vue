<template>
  <!--头部菜单-->
  <el-header>
    <menu1 style="width: 100%"></menu1>
  </el-header>
  <div class="nav-container page-component">

    <user-aside class="left-nav"></user-aside>

<!--右侧内容 start-->
    <div class="page-container">
      <div>
        <el-button type="primary" @click="readAllnotification()">全部已读</el-button>
<!--        <el-button type="danger" @click="deleteBatchNotification()">批量删除</el-button>-->


            <el-empty v-if="notifications.length == 0" description="您没有收到消息噢^^" />
  <el-card v-if="JSON.stringify(notifications)!='{}'" v-for="notification in notifications">
    <h3>{{notification.outerTitle}}</h3>
    <h4 v-if="notification.status == 1" style="color: #28b1ad">已读</h4>
    <h4 v-if="notification.status == 0" style="color: #eb6100">未读</h4>
    <p v-if="notification.type == 1"> <b>{{ notification.notifierName }}</b> 于 {{timestampToTime(notification.gmtCreate)}}
      回复了你的评论</p>
    <p v-if="notification.type == 2"><b>{{ notification.notifierName }}</b> 于 {{timestampToTime(notification.gmtCreate)}}
      点赞了你的评论</p>
    <div class="right-button">
      <el-button v-if="notification.status == 0" type="success" round @click="readAndlink(notification)">已读</el-button>
      <el-button type="danger" round @click="deleteNotification(notification)">删除</el-button>
    </div>
  </el-card>

      </div>
    </div>
    <!--右侧内容 end-->
  </div>
</template>

<script>
import request from "@/utils/request";
import "@/assets/css/hospital_personal.css";
import "@/assets/css/hospital.css";
import "@/assets/css/personal.css";
import menu1 from "@/components/menu1";
import UserAside from "@/components/member/UserAside";

export default {
  name: "notification",
  components: {menu1,UserAside},
  data() {
    return {
      notifications:{
      },
      notificationType:"",
    }
  },

  created() {
    this.getNotification();
  },
  methods:{
    timestampToTime(timestamp) {
      var date = new Date(timestamp);
      var Y = date.getFullYear() + '-';
      var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
      var D = (date.getDate() < 10 ? '0'+date.getDate() : date.getDate()) + ' ';
      var h = (date.getHours() < 10 ? '0'+date.getHours() : date.getHours()) + ':';
      var m = (date.getMinutes() < 10 ? '0'+date.getMinutes() : date.getMinutes()) + ':';
      var s = (date.getSeconds() < 10 ? '0'+date.getSeconds() : date.getSeconds());

      let strDate = Y+M+D+h+m+s;
      return strDate;
    },
    getNotification(){
      request.get("/member/auth/notification/list/" + this.$store.state.id).then(res =>{
          console.log(res);
          this.notifications = res.data;
      })
    },
    readAndlink(notification){
       request.get("/member/auth/notification/read/" + notification.id).then(res => {
          console.log(res);
          notification.status = 1;
       })
    },
    readAllnotification(){
        request.get("/member/auth/notification/readAll/" + this.$store.state.id).then(res => {
          console.log(res);
          this.getNotification();
      })
    },
  /*  deleteBatchNotification(){
      request.post("/member/auth/notification/delete",ids).then(res =>{

      })
    },*/
    deleteNotification(notification){
         var ids = [notification.id];
         console.log(ids);
         request.post("/member/auth/notification/delete",ids).then(res =>{
           console.log(res);
           if(res.code === 200){
             this.$message({
               message: "消息删除成功",
               type: "success"
             });
           }
         this.getNotification();

         })
    }
  }
}
</script>

<style scoped>
.nav-container .left-nav {
  position: -webkit-sticky;
  position: sticky;
  top: 20px;
  float: left
}
.nav-container .page-container {
  padding-top: 38px;
  min-height: 500px;
  width: calc(100% - 200px);
  margin-left: 200px
}

.right-button{
  margin: 0px 5px 5px 5px;
  padding: 0px 5px 5px 5px;
  float: right;
}


</style>