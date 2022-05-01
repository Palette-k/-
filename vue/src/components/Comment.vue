<template>
  <div class="box">
    <login2 :dialogVisible="dialogVisible"></login2>
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
<script src="../utils/comment.js"></script>


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