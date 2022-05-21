<template>
  <div class="box">
    <login2 v-model:dialogVisible="dialogVisible"></login2>
    <!--发表评论-->
    <div class="c-box" @click="isLogin">
      <el-row :gutter="20">
        <el-col :span="2">
          <el-avatar :src="user.authorImg" />
        </el-col>
        <el-col :span="17">
          <el-input v-model="content"
                    type="textarea" :autosize="{ minRows: 2, maxRows: 5 }"
                    placeholder="写下你的评论" clearable/>
        </el-col>

        <el-col :span="1"  >
          <el-button @click="sendComment" type="primary" plain
                     style="line-height: 1.2;width: auto;height: auto">
            发表<br/>评论
          </el-button>
        </el-col>
      </el-row>
    </div>
    <!--评论区-->
    <div class="cc-box" >
      <p v-if="!comments" style="font-size: larger;color: #909399;text-align: center;margin-bottom: 50px">暂无评论，我来发表第一条评论!</p>
      <div v-else>
        <!--父评论-->
        <div class="comment" v-for="(item,index) in comments" :index="index">
          <el-row :gutter="20">
            <el-col :span="2">
              <el-avatar :src="item.user.img" />
            </el-col>
            <el-col :span="18">
              <div class="author-info">
                <span class="author-name">{{item.user.username}}</span>
              </div>
              <div class="talk-box">
                <p>
                  <span class="reply"> {{item.content}}</span>
                </p>
              </div>

              <div class="icon">

                <span class="author-time">{{dateStr(item.gmtCreate)}}</span>

                <a class="icon-zan" @click="CountP(index)">
                  <i class="zan" v-if="!this.comments[index].liked"></i>
                  <i class="dian" v-else></i>
                  <span v-if="item.likeCount>0">{{item.likeCount}}</span>
                </a>

                <a class="icon-pl"  @click="showReplyInput(index,item.user.username,item.user.id,item.id)">
                  <el-icon size="medium" style="vertical-align: text-top;margin-right: 3px"><chat-square /></el-icon>
                  <span v-if="item.commentCount==0">回复</span>
                  <span v-else>
                      {{item.commentCount}}
                    </span>
                </a>

                <a class="show-reply" v-show="item.commentCount >0"
                   @click="showReply(index,item.id)">
                  <span>展开</span>
                  <el-icon size="medium" style="vertical-align: text-top;margin-right: 3px"
                           v-if="!show(index)"><arrow-right /></el-icon>
                  <el-icon size="medium" style="vertical-align: text-top;margin-right: 3px"
                           v-else><arrow-down /></el-icon>
                </a>
              </div>

            </el-col>
          </el-row>

          <!--子回复-->
          <el-collapse-transition>
            <div class="reply-box" v-show="show(index)">
              <div class="rp" v-for="(rp,i) in item.reply" :key="i" >
              <el-row :gutter="20">
                <el-col :span="2">
                  <el-avatar :src="rp.img" style="margin-top: 7px;"></el-avatar>
                </el-col>
                <el-col :span="18">

                  <div class="talk-box" style="padding-top: 5px;padding-bottom: 10px">
                    <span class="author-name">{{rp.from}} </span>
                    <span> 回复 @{{rp.to}} : </span>
                    <span class="reply"> {{ rp.replyComment }}</span>

                  </div>
                  <div class="icon">
                    <span class="author-time">{{dateStr(rp.time)}}</span>

                    <a class="icon-zan" @click="CountC(index,i)">
                      <i class="zan" v-if="!this.comments[index].reply[i].liked"></i>
                      <i class="dian" v-else></i>
                      <span v-if="rp.likeCount >0">{{rp.likeCount}}</span>
                    </a>

                    <a class="icon-pl" @click="showReplyInput(index,rp.from,rp.id,rp.parentId)">
                      <el-icon size="medium" class="pl" ><chat-square /></el-icon>
                      <span v-if="rp.commentCount==0">回复</span>
                      <span v-else>
                         {{rp.commentCount}}
                      </span>
                    </a>

                  </div>
                </el-col>
              </el-row>

            </div>
<!--              <div class="page">
                <el-pagination small layout="prev, pager, next"
                               page-size="10" :total="50" />
              </div>-->

            </div>
          </el-collapse-transition>
          <!--回复框-->
          <div class="reply-input"  v-if="inputShow(index)">
            <el-row :gutter="20">
              <el-col :span="2">
                <el-avatar :src="user.authorImg" />
              </el-col>
              <el-col :span="17">
                <el-input v-model="content1" type="textarea" :autosize="{ minRows: 2, maxRows: 5 }"
                          :placeholder="'回复 @'+ this.to +' :'" clearable></el-input>
              </el-col>


              <el-col :span="1"  >
                <el-button @click="sendCommentReply(index,i)" type="primary" plain
                           style="line-height: 1.2;width: auto;height: auto">
                  发表<br/>评论
                </el-button>
              </el-col>
            </el-row>
          </div>
        </div>
      </div>
    </div>


  </div>


