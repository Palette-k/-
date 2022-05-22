<template>
  <menu1></menu1>
  <div class="body" :id="id">
    <!--    电影名-->
    <h2>{{movieName}}</h2>
    <div class="by">
      <el-row :gutter="20">
        <el-col :span="16">
          <div class="bodyl">

            <div class="part1">

              <el-row :gutter="20">
                <el-col :span="5">
                  <el-image class="photo" :src="url" ></el-image>
                </el-col>
                <el-col :span="10">
                  <div class="intro1" v-for="item in items" :item="item">
                    <span class="c1" >{{item.title}}: {{item.msg}}</span>
                  </div>
                </el-col>
                <el-col :span="5">
                  <div class="show">
                    <p>书影音评分</p>
                    <strong> {{movieScore}}</strong>
                    <br/>
                    <el-rate v-model="movieScore" disabled
                             text-color="#ff9900"  style="--el-rate-icon-margin: 0px;"></el-rate>
                    <br/>
                    <i>{{likeCount}}人收藏</i>
                  </div>
                </el-col>
              </el-row>
            </div>
            <div class="part2">

              <div  style="margin-left:10px;margin-right: 10px;float: left">
                <el-button  size="small" @click="getWantOrHave(2)">想看</el-button>
              </div>
              <div  style="float: left">
                <el-button size="small" @click="getWantOrHave(3)">看过</el-button>
              </div>
              <div>
                <Star1 :mid="mid"></Star1>
              </div>

              <div class="clear"></div>

              <div style="margin-right: 20px;float: left">
                <el-icon style="vertical-align: middle"><chat-line-round /></el-icon>
                <span>评论 </span>
              </div>
              <div style="margin-right: 20px;float: left">
                <el-icon style="vertical-align: middle"><edit-pen /></el-icon>
                <a href="/article" target="_blank">
                  <span>写影评</span>
                </a>
              </div>
              <div style="margin-right: 20px;float: left">
                <el-icon style="vertical-align: middle"><plus /></el-icon>
                <span>收藏</span>
              </div>
              <div >
                <el-icon style="vertical-align: middle"><share /></el-icon>
                <span>分享</span>
              </div>

            </div>

            <div class="part3">
              <h3>{{movieName}}的剧情简介· · · · · ·</h3>
              <p> {{movieIntro}} </p>

              <h3> {{movieName}}的演职员· · · · · ·</h3>
            </div>

            <div class="part4">
              <h3>{{movieName}}的评论· · · · · ·</h3>
              <Comment v-model:comment="comment" :mid="mid"></Comment>
            </div>

            <div class="part5">
              <h3>{{movieName}}的影评· · · · · ·</h3>
              <article-list :articleList="articleList"></article-list>
            </div>
          </div>
        </el-col>
        <el-col :span="8">
          <div class="bodyr">
            <moviesidesort></moviesidesort>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>
<script>
import Star1 from "@/components/Star/Star1";
import moviesidesort from "@/components/moviesidesort";
import Comment from "@/components/comment/Comment";
import Menu1 from "@/components/menu1";
import request from "@/utils/request";
import article from "../components/Article/article"
import {onMounted, reactive, ref, toRefs} from "vue";
import { useRoute} from 'vue-router';
import ArticleList from "@/components/Article/articleList";
export default {
  name: "Movie",
  components: {Menu1, Comment,article, moviesidesort, Star1,ArticleList},
  // Vue2语法
  /*这里的data只是显示用，可以全注释掉*/
  /*data() {
    return {
      id:'',
      movieName: '',
      movieScore:'',
      movieIntro:'',
      likeCount:'',
      items:[
        {
          title: '导演',
          msg: 'movieDid'
        },
        {
          title: '编剧',
          msg: 'bianju',
        },
        {
          title:'主演',
          msg: 'actor'
        },
        {
          title: '类型',
          msg: ''
        },
        {
          title: '制片国家/地区',
          msg: 'movieCry'
        },
        {
          title: '语言',
          msg: 'language'
        },
        {
          title: '上映时间',
          msg: 'movieTime'
        },
      ],

    }
  },*/

    methods: {


      // 看过或想看
     getWantOrHave(type){

        if(this.$store.state.id){

          let wantOrhave = {}
          wantOrhave.likedPostId = this.$store.state.id  //:1501205574206914561,
          wantOrhave.likedParentId = this.$route.query.id  //:1514982695127523330,
          wantOrhave.status = 1 //:1,
          wantOrhave.type = type //:2

          console.log(wantOrhave)

          /*        request.get("/works/auth/like", wantOrhave ).then(res => {
                      //reload
                  })*/
        }


      }
  },
  // mounted() {
  //   this.getAbout();
  // },

  //Vue3语法
  setup() {

    //reactive获取响应式数据，ref也是（不过ref只能用于基本数据类型数据）
    const store = reactive({
      mid:'',
      movieName: '',
      url:'',
      movieScore:'',
      movieIntro:'',
      likeCount:'',
      items:[
        {
          title: '导演',
          msg: 'movieDid'
        },
        {
          title: '编剧',
          msg: 'bianju',
        },
        {
          title:'主演',
          msg: 'actor'
        },
        {
          title: '类型',
          msg: ''
        },
        {
          title: '制片国家/地区',
          msg: 'movieCry'
        },
        {
          title: '语言',
          msg: 'language'
        },
        {
          title: '上映时间',
          msg: 'movieTime'
        },
      ],
      comment:'',
      articleList:'',
    })
    const route = useRoute()
    let id = route.query.id;
    const getAbout =() => {

      request.get("/works/" + id).then(res => {
        const data =res.data;
        store.mid = data.id;
        store.movieName = data.name;
        store.url = data.path;
        store.movieScore =data.score;
        store.items[0].msg = data.producerName;
        for(let i = 0; i<data.tagList.length; i++) {
          store.items[3].msg += data.tagList[i];
          if(i<data.tagList.length-1) {
            store.items[3].msg += " | ";
          }
        }
        store.items[4].msg = data.country;
        store.items[6].msg = data.creatTime;
        store.movieIntro = data.intro;
        store.likeCount =data.likeCount;
        store.comment = data.commentDTOList;
        store.articleList = data.articleDTOList;
        getComment(store.comment)
        /*console.log(store.comment)*/
      })
    }
    const getComment =(comment) => {
      for(let i in comment) {
        comment[i]["reply"]=[]
      }
    }


    onMounted(() => {
      getAbout()
    })

    //返回解构数据
    return toRefs(store);

  }

}
</script>

<style scoped>
.body {
  width: 75%;
  margin: 0 auto;
}


span {
  color: #484849;
}


.photo {

  height: 190px;
}
.show {
  border-left: solid 1px var(--el-border-color);
  padding-left: 8px;
  font-size: 12px;
  line-height: 15px;
}
.show p {
  font-size:5px;
  color: #909399;
  margin-bottom: 11px;
}
.show strong{
  font-style: normal;
  font-weight: normal;
  padding-top: 5px;
  color: #494949;
  min-width: 30%;
  font-size: 28px;
}
.show i{
  border-top: var(--el-border-color) solid 1px;
  padding-top: 5px;
  color: #909399;
}
.c1 {

  line-height: 1.5;
}
.clear{
  clear: both;
}
.part2 {
  margin-top: 25px;
}
/*.part3 p{
  text-align: justify;
  text-justify: inter-ideograph;
  text-indent: 2em;
  width: 95%;
}*/
.part2 span:hover {
  color: #7058a3;
  cursor: pointer;
}
.part2 a {
  text-decoration: none;
}
h3 {
  color: #7058a3;
}
</style>