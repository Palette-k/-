<template>

    <div class="demo-image">

        <el-col v-for="it in pictureurls"
                :key="it.id"
                :span="6"
                :offset="it.id>0?2:0">
<!--          {{it.id}}-->
          <router-link :to="{path:'about',query:{id: it.id}}">
          <el-card class="card" >

            <img
                :src="it.path"
                class="block"
            />

            <span class="demonstration">{{it.name}} </span>

                  <!--评分条-->
<!--            <el-rate  v-model="it.value"
                      show-score
                      score-template="{value} "
                 disabled/>-->

          </el-card>
          </router-link>
        </el-col>


            <!--分页条-->
        <div>


        <div   class="demo-pagination-block">

          <el-pagination
              v-model:currentPage="pageCurrent"
              v-model:page-size="pageSize"
              layout="prev, pager, next, jumper"
              :total="movieTotal"
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"

          />
        <div class="demonstration">共{{moviePages}}页，共有{{movieTotal}}部电影</div>
        </div>

    </div>

    </div>


</template>
<script>

    import request from "@/utils/request";
    import about from "@/views/About"


    export default {

        name: "mid-pic",

        data(){
            return{
                //电影名字，评分，图片地址
              pictureurls:[],


              //总数据条数


              //当前页码
                pageCurrent:1,
              //每页条数
              pageSize: 6,
              //总页数
             moviePages:'',
              movieTotal:'',


            }
        },


      methods:{
        getInfo(){      //首页展示影片

       request.get("/works/indexshow?pageCurrent=" + this.pageCurrent + "&pageSize=" + this.pageSize).then(data =>{
            const res = data.data;
            const movieURL = res.movie;
            this.pictureurls = movieURL;
            this.moviePages = res.moviePages;
             this.movieTotal=res.movieTotal;

          })
        },

        handleCurrentChange(page){
           this.currentPage = page;
         //  console.log(this.currentPage);
           this.getInfo();
        },

        handleSizeChange(size){
          this.pageSize = size;
        //  console.log(this.pageSize);
          this.getInfo();
        },


      },


      //生命周期：挂载完成，可以访问当前this实例
      created() {
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
    .demo-image .block {
      display: inline-block;
      width: 100%;
      object-fit: scale-down;
        height: 170px;
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
    margin-top: 20px;
    margin-bottom: 20px;
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

        bottom: 0;

    }
  .card{
    margin-bottom: 20px;

  }

</style>