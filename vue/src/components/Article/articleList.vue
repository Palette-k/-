<template>
  <div class="articleList-container">
    <div class="article-list" v-for="(item,index) in article" :index="index">
      <div class="author-info">
        <el-avatar :src="item.user.img"  shape="square" :size="24"/>
        <span class="name">{{item.user.username}}</span>
        <span><el-rate disabled v-model="item.score" ></el-rate></span>
        <span class="time">{{getDate(item.gmtCreate)}}</span>
      </div>
      <div class="article-content" >
        <h2 class="title"> {{item.title}}</h2>
        <div class="content">
          <span v-html="$options.filters.Len(item)"></span>
            <a class="show" v-show="item.content.length > 100"
               @click.stop="pickUp(item,$event)">{{item.isExpand ?'收起':'...展开'}}</a>
        </div>

      </div>
      <div class="other-info">
        <el-tooltip effect="light" content="有用" placement="bottom">
          <a class="like" @click="up(index)">
              <el-icon><caret-top /></el-icon>
              <span>{{item.likeCount}}</span>
          </a>
        </el-tooltip>

        <el-tooltip effect="light" content="没用" placement="bottom">
          <a class="unlike" @click="down(index)">
            <el-icon><caret-bottom/></el-icon>
          </a>
        </el-tooltip>

        <el-tooltip effect="light" content="浏览量" placement="top">
          <span class="view">
          <el-icon><svg viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg" data-v-ba633cb8=""><path fill="currentColor" d="M512 160c320 0 512 352 512 352S832 864 512 864 0 512 0 512s192-352 512-352zm0 64c-225.28 0-384.128 208.064-436.8 288 52.608 79.872 211.456 288 436.8 288 225.28 0 384.128-208.064 436.8-288-52.608-79.872-211.456-288-436.8-288zm0 64a224 224 0 1 1 0 448 224 224 0 0 1 0-448zm0 64a160.192 160.192 0 0 0-160 160c0 88.192 71.744 160 160 160s160-71.808 160-160-71.744-160-160-160z"></path></svg></el-icon>
          <span>{{item.viewCount}}</span>
        </span>
        </el-tooltip>

        <el-tooltip effect="light" content="评论" placement="right">
          <span class="comment" @click="showComments(index)">
          <el-icon><chat-round /></el-icon>
          <span>{{item.commentCount}}</span>
        </span>
        </el-tooltip>

      </div>

      <el-collapse-transition>
        <div class="commentList" v-show="show(index)">
          <article-comment v-model:comment="article[index].CommentDTOS" :mid="article[index].id"></article-comment>
        </div>
      </el-collapse-transition>
    </div>
  </div>
</template>

<script>

