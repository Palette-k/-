<template >
  <div id="app">
    <Menu></Menu>
    <router-view></router-view>
  </div>
</template>

<script>

import Menu from "@/components/menu1";

export default {
  name: 'App',
  components:{
    // eslint-disable-next-line vue/no-unused-components
    Menu,
  }
}
</script>

<style>

</style>
