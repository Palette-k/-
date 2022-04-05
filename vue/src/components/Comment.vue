<template>
  <div class="box">
    <!--发表评论-->
    <div class="c-box" >
      <el-row :gutter="20">
        <el-col :span="2">
          <el-avatar :src="authorImg" />
        </el-col>
        <el-col :span="17">
          <el-input v-model="content" type="textarea" :autosize="{ minRows: 2, maxRows: 5 }"
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
      <p v-if="comments.length == 0" style="font-size: larger;color: #909399;text-align: center;margin-bottom: 50px">暂无评论，我来发表第一条评论!</p>
      <div v-else>
        <!--父评论-->
        <div class="comment" v-for="(item,index) in comments" :index="index">
          <el-row :gutter="20">
            <el-col :span="2">
              <el-avatar :src="item.Img" />
            </el-col>
            <el-col :span="18">
              <div class="author-info">
                <span class="author-name">{{item.name}}</span>
              </div>
              <div class="talk-box">
                <p>
                  <span class="reply"> {{item.comment}}</span>
                </p>
              </div>

              <div class="icon">
                <span class="author-time">{{item.time}}</span>

                <a class="icon-zan" @click="Count(index)">
                  <i class="zan" v-if="!this.comments[index].liked"></i>
                  <i class="dian" v-else></i>
                  <span v-if="item.likeCount>0">{{item.likeCount}}</span>
                </a>

                <a class="icon-pl"  @click="showReplyInput(index,item.name,item.id)">
                  <el-icon size="medium" style="vertical-align: text-top;margin-right: 3px"><chat-square /></el-icon>
                  <span v-if="item.commentCount==0">回复</span>
                  <span v-else>
                      {{item.commentCount}}
                    </span>
                </a>
              </div>

            </el-col>
          </el-row>

          <!--子回复-->
          <div class="reply-box">
            <div class="rp" v-for="(reply,i) in item.reply" :key="i" >
              <el-row :gutter="20">
                <el-col :span="2">
                  <el-avatar :src="reply.headerImg" style="margin-top: 7px;"></el-avatar>
                </el-col>
                <el-col :span="18">

                  <div class="talk-box" style="padding-top: 5px;padding-bottom: 10px">
                    <span class="author-name">{{reply.from}} </span>
                    <span> 回复 @{{reply.to}} : </span>
                    <span class="reply"> {{reply.comment}}</span>

                  </div>
                  <div class="icon">
                    <span class="author-time">{{reply.time}}</span>

                    <a class="icon-zan" @click="Count1(index,i)">
                      <i class="zan" v-if="!this.comments[index].reply[i].liked"></i>
                      <i class="dian" v-else></i>
                      <span v-if="reply.likeCount >0">{{reply.likeCount}}</span>
                    </a>

                    <a class="icon-pl" @click="showReplyInput(index,reply.from,reply.id)">
                      <el-icon size="medium" class="pl" ><chat-square /></el-icon>
                      <span v-if="reply.commentCount==0">回复</span>
                      <span v-else>
                         {{reply.commentCount}}
                      </span>
                    </a>

                  </div>
                </el-col>
              </el-row>

            </div>
          </div>
          <!--回复框-->
          <div class="reply-input"  v-if="inputShow(index)">
            <el-row :gutter="20">
              <el-col :span="2">
                <el-avatar :src="authorImg" />
              </el-col>
              <el-col :span="17">
                <el-input v-model="content1" type="textarea" :autosize="{ minRows: 2, maxRows: 5 }"
                          :placeholder="'回复 @'+this.to +' :'" clearable></el-input>
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
var tp ;
export default {
  name: "Comment",
  data() {
    return {
      name: 'Li 华',
      id: 1,
      content: '',
      content1: '',
      authorImg: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png',

      comments: [
        {
          name: 'Lana琳娜',
          id: 19870621,
          Img: 'https://ae01.alicdn.com/kf/Hd60a3f7c06fd47ae85624badd32ce54dv.jpg',
          comment: '我发布一张新专辑Norman Fucking Rockwell,大家快来听啊',
          time: '2019年9月16日 18:43',
          commentCount: 2,
          likeCount: 15,
          liked: false,
          inputShow: false,
          reply: [
            {
              from: 'Taylor Swift',
              fromId: 19891221,
              headerImg: 'https://ae01.alicdn.com/kf/H94c78935ffa64e7e977544d19ecebf06L.jpg',
              to: 'Lana Del Rey',
              toId: 19870621,
              comment: '我很喜欢你的新专辑！！',
              time: '2019年9月16日 18:43',
              commentCount: 0,
              likeCount: 0,
              liked:false,
              inputShow: false
            },
            {
              from: 'Taylor Swift',
              fromId: 198911,
              headerImg: 'https://ae01.alicdn.com/kf/H94c78935ffa64e7e977544d19ecebf06L.jpg',
              to: 'Lana Del Rey',
              toId: 198701,
              comment: '我很喜欢你的新专辑！！',
              time: '2019年9月16日 18:43',
              commentCount: 0,
              likeCount: 0,
              liked:false,
              inputShow: false
            }
          ]
        },
        {
          name: 'Lana琳娜',
          id: 19870621,
          Img: 'https://ae01.alicdn.com/kf/Hd60a3f7c06fd47ae85624badd32ce54dv.jpg',
          comment: '我发布一张新专辑Norman Fucking Rockwell,大家快来听啊',
          time: '2019年9月16日 18:43',
          commentCount: 2,
          likeCount: 15,
          liked: false,
          inputShow: false,
          reply: [
            {
              from: 'Taylor Swift',
              fromId: 19891221,
              headerImg: 'https://ae01.alicdn.com/kf/H94c78935ffa64e7e977544d19ecebf06L.jpg',
              to: 'Lana Del Rey',
              toId: 19870621,
              comment: '我很喜欢你的新专辑！！',
              time: '2019年9月16日 18:43',
              commentCount: 0,
              likeCount: 0,
              liked:false,
              inputShow: false
            },
            {
              from: 'Taylor Swift',
              fromId: 198911,
              headerImg: 'https://ae01.alicdn.com/kf/H94c78935ffa64e7e977544d19ecebf06L.jpg',
              to: 'Lana Del Rey',
              toId: 198701,
              comment: '我很喜欢你的新专辑！！',
              time: '2019年9月16日 18:43',
              commentCount: 0,
              likeCount: 0,
              liked:false,
              inputShow: false
            }
          ]
        },
        {
          name: 'Lana琳娜',
          id: 19870621,
          Img: 'https://ae01.alicdn.com/kf/Hd60a3f7c06fd47ae85624badd32ce54dv.jpg',
          comment: '我发布一张新专辑Norman Fucking Rockwell,大家快来听啊',
          time: '2019年9月16日 18:43',
          commentCount: 2,
          likeCount: 15,
          liked: false,
          inputShow: false,
          reply: [
            {
              from: 'Taylor Swift',
              fromId: 19891221,
              headerImg: 'https://ae01.alicdn.com/kf/H94c78935ffa64e7e977544d19ecebf06L.jpg',
              to: 'Lana Del Rey',
              toId: 19870621,
              comment: '我很喜欢你的新专辑！！',
              time: '2019年9月16日 18:43',
              commentCount: 0,
              likeCount: 0,
              liked:false,
              inputShow: false
            },
            {
              from: 'Taylor Swift',
              fromId: 198911,
              headerImg: 'https://ae01.alicdn.com/kf/H94c78935ffa64e7e977544d19ecebf06L.jpg',
              to: 'Lana Del Rey',
              toId: 198701,
              comment: '我很喜欢你的新专辑！！',
              time: '2019年9月16日 18:43',
              commentCount: 0,
              likeCount: 0,
              liked:false,
              inputShow: false
            }
          ]
        },
      ]

    }
  },
  methods: {
    sendComment() {
      /*写评论*/
      if (!this.content) {
        this.$message({
          showClose: true,
          type: 'warning',
          message: '评论不能为空！'
        })
      } else {
        let a = {}
        let timeNow = new Date().getTime()
        let time = this.dateStr(timeNow)
        a.name = this.name
        a.id = this.id
        a.comment = this.content
        a.Img = this.authorImg
        a.time = time
        a.commentCount = 0
        a.likeCount = 0
        this.comments.push(a)
        this.content =''
      }
    },
    sendCommentReply(index,i) {
      /*回复功能*/
      if (!this.content1) {
        this.$message({
          showClose: true,
          type: 'warning',
          message: '评论不能为空！'
        })
      } else {
        let a = {}
        let timeNow = new Date().getTime()
        let time = this.dateStr(timeNow)
        a.from = this.name
        a.to = this.to
        a.comment = this.content1
        a.headerImg = this.authorImg
        a.time = time
        a.commentCount = 0
        a.likeCount = 0
        this.comments[index].reply.push(a)
        this.comments[index].commentCount++
        this.comments[index].reply[i].commentCount++
        this.content1 =''
        this.comments[index].inputShow =false
      }
    },
    Count(index) {
      /*父组件点赞*/
      if(!this.comments[index].liked) {
        this.comments[index].likeCount++
      } else {
        this.comments[index].likeCount--
      }
      this.comments[index].liked =!this.comments[index].liked
    },
    Count1(index,i) {
      /*子组件点赞*/
      if(!this.comments[index].reply[i].liked) {
        this.comments[index].reply[i].likeCount++
      }else {
        this.comments[index].reply[i].likeCount--
      }
      this.comments[index].reply[i].liked = !this.comments[index].reply[i].liked
    },

    showReplyInput(i,name,id) {
      /*关闭父评论回复框*/
      if(tp || tp==0) {
        this.comments[tp].inputShow= false
        console.log(tp)
      }
      tp = i

      /*关闭子评论回复框*/
      this.comments[i].inputShow =false
      this.index = i
      /*显示*/
      this.comments[i].inputShow =true
      this.to =name
      this.toId = id
    },
    inputShow(index) {
      return this.comments[index].inputShow
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
    }
  }
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
.cc-box {
  padding-top: 30px;
}
.comment {
  margin-bottom: 20px;
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
  background: url("../image/icon2.png")  -150px 0;
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
  background: url("../image/icon2.png")  -150px 0;
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
.reply-input {
  padding-bottom: 20px;
}
</style>