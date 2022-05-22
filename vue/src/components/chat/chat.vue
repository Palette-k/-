<template>
<!--  <div class="chat_body">-->
    <el-container >
      <!--左边-->
      <div class="left-aside">
        <!--头像-->
        <el-row slot="reference">
          <div class="head-portrait"><img :src="user.img?user.img:defaultAvatar" /></div>
        </el-row>
      </div>
      <!--中间-->
      <el-aside width="250px">
        <!--聊天列表头部-->
        <div class="chat-record">
          <div class="chat-record-input">
            <el-input v-model="inputQuery" size="mini" prefix-icon="el-icon-search" placeholder="搜索" clearable></el-input>
          </div>
          <div class="chat-record-add">
            <a href="#" style="line-height: 25px" title="发起群聊" @click="addGroupChatDialog">✚</a>
          </div>
        </div>
        <!--聊天记录列表-->
        <div v-for="(friend, i) in friendList" :key="i" style="margin-top :20px;">
          <el-row @click.native.stop="clickChatRecord(friend)">
            <el-col :span="6">
              <el-badge :value="2" class="item">
              <div class="chat-portrait"><img :src="friend.img?friend.img:defaultAvatar" /></div>
              </el-badge>
            </el-col>

            <el-col :span="18">
              <el-row>
                <el-col :span="15" class="chat-nickName">{{friend.username}}</el-col>
<!--                <el-col :span="9">{{item.showTime}}</el-col>-->
              </el-row>
<!--              <div class="chat-newMsg">{{item.message}}</div>-->
            </el-col>
          </el-row>
        </div>
      </el-aside>
      <!--右边-->
      <el-main>
        <el-row class="main-title">
          <el-col :span="21">{{contact.username}}</el-col>
          <el-col :span="3" @click.native.stop="lookDetail">
            <a href="#" class="el-icon-more" aria-hidden="true" title="聊天信息"></a>
          </el-col>
        </el-row>
        <div class="main-msg">
          <div v-for="(item,index) in msgList" :key="index">
            <el-row v-if="item.from == contact.id">
              <el-row style="margin: 0 auto; width: 200px">
                <span class="message-time">{{timestampToTime(item.time)}}</span>
              </el-row>
              <el-col style="max-width: 87.5%;">
                <div class="msg-portrait-left"><img :src="contact.img?contact.img:defaultAvatar" /></div>
              </el-col>
              <el-col class="left-msg">
                <div class="chat-message-left-nickName">{{contact.username}}</div>
                <div class="chat-msg-left">
                  <span class="msg-detail">{{item.message}}</span>
                </div>
              </el-col>

            </el-row>

            <el-row v-if="item.from == this.$store.state.id">
              <el-row style="margin: 0 auto; width: 200px">
                <span class="message-time">{{timestampToTime(item.time)}}</span>
              </el-row>
              <el-col :span="21">
                <div class="chat-message-right-nickName">{{user.username}}</div>
                <div class="chat-msg-right">
                  <span class="msg-detail">{{item.message}}</span>
                </div>
              </el-col>
              <el-col :span="3">
                <div class="msg-portrait-right"><img :src="user.img?user.img:defaultAvatar" /></div>
              </el-col>
            </el-row>
          </div>
        </div>

        <div class ="main-chat-input">
          <!--工具栏-->
          <el-popover placement="top-start" width="400" trigger="click" class="emoBox">
            <div class="emotionList">
              <a href="javascript:void(0);" @click="getEmo(index)" v-for="(item,index) in faceList" :key="index" class="emotionItem">{{item}}</a>
            </div>
            <el-button>
              class="emotionSelect"
                slot="reference"
              <i  class="iconfont icon-biaoqing">&#xe600;</i></el-button>
          </el-popover>

          <el-button class="emotionSelect">
            <el-icon><FolderOpened /></el-icon>
<!--            <i class="el-icon-folder-opened" aria-hidden="true" title="发送文件"></i>-->
          </el-button>

<!--          <el-button class="emotionSelect">
            <i class="el-icon-chat-dot-round" aria-hidden="true" title="聊天记录"></i>
          </el-button>-->

          <!--输入框-->
<!--          <el-input type="textarea" :rows="7" v-model="msg"  resize="none" border="none" @keyup.enter.native="sendMsg"  >-->
          <div>
            <label>
                <textarea
            class="messageText"
            maxlength="256"
            v-model="msg"
            :placeholder="hint"
            @keydown.enter="sendMsg($event)"
            style="width: 1280px; height: 200px"
                ></textarea>
            </label>

          </div>
          <el-button size="mini" class="send" :class="{emptyText: isEmptyText}"
                     style="float: right"
                     title="按下 ENTER 发送" @click="sendMsg()">发送</el-button>
