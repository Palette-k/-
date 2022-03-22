<template>
  <div class="body">
    <!--    电影名-->
    <h2>{{movieName}}</h2>
    <div class="by">
      <el-row :gutter="20">
        <el-col :span="16">
         <div class="bodyl">

      <div class="part1">

        <el-row :gutter="16">
          <el-col :span="5">
            <el-image class="photo" :src="url" ></el-image>
          </el-col>
          <el-col :span="10">
            <div class="intro1" v-for="item in items" :item="item">
            <span class="c1" >{{item.title}}: {{item.msg}}</span>
            </div>
          </el-col>
          <el-col :span="1">
         <div class="show">

          <br/>
          <el-rate v-model="movieScore" disabled show-score size="large"
                   text-color="#ff9900"></el-rate>
         </div>
        </el-col>
      </el-row>
    </div>
        <div class="part2">

            <div  style="margin-left:10px;margin-right: 10px;float: left">
              <el-button size="small" >想看</el-button>
            </div>
            <div  style="float: left">
              <el-button size="small" >看过</el-button>
            </div>
            <div>
              <Star1></Star1>
            </div>

          <div class="clear"></div>

            <div style="margin-right: 20px;float: left">
            <el-icon style="vertical-align: middle"><chat-line-round /></el-icon>
              <span>评论 </span>
            </div>
            <div style="margin-right: 20px;float: left">
              <el-icon style="vertical-align: middle"><edit-pen /></el-icon>
              <span>写影评</span>
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

        </div>

        <div class="part5">
          <h3>{{movieName}}的影评· · · · · ·</h3>

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
import Star1 from "@/components/Star1";
import moviesidesort from "@/components/moviesidesort";

export default {
  name: "Movie",
  /*这里的data只是显示用，可以全注释掉*/
  data() {
    return {
      movieName: '青春变形记',
      url: require('../image/image1.jpg'),
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
          msg: 'type'
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
        {
          title: '制片国家/地区',
          msg: 'movieCry'
        }
      ],

    }
  },
  components: {moviesidesort, Star1}
}
</script>

<style scoped>
.body {
  width: 70%;
  margin: 0 auto;
}


span {
  color: #484849;
}


.photo {

  height: 190px;
}
.show {
  border-left: solid 1px var(--el-border-color-base);
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
</style>