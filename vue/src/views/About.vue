<template>

  <div class="body" :id="id">
    <menu1></menu1>
    <div class="by">
         <div class="bodyl">
           <!--    电影名-->
           <h2>{{movieName}}</h2>
      <div class="part1">

            <el-image class="photo" :src="url" ></el-image>
            <div class="intro1">
              <div  v-for="item in items" :item="item">
                <span class="c1" >{{item.title}}: {{item.msg}}</span>
              </div>
            </div>



         <div class="show">
           <p>书影音评分</p>
          <strong> {{movieScore}}</strong>
          <br/>
          <el-rate v-model="movieScore" disabled
                   text-color="#ff9900"  style="--el-rate-icon-margin: 0px;"></el-rate>
           <br/>
           <i>{{likeCount}}人收藏</i>
         </div>

    </div>

           <div class="clear"></div>
        <div class="part2">

          <!--        想看和看过按钮  -->
           <div class="wantOrHave">
             <div  style="margin-left:10px;margin-right: 10px;float: left">
             <p v-show="!WantOrHaveShow" style="font-size: larger;color: plum;text-align: left;margin-bottom: 10px">{{message}}</p>
             </div>
             <div  style="float: left">
               <el-button v-show="!WantOrHaveShow" @click="delWantOrHave()" size="small" >取消</el-button>
             </div>

            <div  style="margin-left:10px;margin-right: 10px;float: left">
              <el-button v-show="WantOrHaveShow" size="small" @click="getWantOrHave(2)">想看</el-button>
            </div>
            <div  style="float: left">
              <el-button size="small" v-show="WantOrHaveShow"  @click="getWantOrHave(3)">看过</el-button>
            </div>
           </div>


            <div>
              <Star1 :mid="mid"></Star1>
            </div>

          <div class="clear"></div>

            <div style="margin-right: 20px;float: left">
              <el-icon style="vertical-align: middle"><chat-line-round /></el-icon>
              <a href="#comment">
                <span>评论 </span>
              </a>
            </div>

            <div style="margin-right: 20px;float: left">
              <el-icon style="vertical-align: middle"><edit-pen /></el-icon>
              <router-link :to="{ path:'article', query:{ id: mid ,title:movieName}}" >
                <span>写影评</span>
              </router-link>


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
          <h3 id="comment">{{movieName}}的评论· · · · · ·</h3>
          <Comment v-model:comment="comment" :mid="mid"></Comment>
        </div>

        <div class="part5">
          <h3>{{movieName}}的影评· · · · · ·</h3>
          <article-list :articleList="articleList"></article-list>
        </div>
      </div>

          <div class="bodyr" >
            <moviesidesort></moviesidesort>
            <el-backtop :right="100" :bottom="100" />
          </div>

    </div>

    <Footer style="flex:0;"></Footer>
  </div>

</template>
<script>
import Star1 from "@/components/Star/Star1";
import moviesidesort from "@/components/moviesidesort";
import Comment from "../components/comment/Comment";
import Menu1 from "@/components/menu1";
import request from "@/utils/request";
import article from "../components/Article/article"
import {onMounted, reactive, ref, toRefs} from "vue";
import {useRoute, useRouter} from "vue-router"
import {useStore} from "vuex";
import ArticleList from "@/components/Article/articleList";
import Footer from "@/components/footer/Footer";
export default {
  name: "Movie",
  components: {Footer, ArticleList, Menu1, Comment,article, moviesidesort, Star1},
  // Vue2语法
  /*这里的data只是显示用，可以全注释掉*/
  data() {
    return {
      WantOrHaveShow:true,
      message:'',
    }
  },

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


      request.post("/works/auth/like", wantOrhave ).then(res => {
                  //reload
          if(res.code === 200){
            this.WantOrHaveShow = false;
            if (wantOrhave.type === 2){
              this.message = "您想看这部作品"
            }else{
              this.message = "您已经看过这部作品"
            }
          }
      })
    }


  },
  showWant(){

    if(this.$store.state.id){

      let want = {}
      want.likedPostId = this.$store.state.id  //:1501205574206914561,
      want.likedParentId = this.$route.query.id  //:1514982695127523330,
      want.status = 1 //:1,
      want.type = 2 //:2

      console.log("want:",want)



/*      let wantOrHave =  document.getElementsByClassName('wantOrHave');
      console.log("wantOrHave:",wantOrHave)
      wantOrHave[0].add('')*/

              request.post("/works/showWantStatus", want ).then(res => {
                 if(res.code === 200){
                   this.WantOrHaveShow = false
                   this.message = "您想看这部作品"
                 }
              })
    }


  },

  showHave(){

    if(this.$store.state.id){

      let have = {}
      have.likedPostId = this.$store.state.id  //:1501205574206914561,
      have.likedParentId = this.$route.query.id  //:1514982695127523330,
      have.status = 1 //:1,
      have.type = 3 //:2

      console.log("have:",have)


      request.post("/works/showHaveStatus", have ).then(res => {
        if(res.code === 200){
          this.WantOrHaveShow = false
          this.message = "您已经看过这部作品"
        }
      })
    }


  },
  delWantOrHave(){
    if(this.$store.state.id){

      let wantOrhave = {}
      wantOrhave.likedPostId = this.$store.state.id  //:1501205574206914561,
      wantOrhave.likedParentId = this.$route.query.id  //:1514982695127523330,
      wantOrhave.status = 0 //:1,

      if(this.message === "您想看这部作品"){
        wantOrhave.type = 2
      }else{
        wantOrhave.type = 3
      }


      console.log(wantOrhave)


      request.post("/works/auth/like", wantOrhave ).then(res => {
                        //reload
              if(res.code === 200){
                this.WantOrHaveShow = true;
              }
      })
    }
  }
  },
  mounted() {
    this.showWant();
    this.showHave();
  },

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
        /*console.log(store)*/
      })
    }
    const getComment =(comment) => {
      //对接收的评论数据格式处理，增加二级评论
      for(let i in comment) {
        comment[i]["reply"]=[]
      }
    }
    /* const router =useRouter();
     const store1 =useStore();
   const toArticle =(id) => {
         router.push({
           name:"article",
           params:{
             id:id
           }
         })
       }*/

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
  max-width: 100%;
  /*其他*/
  /*position: absolute;*/
  /*top: 0;*/
  /*right: 0;*/
  /*bottom: 0;*/
  /*left: 0;*/
  display: flex;flex-direction: column;min-height: 100%;
}
.by {
  width: 75%;
  margin: 0 auto;
}
.by {
  display: flex;
}
.bodyl {
  width: 70%;
}
.bodyr {
  flex: 1;
  flex-direction: row;
}
@media (max-width: 600px) {
  .by {
    width: 100%;
    margin: 0 auto;
  }
  .bodyl {
    width: 100%;

  }
  .photo {
    margin-right: 0px !important;
  }
  .show {
    margin-left: 0px !important;
  }
  .bodyr {
    display: none;
  }
}
span {
  color: #484849;
}

.photo {
  float: left;
  height: 190px;
  padding: 5px 10px;
  margin-right: 20px;
}
.intro1{
  float: left;
}
.show {
  float: left;
  margin-left: 50px;
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
  margin-top: 10px;
  margin-left: 10px;
}
.part3 p{
  text-align: justify;
  text-justify: inter-ideograph;
  text-indent: 2em;
  width: 95%;
}
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