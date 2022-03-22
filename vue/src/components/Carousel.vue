<template>
  <el-main style="height:400px">
    <div style="text-align: center">
      <span class="demonstration">首页</span>
      <el-carousel style="margin: 1cm 2cm 2cm 8cm;
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
  </el-main>
</template>

<script>


export default {
  name: "Carousel",
  data() {
    return {

      pictureurls: [{id: 0, idviews: require('../image/gallery/image1.jpg')},
        {id: 1, idviews: require('../image/gallery/image2.jpg')},
        {id: 2, idviews: require('../image/gallery/image3.jpg')},
        {id: 3, idviews: require('..//image/gallery/image4.jpg')},
        {id: 4, idviews: require('../image/gallery/image1.jpg')},],
    }
  },
}
</script>

<style scoped>

</style>