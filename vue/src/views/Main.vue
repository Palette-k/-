<template>
<!--    <div>首页</div>-->
<!--  <router-link to="/login">退出</router-link>-->
 <Menu></Menu>
  <Carousel></Carousel>
  <Fenqu></Fenqu>
  <Mid></Mid>
  <Paihang></Paihang>
</template>

<script>

import Carousel from "@/components/Carousel";
import Fenqu from "@/components/Fenqu";
import Mid from "@/components/Mid";
import Paihang from "@/components/Paihang";
import Menu from "@/components/Menu";
export default {

  //eslint-disable-next-line vue/multi-word-component-names
  name: "Main",
  components: {Menu, Paihang, Mid, Fenqu, Carousel,}
}
</script>

<style scoped>

</style>