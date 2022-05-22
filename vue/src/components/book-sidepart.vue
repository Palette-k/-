<template>
    <h1 style="font-family: 楷体;
                              font-size: x-large;
                              text-align: center;color: #3a8ee6">书籍分区</h1>
<!--    <h2 style="text-align: center;font-size: large;color: #85ce61">文学</h2>-->
    <div style="text-align: center ;margin: 2px 2px 2px 2px">


      <div>

        <ul v-for="item in children"
            :key="item.id"
            class="tagul">

          <router-link :to="{path:'movie',query:{tagcatelogId: item.id,catelogId:item.catelogId}}">
            <el-link type="warning"
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
    import * as bleftside from '@element-plus/icons-vue'
    import request from "@/utils/request";
    export default {
        name: "bside-part",
        components:bleftside,
        data(){
            return{
              children:{}
            }
        },
      methods:{
        getBookTag(){
          request.get("/works/tagcategory/showCateTree").then(res => {
            let data = res.data[2]
            this.children = data.children

            console.log("数据",this.children)
          })
        }
      },
      created() {
        this.getBookTag()
      }
    }
</script>

<style scoped>
.tagul{
  display: inline-block;
  text-align: center;
}
</style>