<template>
    <div class="common-layout">
        <el-container
        style="width: 100%">
            <!--头部菜单-->
            <el-header >
          <menu1></menu1>
            </el-header>
            <!--轮播图-->
       <el-main
       style="margin: 2cm 0cm 2cm 0cm">
           <carousel></carousel>
            </el-main>
            <!--电影区-->
            <el-container>

                <el-aside style="background-color: rgb(238, 241, 246)" >
                    <sidepart></sidepart>
                </el-aside>
                <el-main>
                   <movie></movie>
                </el-main>
                <el-aside  class="el-aside-2"
                        style="">
                    <sidesort></sidesort>
                    </el-aside>

            </el-container>
            <!--音乐区-->
            <el-container
            style="margin-top: 4cm">
                <el-aside style="background-color: rgb(238, 241, 246)" >
                   <musicsidepart></musicsidepart>
                </el-aside>
                <el-main>
                  <music></music>
                </el-main>
                <el-aside  class="el-aside-3"
                           style="">
                    <musicsidesort></musicsidesort>
                </el-aside>
            </el-container>
            <!--书籍区-->
            <el-container
                    style="margin-top: 4cm">
                <el-aside style="background-color: rgb(238, 241, 246)" >
                   <booksidepart></booksidepart>

                </el-aside>
                <el-main>
                    <book></book>
                </el-main>
                <el-aside  class="el-aside-1"
                           style="">
                    <booksidesort></booksidesort>
                </el-aside>
            </el-container>
        </el-container>
    </div>

</template>

<script>


   import carousel from "../components/carousel";
   import menu1 from "../components/menu1";
   import movie from "../components/movie";
   import sidepart from "../components/moviesidepart";
   import sidesort from "../components/moviesidesort";
   import music from "../components/music";
   import musicsidepart from "../components/music-sidepart";
   import musicsidesort from "../components/music-sidesort";
   import book from "../components/book";
   import booksidepart from "../components/book-sidepart";
   import booksidesort from "../components/book-sidesort"
    export default {
        name: "layout-1",
        components: {

           menu1, movie,sidesort,sidepart,carousel,music,musicsidepart,
           musicsidesort,book,booksidepart,booksidesort}



    }
</script>

<style scoped>

      .el-header {
        position: relative;

        color: var(--el-text-color-primary);
    }
    .el-aside {
        width: 240px;
        color: var(--el-text-color-primary);
        background: #fff !important;
        border-right: solid 1px #e6e6e6;
        box-sizing: border-box;
    }
     .el-main {

        padding: 0;
    }

     .el-aside-1{
         width: 240px;
         color: var(--el-text-color-primary);
         background: #fff !important;
         border-left: solid 1px #e6e6e6;
         box-sizing: border-box;
         text-align: center;

     }
      .el-aside-2{
          width: 240px;
          color: var(--el-text-color-primary);
          background: #fff !important;
          border-left: solid 1px #e6e6e6;
          box-sizing: border-box;
          text-align: center;

      }
      .el-aside-3{
          width: 240px;
          color: var(--el-text-color-primary);
          background: #fff !important;
          border-left: solid 1px #e6e6e6;
          box-sizing: border-box;
          text-align: center;

      }


</style>