<template>
    <div style="text-align: center">
        <el-carousel style="
                    margin: 0 auto;
                    height: 50%;
                    width: 60%;
                    text-align: center"
        >
            <el-carousel-item
                    v-for="item in picture"
                    :key="item.id"
                    style="width: 100%;
                                 height: 100%">
                <el-image style="height: 100%;
                                             width: 100%"
                          :src=" item.img"
                ></el-image>
            </el-carousel-item>
        </el-carousel>
    </div>
</template>

<script>
    import request from "@/utils/request";
    export default {
        name: "carousel-1",

        data (){

            return{
                picture:[]
            }
        },
        methods:{
            carouselpicture(){
                request.get("/works/carousel/showPostImgs").then(response=>{
                    const data=response.data;
                    console.log(data)
                    this.picture=data;
                })
            }


        },
        created() {
            this.carouselpicture()
        }
    }
</script>

<style scoped>

</style>