<!--          </el-input>-->
        </div>
<!--        <el-button size="mini" style="float:right" @click="sendMsg()">发送(S)</el-button>-->
      </el-main>
    </el-container>

    <!--创建群聊弹框-->
<!--    <el-dialog :visible.sync="groupChatDialog" :close-on-click-modal="false" :append-to-body="true">
      <div style="height: 300px;">
        <div class="add-chatGroup-left" >
          <el-input v-model="inputQuery" size="mini" prefix-icon="el-icon-search" placeholder="搜索" clearable></el-input>
          <div v-for="(item,index) in friendList" :key="index" style="margin-top :20px;">
            <el-row>
              <el-col :span="6">
                <div class="chat-portrait"><img :src="item.friendPortrait" /></div>
              </el-col>

              <el-col :span="15">
                <div style="height: 35px;line-height: 35px;font-size: 18px;font-weight: bold">{{item.friendName}}</div>
              </el-col>
              <el-col :span="2">
                <div>
                  <el-checkbox @change="groupChatCheckChange($event,item)"></el-checkbox>
                </div>
              </el-col>
            </el-row>
          </div>
        </div>
        <div class="add-chatGroup-right">
          <div>
            <span style="margin-right: 100px;">{{checkGroupChatTitle}}</span>
            <el-button size="mini" type="success" @click="addGroupChat">确认</el-button>
            <el-button size="mini" type="info">取消</el-button>
          </div>
          <div v-for="tag in checkChatUsers" :key="tag.friendCode" style="margin-top :20px;">
            <el-row closable
                    :disable-transitions="false"
                    @close="handleCloseTag(tag.friendCode)">
              <el-col :span="6">
                <div class="chat-portrait"><img :src="tag.friendPortrait" /></div>
              </el-col>

              <el-col :span="10">
                <div style="height: 35px;line-height: 35px;font-size: 18px;font-weight: bold">{{tag.friendName}}</div>
              </el-col>
            </el-row>
          </div>
        </div>
      </div>
    </el-dialog>-->

    <!--群聊和个人详细信息-->
<!--    <el-drawer
        title="我是标题"
        :visible.sync="drawer"
        :with-header="false">
      <span>我来啦!</span>
    </el-drawer>-->

</template>
<script>
const appData=require("../../utils/emoji.json")//引入存放emoji表情的json文件
import request from "@/utils/request";

