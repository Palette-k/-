<template>
  <div  class="body">
  <div class="background">
  <div class="content">
    <h1 style="font-size: 60px;margin-bottom: 0" >PING书影音</h1>
    <p style="font-size: 20px">{{msg}}
    <br>
      {{msg1}}
    </p>
  </div>
  </div>
    <a href="/layout" target="_self">Start</a>
  </div>
</template>

<script>


export default {
  name: "Home",

  data() {
    return {
      msg: "一个可以给书籍、电影、音乐打分评论的网站",
      msg1:"页面简洁、无广告、易上手"
    }
  },
}
</script>

<style scoped>
.background {
  width: 100%;
  height: 100%;
  background-image: url("../image/画板.png");
  background-size: cover;
  position: absolute;
  z-index: -1;
  background-repeat: no-repeat;
}

.content {
  position: fixed;
  left: 35%;
  bottom: 20%;

  line-height: 1.6;
  color: #ffffff;
  text-shadow: 2px 2px 4px #000000;
  text-align: center

}
a{
  text-decoration: none;
  position: absolute;
  left: 50%;
  bottom: 13%;
  transform: translate(-50%,-50%);
  font-size: 16px;
  background: linear-gradient(90deg,#03a9f4,#f441a5,#ffeb3b,#03a9f4);
  background-size: 300%;
  width: 35px;
  height: 35px;
  line-height: 100px;
  text-align: center;
  text-shadow: 2px 2px 4px #000000;
  color: #fff;
  border-radius: 50px;
  z-index: 1;
}
a::before{
  content: "";
  position: absolute;
  left: -5px;
  right: -5px;
  top:-5px;
  bottom: -5px;
  /*background: linear-gradient(90deg,#03a9f4,#f441a5,#ffeb3b,#03a9f4);*/
  background: linear-gradient(to top, #fbc2eb 0%, #a6c1ee 100%);
  background-size: 300%;
  border-radius: 50px;
  filter: blur(20px);
  z-index: -1;
}
a:hover{
  animation: sun 12s infinite;
}
@keyframes  sun{
  100%{
    background-position: 400% 0;
  }

}
</style>