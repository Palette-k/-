<template>
  <div class="star">
    <span style="color: black;vertical-align: middle"> 评 价: </span>
<!--disable表示只读，show-score显示分值-->
    <el-rate style="vertical-align: middle" v-model="score" :colors="colors" :texts="texts" show-text></el-rate>
  </div>
</template>

<script>
export default {
  name: "Star1",
  data() {
    return {
      score: '',
      colors: ['#99A9BF','#F7BA2A','#FF9900'],
      texts: ['极差','较差','一般','较好','极好']
    }
  },
  methods: {

  }
}
</script>

<style scoped>
.star {
  text-align: center;
  border-right: solid 1px var(--el-border-color-base);
  display: inline-block;
  width: 50%;
  box-sizing: border-box;
}
.star:last-child {
  border-right: none;
}
.star {
  display: block;
  color: var(--el-text-color-secondary);
  font-size: 14px;
  margin-bottom: 20px;
}
</style>