</template>
<script>
  import request from "@/utils/request";
  import login2 from "../Login/login2"
  var tp;
  export default {
    name: "Comment",
    // 接收父组件的评论列表和电影id
    props: ['comment','mid'],
    // 自定义更新父组件中的评论列表事件
    emits:['update:comment'],
    //引入登录弹窗
    components:{login2},
    computed: {
      //更新子组件中的comments和id
      comments() {
        return this.comment
      },
      id() {
        return this.mid
      },
    },
    data() {
      return {
        //用户信息
        user:{
          id:'',
          name: " ",
          authorImg: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png',
        },
        //评论框内容
        content: '',
        //回复框内容
        content1: '',
        //登录弹窗默认false
        dialogVisible: false,

      }
    },
    methods: {
      //获取用户信息
      getUser(){
        if(this.$store.state.id){
          request.get("/member/user/findUserById?userId=" + this.$store.state.id).then(res =>{
            this.user.authorImg = res.img
            this.user.name = res.username
            this.user.id = res.id
            /*console.log(this.user.name)*/

          })
        }
      },


      //获取二级评论
      getComment2(index,pid,comment){
        this.comments[index].reply=[]
        let a={}
        request.get("/works/comment/" + pid).then(res =>{
          const data = res.data;
          /*console.log(data)*/
          for(let i in data) {
            if(data[i].parentId == pid) {
              a.img =data[i].user.img
              a.id =data[i].user.id
              a.from =data[i].user.username
              a.toid =comment.find(e => e.id==pid).user.id
              a.to =comment.find(e => e.id==pid).user.username
              a.parentId = data[i].parentId
              a.selfId =data[i].id
              a.replyComment =data[i].content
              a.time =data[i].gmtCreate
              a.likeCount =data[i].likeCount
              a.commentCount =data[i].commentCount
              a.liked =false
              this.comments[index].reply.push(a)
              a ={}
              /*console.log(this.comments[index].reply)*/
            }
          }

        })
      },
      getZan(z){
        console.log(z)
        request.post('/works/auth/like', z).then(res =>{
          console.log(res)
        })
      },
      postComment(a){
        console.log(a)
        request.post('/works/auth/comment/' + this.$store.state.id ,a).then(res => {
          if(res.code == 200) {
            console.log(res)
            this.$message.success("评论成功！")
            // window.location.reload();
          }else {
            console.log(res)
            this.$message.error("评论失败！")
          }
        })
      },
      sendComment() {
        /*写评论*/
        if(this.$store.state.id) {
          if (!this.content) {
            this.$message({
              showClose: true,
              type: 'warning',
              message: '评论不能为空！'
            })
          } else {
            let a = {}
            let b = {
              user:{
                username:'',
                id: '',
                img:'',
              },
            }
            let timeNow = new Date().getTime()
            b.user.username = this.user.name
            b.user.id = this.user.id
            b.user.img = this.user.authorImg
            b.content = this.content
            b.gmtCreate = timeNow
            b.commentCount = 0
            b.likeCount = 0
            this.comments.unshift(b)
            this.$emit('update:comment',this.comments)
            a.parentId =this.id
            a.content = this.content
            a.type = 1
            this.postComment(a)
            /*console.log(JSON.stringify(a))*/
            this.content =''
          }
        }

      },
      sendCommentReply(index,i) {
        /*回复功能*/
        if(this.$store.state.id) {
          if (!this.content1) {
            this.$message({
              showClose: true,
              type: 'warning',
              message: '评论不能为空！'
            })
          } else {
            let a = {}
            let b ={}
            let timeNow = new Date().getTime()
            b.from = this.from
            b.to = this.to
            b.replyComment = this.content1
            b.img = this.user.authorImg
            b.time = timeNow
            b.commentCount = 0
            b.likeCount = 0
            this.comments[index].reply.unshift(b)
            this.comments[index].commentCount++
            a.parentId = this.commentId
            a.content = this.content1
            a.type = 2
            this.postComment(a)
            this.content1 =''
            this.comments[index].inputShow =false
          }
        }

      },
      CountP(index) {
        /*父组件点赞*/
        if(this.$store.state.id) {
          let zan = {}
          zan.likedPostId =this.$store.state.id
          zan.likedParentId = this.comments[index].id
          zan.type = 1
          if(!this.comments[index].liked) {
            this.comments[index].likeCount++
            zan.status = 1
          } else {
            this.comments[index].likeCount--
            zan.status = 0
          }
          this.getZan(zan)
          this.comments[index].liked =!this.comments[index].liked
        }

      },
      CountC(index,i) {
        /*子组件点赞*/
        if(this.$store.state.id) {
          let zan = {}
          zan.likedPostId =this.$store.state.id
          zan.likedParentId = this.comments[index].reply[i].selfId
          zan.type = 1
          if(!this.comments[index].reply[i].liked) {
            this.comments[index].reply[i].likeCount++
            zan.status = 1
          }else {
            this.comments[index].reply[i].likeCount--
            zan.status = 0
          }
          this.getZan(zan)
          this.comments[index].reply[i].liked = !this.comments[index].reply[i].liked
        }

      },
      showReplyInput(i,name,id,commentId) {
        if(this.$store.state.id) {
          /*关闭父评论回复框*/
          if(tp || tp==0) {
            this.comments[tp].inputShow= false
            /*console.log(tp)*/
          }
          tp = i

          /*关闭子评论回复框*/
          this.comments[i].inputShow =false
          this.index = i
          /*显示*/
          this.comments[i].inputShow =true
          this.to =name
          this.toId = id
          this.commentId =commentId
          // document.querySelector('.reply-input').scrollIntoView(true)
        }

      },
      //展开回复
      showReply(index,pid){
        this.comments[index].show = !this.comments[index].show;
        this.comments[index].inputShow =false
        this.getComment2(index,pid,this.comments)
      },
      show(index){
        return this.comment[index].show
      },
      inputShow(index) {
        return this.comment[index].inputShow
      },
      dateStr(date) {
        //获取js 时间戳
        var time = new Date().getTime();
        //去掉 js 时间戳后三位，与php 时间戳保持一致
        time = parseInt((time - date) / 1000);
        //存储转换值
        var s;
        if (time < 60 * 10) {//十分钟内
          return '刚刚';
        } else if ((time < 60 * 60) && (time >= 60 * 10)) {
          //超过十分钟少于1小时
          s = Math.floor(time / 60);
          return s + "分钟前";
        } else if ((time < 60 * 60 * 24) && (time >= 60 * 60)) {
          //超过1小时少于24小时
          s = Math.floor(time / 60 / 60);
          return s + "小时前";
        } else if ((time < 60 * 60 * 24 * 30) && (time >= 60 * 60 * 24)) {
          //超过1天少于30天内
          s = Math.floor(time / 60 / 60 / 24);
          return s + "天前";
        } else {
          //超过30天ddd
          var date = new Date(parseInt(date));
          return date.getFullYear() + "/" + (date.getMonth() + 1) + "/" + date.getDate();
        }
      },
      isLogin() {
          if(this.$store.state.id) {
            this.dialogVisible=false
          }else {
            this.dialogVisible = true

          }

      }
    },
    mounted() {
      this.getUser()
    },

  }
