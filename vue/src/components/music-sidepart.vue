<template>
    <h1 style="font-family: 楷体;
                              font-size: x-large;
                              text-align: center;color: #b88230">音乐分区</h1>
    <div style="margin:0 auto">
      <div>

        <ul v-for="item in children"
            :key="item.id"
            class="tagul">

          <router-link :to="{path:'movie',query:{tagcatelogId: item.id,catelogId:item.catelogId}}">
            <el-link type="success"
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
    import * as mleftside from '@element-plus/icons-vue'
    import request from "@/utils/request";
    export default {
        name: "musicside-part",
        components:mleftside,
        data(){
            return{
              children:{}
            }
        },
        methods:{
          getMusicTag(){
            request.get("/works/tagcategory/showCateTree").then(res => {
              let data = res.data[1]
              this.children = data.children

              console.log("数据",this.children)
            })
          }
        },
      created() {
          this.getMusicTag()
      }
    }
</script>

<style scoped>
.tagul{
  display: inline-block;
  text-align: center;
}
</style>