export default {
  components:{

  },

  data() {
    return {
      mainTitle:'',
      user:{},
      dialogVisible :true,
      //搜索框输入
      inputQuery:'',
      //登录客户头像
      msg: '',
      hint: '请输入聊天内容',
      // api: api,
      socket: null,
      bubbleMsg: '',
      interval: null,
      isEmptyText: true,
      defaultAvatar:require('../../image/th.jpg'),
      drawer:false,
      friendList: [],
      msgList:[],
      contact:{}

    }
  },

  methods:{
    //查看聊天详细信息
    lookDetail(){
      this.drawer = true;
    },

    //获取表情包，放入输入框
    getEmo(index){
      let textArea = document.getElementById('textarea');
      //将选中的表情插入到输入文本的光标之后
      function changeSelectedText(obj, str) {
        if (window.getSelection) {
          // 非IE浏览器
          textArea.setRangeText(str);
          // 在未选中文本的情况下，重新设置光标位置
          textArea.selectionStart += str.length;
          textArea.focus()
        } else if (document.selection) {
          // IE浏览器
          obj.focus();
          var sel = document.selection.createRange();
          sel.text = str;
        }
      }
      changeSelectedText(textArea,this.faceList[index]);
      this.content=textArea.value;// 要同步data中的数据
      return;
    },

    //添加群聊好友
    addGroupChat(){
      if(this.checkChatUsers.length < 2){
        this.$msg.success("请选择2个及以上好友！");
        return;
      }
      console.log(this.checkChatUsers);
      this.groupChatDialog = false;
      //提交
      this.$api.addGroupChat(this.checkChatUsers).then(res => {

      }).catch(err => {
        this.$commsgbox.alert(err);
      });

    },
    //勾选好友
    groupChatCheckChange(checked,item){
      if(checked  == true){
        console.log("勾选："+item.friendCode);
        this.checkChatUsers.push(item);
      }else{
        console.log("取消："+item.friendCode);
        for(var index in this.checkChatUsers){
          if(this.checkChatUsers[index].friendCode === item.friendCode){
            this.checkChatUsers.splice(index, 1);
          }
        }
      }
      if(this.checkChatUsers.length > 0 ){
        this.checkGroupChatTitle  = "已选择了"+this.checkChatUsers.length+"个联系人";
      }else{
        this.checkGroupChatTitle = '请勾选需要添加的联系人';
      }

    },
    //群聊弹框
    addGroupChatDialog(){
      let req = {"userCode":this.fromCode};
      this.$api.getFriendList(req).then(res => {
        this.friendList = res.data.data;
        this.groupChatDialog = true;
        this.checkChatUsers = [];
      }).catch(err => {
        this.$commsgbox.alert(err);
      });
    },

    //获取用户信息
    getUserImg(id){
      request.get("/member/user/showInfo/" + id).then(res => {
     //   console.log(res);
        this.user = res.data;
      });
    },
    /***********************/
    //查询聊天列表
    getChatFriendsList(){
      // 界面渲染时获取用户的好友列表并展示
      request({
        method: 'get',
        url: '/member/get/getContactor',
        params: {
          from: this.$store.state.id
        }
      }).then(res => {
         console.log(res)
        this.friendList = res
      }).catch(err => {
        console.log(err)
      })
    },
    //点击好友列表，开始聊天
    clickChatRecord(item){

      this.contact = item;
      console.log(this.contact)

      request({
        method: 'post',
        url: '/member/pullMsg',
        params: {
          from: this.$store.state.id,
          to: this.contact.id
        }
      }).then(res => {
        console.log(res);
        this.msgList = res;
        this.setScrollToEnd()
      }).catch(err => {
        console.log(err)
      })


    },


    sendMsg(e) {
    /*  if (e) {
        e.preventDefault()
      }*/
      if (!this.msg) {
        this.hint = '信息不可为空！'
        return
      }
      let entity = {
        from: this.$store.state.id,
        to: this.contact.id,
        message: this.msg,
        time: new Date()
      }
      this.socket.send(JSON.stringify(entity))
      this.msgList.push(entity)
      this.msg = ''
      this.hint = ''
      this.setScrollToEnd()
    },

    //关闭
    websocketClose(e){
      console.log("connection closed (" + e.code + ")");
    },
    //设置div的下拉条始终在底部
    setScrollToEnd(){
      let that = this;
      that.$nextTick(()=> {
        let box = that.$el.querySelector(".main-msg")
        box.scrollTop = box.scrollHeight
      });
    },
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


    //查询聊天界面信息
 /*   getChatInfo(){
      let that = this;
      //连接websocket的userCode
      let req = {"userCode":that.fromCode};
      that.$api.getChatInfo(req).then(res => {
        //好友聊天列表
        that.friendChatList = res.data.data.friendChatList;
        //所有的聊天记录
        that.chatRecord = res.data.data.chatRecord;
      }).catch(err => {
        that.$commsgbox.alert(err);
      });
    },
*/
    //初始化加载表情包列表
 /*   initEmoji(){
      for (let i in appData){//读取json文件保存数据给数组
        this.faceList.push(appData[i].char);
      }
    },*/
  },
  mounted() {
    //初始化websocket组件和方法

    // 渲染界面时, 根据用户的 id 获取 websocket 连接
    this.socket = new WebSocket(`ws://511u188n49.zicp.vip/api/member/webSocket/` + this.$store.state.id)
    this.socket.onmessage = event => {
      this.msgList.push(JSON.parse(event.data))
    }
    // 为防止网络和其他一些原因，每隔一段时间自动从信箱中获取信息
    this.interval = setInterval(() => {
      if (this.contact && this.contact.id) {
        request({
          method: 'post',
          url: '/member/pullMsg',
          params: {
            from: this.$store.state.id,
            to: this.contact.id
          }
        }).then(res => {
          console.log(res);
          this.msgList = res;
        }).catch(err => {
          console.log(err)
        })
      }
    }, 15000)

   // this.setScrollToEnd()
  },
  created() {

    //初始化查询个人信息 好友列表 和所有的聊天信息
    this.getUserImg(this.$store.state.id);
    //this.getChatInfo();
    this.getChatFriendsList();
  },

/*  filters: {
    time:function(time){
      return this.$comfunc.timeFormat(time);
    },
  }*/
}
</script>
<style scoped>
.message-time{
  background-color: #DADADA;
  margin: 0 auto;
  padding:1px 0px;
}
.add-chatGroup-left{
  width:350px;
  float: left;
  height: 300px;
 /* overflow-y: auto;*/
  /*右边框*/
  border-width: 0 1px 0 0;
  border-style: solid;
  border-color: black;
}
.add-chatGroup-right{
  width: 400px;
  float: left;
  height:300px;
 /* overflow-y: auto;*/
}
.chat-message-left-nickName{
  text-align: left;
  margin: 0px 10px;
}
.chat-message-right-nickName{
  text-align: right;
  margin: 0px 10px;
}
.msg-detail{
  padding: 0px 15px;
}
.chat-msg-right{
  text-align: center;
  min-height:35px;
  height:max-content;
  line-height: 35px;
  margin: 5px 10px;
  background-color: #9EEA6A;
  border-radius:5px;
  width:max-content;
  float:right;
  max-width:250px;
  word-wrap: break-word;
}
.msg-portrait-right{
  width:35px;
  height: 35px;
  margin-right:20px;
  margin-top:10px;
}
.msg-portrait-right img{

  display: block;
  width: 35px;
  height: 35px;
}
.chat-msg-left{
  text-align: center;
  min-height:35px;
  height:max-content;
  line-height: 35px;
  margin: 5px 10px;
  background-color: #FFFFFF;
  border-radius:5px;
  width:max-content;

  max-width:250px;
  word-wrap: break-word;
}
.msg-portrait-left{

  width:35px;
  height: 35px;
  margin: 10px 15px;
}
.msg-portrait-left img{

  display: inline;
  width: 35px;
  height: 35px;
}
.main-chat-input{
  height: 155px;
  background-color: #ffffff;
}
.main-msg{
  height:568px;
  overflow-y: auto;
}