</script>


<style scoped>
.box {
  margin-left: 10px;
  margin-right: 10px;
}
.c-box {
  padding-top: 10px;
  padding-bottom: 30px;
  border-bottom: solid 1px #eeeff3;
}

.comment {
  padding-top: 15px ;
  padding-bottom: 10px;
  border-bottom: solid 1px #eeeff3;
}
.rp {
  margin-bottom: 5px;
}
.author-name {
  font-family: 华文楷体;
  margin-right: 4px;
}
.reply{
  font-family: Arial, Helvetica, sans-serif;
}
.icon {
  display: flex;
  color: #909399;
  font-size: 12px;
}
.reply-box {
  margin-top: 10px;
  padding-left: 60px;
  padding-bottom: 10px;
}
.zan {
  vertical-align: -2px;
  margin-top: -4px;
  margin-right: 3px;
  width: 15px;
  height: 14px;
  display: inline-block;
  background: url("icon2.png")  -150px 0;
  filter: grayscale(10);
}
.icon-zan:hover {
  color:   #337ecc;
  cursor: pointer;
  text-decoration: none;
}
.dian {
  vertical-align: -2px;
  margin-top: -4px;
  margin-right: 3px;
  width: 15px;
  height: 14px;
  display: inline-block;
  background: url("icon2.png")  -150px 0;
}
.icon-zan {
  margin-left: 15px;
}
.icon-pl {
  margin-left: 15px;
}
.pl {
  vertical-align: text-top;
  margin-right: 3px
}
.icon-pl:hover {
  color:   #337ecc;
  cursor: pointer;
  text-decoration: none;
}
.show-reply{
  margin-left: 15px;
}
.show-reply:hover{
  color:   #337ecc;
  cursor: pointer;
  text-decoration: none;
}
.reply-input {
  padding-top: 20px;
  padding-bottom: 20px;
}
.page{
  padding-top: 10px;
}
</style>