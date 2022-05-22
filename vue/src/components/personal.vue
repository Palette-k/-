<template>
    <!--个人中心-->
    <el-button type="text" style="padding: 5px 5px" @click="drawer=true">
        <el-avatar :icon="UserFilled" :src=userImg></el-avatar>
    </el-button>
    <el-drawer v-model="drawer" :with-header="false">
        <div class="layout">
        <div class="usericon">
        <el-avatar  :icon="UserFilled"
                    :src=userImg
                    :size='number' style="margin: 0 auto"></el-avatar>

        </div>
          <br>
            <div class="id">
                <h3>账号 {{ username }}</h3>
            </div>
            <br>
          <!--这里要记得导入id-->

          <div class="person">
            <el-button type="text" >
                <a href="/userInfo">个人中心</a>
              <el-icon><avatar /></el-icon>
            </el-button>
        </div>
            <br>
       <div class="history">
            <el-button type="text">
              <a href="/chat">聊天记录</a>
                <el-icon><clock /></el-icon></el-button>

       </div>
            <br>
      <div class="message">
            <el-button type="text"  >
              <a href="/notification">消息</a>
                <el-icon><message /></el-icon></el-button>
      </div>
            <br>
        <div class="out">
            <el-col :span="12">  <el-button>
                退出
                <el-icon><arrow-right-bold /></el-icon>
            </el-button></el-col>
        </div>
        </div>
    </el-drawer>

</template>

<script>
    import {ArrowRightBold, Avatar, Clock, Message,  UserFilled} from "@element-plus/icons-vue"
    import {ref} from "vue";
    import cookie from "js-cookie";

   import request from "@/utils/request";
    export default {
        name: "personal-int",
        components:{ArrowRightBold,Avatar,Clock,Message,},
        data(){

            return{
                UserFilled,
                drawer:ref(false),
                username:'',
                userImg:'',
            }
        },
      created() {
        this.getPersonal();
      },
        methods:{
           getPersonal(){
             if(this.$store.state.id){
               request.get("/member/user/findUserById?userId=" + this.$store.state.id).then(res => {
                 console.log(res);
                 this.username = res.username;
                 this.userImg = res.img;
               })
             }
             }

        },


    }
</script>
<style>
    .el-drawer__body{
        padding: 0 !important;
    }
</style>

<style scoped>
    .layout{
        display: flow;
    }
    .usericon{

   margin-left: 4cm;
        padding: 20px;



    }
    .id{
 padding:0 20px;
        border-bottom: solid 1px #03a9f4;
    }
    .person{
        padding: 0 20px;
        border-bottom: solid 1px #03a9f4;

    }
    .history{
        padding: 0 20px;
        border-bottom: solid 1px #03a9f4;
    }
    .message{
        padding: 0 20px;
        border-bottom: solid 1px #03a9f4;
    }
    .out{
      padding:0 20px;
        position: fixed;
        bottom: 0;


    }


</style>