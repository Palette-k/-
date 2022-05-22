<template>
    <h1 style="font-family: 楷体;
                              font-size: x-large;
                              text-align: center;
                              color: #c45656">影片分区</h1>

    <div style="text-align: center ;margin: 10px 0 10px 0">

      <div>

          <ul v-for="item in children"
              :key="item.id"
              class="tagul">

              <router-link :to="{path:'movie',query:{tagcatelogId: item.id,catelogId:item.catelogId}}">
            <el-link type="primary"
                     style="margin: 2px 2px 2px 2px"
            >
              {{ item.tagName }}
            </el-link>
              </router-link>

          </ul>


      </div>



    </div>



</template>

<script>
    import * as leftside from '@element-plus/icons-vue'
    import request from "@/utils/request";
    export default {
        name: "side-part",
        components:leftside,
        data(){

            return{
              children:{}
            }
        },
      methods:{
          getMovieTag(){
             request.get("/works/tagcategory/showCateTree").then(res => {
                let data = res.data[0]
                this.children = data.children

               console.log("数据",this.children)
             })
          }
      },
      created() {
          this.getMovieTag();
      }
    }
</script>

<style scoped>
* {


  color: var(--el-text-color-primary);
  background: #fff !important;

  box-sizing: border-box;
  text-align: center;
}

.tagul{
  display: inline-block;
  text-align: center;
}
</style>