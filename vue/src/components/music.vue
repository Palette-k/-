<template>
    <div class="demo-image">
        <el-row  >
            <el-col v-for="it in pictureurls"
                    :key="it.id"
                    :span="6"
                    :offset="it.id>0?2:0"
                    class="musiccard"
            >
              <router-link :to="{path:'about',query:{id: it.id}}">
                <el-card  >
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
                    :total="musictotal"
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    class="demo-pagination-block"
            />
            <div class="demonstration">共{{musicPages}}页，共有{{ musictotal}}首音乐</div>
        </div>
    </div>
</template>

<script>

    import request from "@/utils/request";



    export default {

        name: "mid-pic",

        data(){
            return{

                pictureurls:[],

                //总数据条数


                //当前页码
                pageCurrent:1,
                //每页条数
                pageSize: 6,
                //总页数
                musicPages:2,
                musictotal:'',


            }
        },


        methods:{
            getInfo(){      //首页展示影片

                request.get("/works/indexshow?pageCurrent=" + this.pageCurrent + "&pageSize=" + this.pageSize).then(data =>{
                    const res = data.data;
                    console.log(data.data)
                    const musicURL = res.music;
                    console.log(res.music)
                    this.pictureurls = musicURL;
                    this.musicPages = res.musicPages;
                    this.musictotal=res.musicTotal;

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
    .demo-image{
        margin: 0 auto;
        padding-left: 20px;
    }
    .demo-image .block {
        display: inline-block;
        width: 160px;
        object-fit: scale-down;
        height: 170px;

    }
    .demo-image .demonstration {
        display: block;
        color: var(--el-text-color-secondary);
        font-size: 14px;
        text-align: center;
        margin-top: 20px;
        margin-bottom: 20px;
    }
    .demo-pagination-block{
        margin: auto;
        width: 40%;
        position: relative;


    }
    .musiccard{
        margin-left: 20px;
    }

</style>