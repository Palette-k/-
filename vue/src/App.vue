<template >
  <div id="app">
<!--    <Menu></Menu>-->
    <router-view></router-view>
  </div>
</template>

<script>



export default {
  name: 'App',
 /* components:{
    // eslint-disable-next-line vue/no-unused-components
    Layout,
  }*/
}
</script>

<style>
#app{
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
}
</style>
