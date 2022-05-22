<template>
    <div class="demo-image">
        <el-row style="margin-top: 10%">
            <el-col v-for="it in pictureurls"
            :key="it.id"
            :span="6"
            :offset="it.id>0?2:0">
              <router-link :to="{path:'about',query:{id: it.id}}">
                <el-card :body-style="{ padding: '0px' }">
                    <img
                            :src="it.path"
                            class="block"
                    />
                    <span class="demonstration">{{it.name}}</span>

                </el-card>
              </router-link>
            </el-col>
        </el-row>
        <div >
        <el-pagination
                v-model:currentPage="pageCurrent"
                v-model:page-size="pageSize"
                layout="prev, pager, next, jumper"
                :total="bookTotal"
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                style="margin: 0 auto;width: 40%"
        />
        <div class="demonstration">共{{bookPages}}页，共有{{ bookTotal}}本书</div>
    </div>
    </div>
   </template>

<script>


    import request from "@/utils/request";

    export default {

        name: "mid-pic",

        data(){
            return{
                //电影名字，评分，图片地址
                pictureurls:[],
                ImgUrl:[],

                //总数据条数


                //当前页码
                pageCurrent:1,
                //每页条数
                pageSize: 6,
                //总页数
              bookPages:2,
              bookTotal:'',


            }
        },


        methods:{
            getInfo(){      //首页展示影片

                request.get("/works/indexshow?pageCurrent=" + this.pageCurrent + "&pageSize=" + this.pageSize).then(data =>{
                    const res = data.data;
                    console.log(data.data)
                    const bookURL = res.book;
                  //  console.log(res.book)
                    this.pictureurls = bookURL;
                  //  console.log(res.bookTotal);
                    this.bookPages = res.bookPages;
                    this.bookTotal=res.bookTotal;

                })
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
    .demo-image .block {
        display: inline-block;
        width: 100%;
        object-fit: scale-down;
    }
    .demo-image .demonstration {
        display: block;
        color: var(--el-text-color-secondary);
        font-size: 14px;
        text-align: center;
        margin-top: 20px;
        margin-bottom: 20px;
    }


</style>