.chat-newMsg{
  text-align: left;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}
.chat-nickName{
  text-align: left;
  font-size: 16px;
  font-weight: bold;
  width: 100px;
  float: left;
}
.chat-msgTime{
  text-align: right;
  font-size: 12px;
  width: 20px;
}
.chat-record-add{
  float: right;
  width: 25px;
  height: 25px;
  margin-right: 10px;
  margin-top:20px;
  background-color: #DCD9D8;
}
.head-portrait{
  margin:20px 12px;
}
.head-portrait img {
  display: block;
  width: 35px;
  height: 35px;
}
.chat-portrait img{
  display: block;
  width: 40px;
  height: 40px;
}

.chat-portrait{
  width:40px;
  height: 40px;
  margin-left: 10px;
}
.chat-nickName{
  text-align: center;
  font-size: 16px;
  font-weight: bold;
}

a{
  text-decoration:none;
}
a:hover{color: black}
input {
  background-color:transparent;
}
.chat-record-input{
  width: 175px;
  margin-left: 10px;
  line-height: 65px;
  float: left;
}

.left-aside{
  width: 60px;
  background-color: #28292C;
}
.chat_body{
  height: 500px;
  border: #99a9bf solid 1px;
}
.el-container{
  height: auto;
  margin-bottom: 40px;
}
.el-aside {
  background-color: #EEEAE8;
}
.el-main {
  background-color: #F5F5F5;
  padding: 0px;
}

.chat-record{
  height: 65px;
  width:230px;
}
.main-title{
  height: 65px;
  border-bottom: #99a9bf solid 1px;
  font-size: 16px;
  font-weight: bold;
  text-align: left;
  line-height: 65px;
  padding-left: 25px;
}

.left-msg{
  position: absolute;
  top:20px;
  left: 50px;
}

/*表情*/
.emotionSelect{
  border: none;
  padding:5px 10px;
  float:left;
}
.emotionList{
  display: flex;
  flex-wrap: wrap;
  padding:5px;
}
.emotionItem{
  width:10%;
  font-size:20px;
  text-align:center;
}
/*包含以下四种的链接*/
.emotionItem {
  text-decoration: none;
}
/*正常的未被访问过的链接*/
.emotionItem:link {
  text-decoration: none;
}
/*已经访问过的链接*/
.emotionItem:visited {
  text-decoration: none;
}
/*鼠标划过(停留)的链接*/
.emotionItem:hover {
  text-decoration: none;
}
/* 正在点击的链接*/
.emotionItem:active {
  text-decoration: none;
}
</style>

<style lang="scss">
/* el-popover是和app同级的，所以scoped的局部属性设置无效 */
/* 需要设置全局style */
.el-popover{
  height:500px;
  width:300px;
  overflow-y:auto;
}
</style>