import {CaretBottom} from "@element-plus/icons-vue";
import Comment from "../ccoment/Comment";
import ArticleComment from "@/components/Article/articleComment";
export default {
  name: "articleList",
  components: {ArticleComment, Comment, CaretBottom},
  props:["articleList"],
  computed: {
    article() {
      console.log(this.articleList)
      for(let i in this.articleList) {
        this.articleList[i].liked =false
        this.articleList[i].isExpand =false
      }
      return this.articleList
    }
  },
  data() {
    return{

    }
  },
  methods: {
    getDate(date) {
      let time = new Date(date)
      const year = time.getFullYear()
      const month = time.getMonth()<10? ('0'+time.getMonth()) : time.getMonth()
      const day = time.getDay()<10? ('0'+time.getDay()) : time.getDay()
      const hour = time.getHours() <10 ? ('0' + time.getHours()) : time.getHours()
      const second = time.getSeconds() < 10 ? ('0' + time.getSeconds()) : time.getSeconds()
      const minutes = time.getMinutes() < 10 ? ('0' + time.getMinutes()) : time.getMinutes()
      return year + "-" + month +"-"+day  +"   " +
          hour+ ":" +second +":" + minutes
    },
    pickUp(item, e) {
      let target = e.target.parentNode
      item.isExpand = !item.isExpand
      if (item.isExpand) {
        target.style.height = "auto"
      }else {
        target.style.height = "3rem"
      }
    },
    up(index) {
      if(this.$store.state.id) {
        if(!this.article[index].liked) {
          let p1 = document.getElementsByClassName("like")[index]
          p1.style.backgroundColor = "#7058a3"
          p1.style.color = "#f0f7f9"
          let p2 = document.getElementsByClassName("unlike")[index]
          p2.style.backgroundColor = "#f0f7f9"
          p2.style.color = "#7058a3"
          this.article[index].likeCount++
          this.article[index].liked = !this.article[index].liked
        }
      }
    },
    down(index) {
      if(this.$store.state.id) {
        if(this.article[index].liked) {
          let p1 = document.getElementsByClassName("like")[index]
          p1.style.backgroundColor = "#f0f7f9"
          p1.style.color = "#7058a3"
          this.article[index].likeCount--
          this.article[index].liked = !this.article[index].liked
        }
        let p2 = document.getElementsByClassName("unlike")[index]
        p2.style.backgroundColor = "#7058a3"
        p2.style.color = "#f0f7f9"
      }
    },
    showComments(index) {
      this.article[index].show = !this.article[index].show;
    },
    show(index) {
      return this.article[index].show
    }
  },
  filters: {
    Len(item) {
      if(!item.content) return '';
      let text = item.content.replace(/<[^<>]+>/g ,"")
      // console.log(text)
      if(item.isExpand) {
        return item.content
      }else {
        return text.substr(0,100)
      }
    }
  }
}
</script>

<style lang="less" scoped>
.articleList-container {
  .article-list {
    padding-top: 20px;
    padding-bottom: 20px;
    border-bottom: 1px solid #eeeff3;
    .commentList {
      margin: 15px 10px;
      border: #ebebeb solid 1px;
      box-shadow: 0 1px 3px rgb(18 18 18 / 10%);
    }
  }
  .author-info {
    height: 24px;
    .name {
      font-size: 13px;
      margin-left: 10px;
      color: #484849;
    }
    .name:hover {
      cursor:pointer;
      color: #337ecc;
    }
    .time {
      font-size: 13px;
      font-weight: 300;
      margin-left: 10px;
      color: #999;
    }
    /deep/ .el-rate {
      margin-left: 5px;
      height: 0px;
      --el-rate-icon-margin: 0px;
      --el-rate-icon-size: 13px;
    }
    .el-avatar {
      margin-top: 2px;
      vertical-align: text-top;
    }
  }
  .article-content {
    h2 {
      font-size: 17px;
      margin-bottom: 10px;
      font-weight: 600;
    }
    .title {
      font-family: Arial, Helvetica, sans-serif;
      margin-top: 16px;
      color: #484849;
      margin-bottom: -15px;
    }
    .title:hover {
      cursor: pointer;
      color: #337ecc;
    }
    .content {
      span {
        font-size: 14px;
        color: #484849;
        font-family: Arial, Helvetica, sans-serif;
        text-align: justify;
        text-justify: inter-ideograph;
        text-indent: 2em;
      }
      a {
        color: #337ecc;
        font-size: 14px;
      }
      a:hover {
        cursor: pointer;
        text-decoration: underline;
      }
    }
  }
  .other-info {
    margin-top: 15px;
    a {
      color:#3377aa;
      padding: 4px 10px;
      margin-right: 10px;
      border-radius: 2px;
      background-color: #f0f7f9;
      opacity: 50%;
      display: inline-block;
      line-height: 1;
    }
    a:hover {
      color: #7058a3;
      cursor: pointer;
    }
    .view {
      margin-left: 10px;
    }
    .view ,.comment{
      color: #909399;
      border: none;
      background-color: white;
      margin-right: 15px;
      .el-icon {
        margin-right: 4px;
      }
      .span {
        vertical-align: text-bottom;
      }
      span:hover{
        cursor: default;
      }
    }
  }
}



</style>