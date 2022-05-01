<template>

  <div class="demo-image">

    <el-row :gutter="20">
      <el-col v-for="it in pictureurls"
              :key="it.movieId"
              :span="5">
        <el-card class="b" shadow="hover" :body-style="{ padding: '0px' }">
            <img
                :src="getImage(it.moviePath)"
                class="block"
            />
          <span class="demonstration">{{it.movieName}} </span>

          <!--评分条-->
          <!--            <el-rate  v-model="it.value"
                                show-score
                                score-template="{value} "
                                disabled/>-->
        </el-card>

      </el-col>
    </el-row>

    <!--分页条-->
    <div class="demo-pagination-block">

      <el-pagination
          v-model:currentPage="currentPage"
          v-model:page-size="pageSize"
          :small="false"
          :disabled="false"
          :background="false"
          layout="prev, pager, next, jumper"
          :total="totalSize"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
      />
      <div class="demonstration">共{{pages}}页，共有{{totalSize}}部电影</div>
    </div>

  </div>

</template>

<script>
import * as midpic from '@element-plus/icons-vue'
import request from "@/utils/request";
import {Ref} from "vue";


export default {

  name: "mid-pic",
  components:midpic,
  data(){
    return{
      //电影名字，评分，图片地址
      pictureurls:[],
      ImgUrl:[],

      //总数据条数
      totalSize: 0,
      //当前页码
      currentPage: 1,
      //每页条数
      pageSize: 6,
      //总页数
      pages:0,



    }
  },


  methods:{
    getInfo(){      //首页展示影片

      request.get("/works/indexshow?pageCurrent=" + this.currentPage + "&pageSize=" + this.pageSize).then(data =>{
        //console.log(data.data);
        const res = data.data;
        const movieURL = res.movies;
        console.log(movieURL);
        this.pictureurls = movieURL;

        //  console.log(this.pictureurls[0].moviePath);

        /* this.pictureurls.forEach( imgURL =>{
           console.log(imgURL.moviePath);
           imgURL.moviePath = "http://localhost:8001/" +  imgURL.moviePath;
           console.log(imgURL.moviePath);
         })*/

        //   console.log(res.pageTotal);
        this.totalSize = res.pageTotal;
        this.pages = res.pages;

        // this.getImage()
      })
    },
    getImage(img){

      return  require('../assets/gallery/' + img + '.jpg');
      /* this.pictureurls.forEach( imgURL =>{
         console.log(imgURL.moviePath);
         imgURL.moviePath = require('@/'+ imgURL.moviePath);
         console.log(imgURL.moviePath);
       })*/
    },


    handleCurrentChange(page){
      this.currentPage = page;
      console.log(this.currentPage);
      this.getInfo();
    },

    handleSizeChange(size){
      this.pageSize = size;
      console.log(this.pageSize);
      this.getInfo();
    },





  },


  //生命周期：挂载完成，可以访问当前this实例
  mounted() {
    this.getInfo();
    //  this.getImage();
  },


}



</script>

<style scoped>

/*  .demo-image .block {
      padding: 30px 0;
      text-align: center;

      display: inline-block;
      width: 20%;
      box-sizing: border-box;
      vertical-align: top;
  }
*/

.b {
  margin-bottom: 40px;
}
.block {
  display: block;
  width: 100%;
}
/*    .demo-image .demonstration {
        display: block;
        color: var(--el-text-color-secondary);
        font-size: 14px;
        margin-bottom: 20px;
    }*/

.demo-image .demonstration {
  display: block;
  color: var(--el-text-color-secondary);
  font-size: 14px;
  text-align: center;
  margin-top: 10px;
  margin-bottom: 10px;
}

.demo-image .blocks {
  padding: 30px 0;
  text-align: center;
  display: inline-block;
  width: 20%;
  box-sizing: border-box;
  vertical-align: top;
}


.demo-pagination-block .demonstration {
  margin-top: 10px;
  margin-bottom: 16px;
  text-align: center;
}

.demo-pagination-block{
  margin: auto;
  width: 40%;

}

</style>