<template>
    <div style="text-align: center">
        <el-carousel style="
                    margin: 0 auto;
                    height: 50%;
                    width: 50%;
                    text-align: center"
        >
            <el-carousel-item
                    v-for="item in pictureurls"
                    :key="item.id"
                    style="width: 100%;
                                 height: 100%">
                <el-image style="height: 100%;
                                             width: 100%"
                          :src=" item.idviews"
                ></el-image>
            </el-carousel-item>
        </el-carousel>
    </div>
</template>

<script>
    import * as carousel from '@element-plus/icons-vue'
    export default {
        name: "carousel-1",
        components:carousel,
        data (){

            return{
                pictureurls: [{id: 0, idviews: require('../assets/gallery/image1.jpg')},
                    {id: 1, idviews: require('../assets/gallery/image2.jpg')},
                    {id: 2, idviews: require('../assets/gallery/image3.jpg')},
                    {id: 3, idviews: require('../assets/gallery/image4.jpg')},
                    {id: 4, idviews: require('../assets/gallery/image1.jpg')},],
            }
        }
    }
</script>

<style scoped>

</style>