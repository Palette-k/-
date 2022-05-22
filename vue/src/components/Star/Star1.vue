<template>
  <div class="star">
    <span style="color: black;vertical-align: middle"> 评 价: </span>
<!--disable表示只读，show-score显示分值-->
    <el-rate style="vertical-align: middle"
             v-model="score" @change="changeRate(score)"
             :colors="colors" :texts="texts" show-text></el-rate>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "Star1",
  props:["mid"],
  computed:{
    id() {
      return this.mid
    }
  },
  data() {
    return {
      score: '',
      colors: ['#99A9BF','#F7BA2A','#FF9900'],
      texts: ['极差','较差','一般','较好','极好']
    }
  },
  methods: {
    changeRate(score){
      let s ={}
      s.userId =this.$store.state.id
      s.worksId =this.id
      s.score =score
      this.postRate(s)
    },
    postRate(score){
        request.post("/works/auth/score",score).then(msg =>{
          if(msg.code === 200) {
            console.log(msg);
            this.$message.success("评价成功！");
          }else if(msg.code === 406) {
              this.$message.warning(msg.message)
          }else {
            console.log(msg);
            this.$message.warning("您已经给这部作品评过分了！");
          }

      })
    }
  }
}
</script>

<style scoped>
.star {
  text-align: center;
  border-right: solid 1px var(--el-border-color);
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
  margin-bottom: 15px;
}

</style>