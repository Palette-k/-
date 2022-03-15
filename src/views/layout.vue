<template>
    <div class="common-layout">
        <el-container
                class= "lass=layout-container-demo"
                style="height: 1000px; border: 1px solid #eee" >
            <!--头部菜单-->
            <el-header >

                <el-menu
                        :default-active="activeIndex"
                        class="el-menu-demo"
                        mode="horizontal"
                        @select="handleSelect"
                        style="">

                        <!--logo标志-->
                    <el-image style="width: 50px; height: 50px"
                              :src="img1"
                    ></el-image>

                    <el-icon
                            class="is-loading"
                            style=" vertical-align:baseline ">
                        <loading />
                    </el-icon>
                    <!--输入框-->
                    <el-col
                            :span="6"
                            style="margin-top: 12px;margin-right: 9cm">
                        <el-input class="i-input"
                                  placeholder="Please Input"
                                 >
                            <template #suffix>
                                <el-icon style="top: 10px"><Search /></el-icon>
                            </template>
                        </el-input>
                    </el-col>

                    <!--menu-->


                    <el-menu-item index="1">Processing Center</el-menu-item>
                    <el-sub-menu index="2">
                        <template #title>Workspace</template>
                        <el-menu-item index="2-1">item one</el-menu-item>
                        <el-menu-item index="2-2">item two</el-menu-item>
                        <el-menu-item index="2-3">item three</el-menu-item>
                        <el-sub-menu index="2-4">
                            <template #title>item four</template>
                            <el-menu-item index="2-4-1">item one</el-menu-item>
                            <el-menu-item index="2-4-2">item two</el-menu-item>
                            <el-menu-item index="2-4-3">item three</el-menu-item>
                        </el-sub-menu>
                    </el-sub-menu>

                    <el-menu-item index="3">Orders</el-menu-item>
                    <el-menu-item>

                    </el-menu-item>
                    <el-button type="text"
                               @click="drawer = true"
                               style="margin-top: 10px">
                        <el-avatar :icon="UserFilled"
                                   :size="small"></el-avatar>
                    </el-button>

                    <el-drawer v-model="drawer" title="I am the title" :with-header="false">
                        <el-container>
                            <el-header>
                            <div>账号:</div>
                        </el-header>
                            <el-main>
                                <div>性别：</div>
                            </el-main>
                            <el-main>
                                <div>浏览记录：</div>
                            </el-main>

                        </el-container>
                    </el-drawer>

                </el-menu>


            </el-header>
            <!--轮播图-->
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

            <el-container>
                <el-aside style="background-color: rgb(238, 241, 246)" >
                   <h1 style="font-family: 楷体;
                              font-size: x-large;
                              text-align: center">影片分区</h1>
                    <div style="text-align: center ;margin: 2px 2px 2px 2px">
                        <el-link href="https://element.eleme.io" target="_blank">科幻</el-link>
                        <el-link type="primary">动作</el-link>
                        <el-link type="success">爱情</el-link>
                        <el-link type="warning">悬疑</el-link>
                    </div>
                    <div
                    style="text-align: center">
                        <el-link href="https://element.eleme.io" target="_blank">搞笑</el-link>
                        <el-link type="primary">文艺</el-link>
                        <el-link type="success">青春</el-link>
                        <el-link type="warning">{{message}}</el-link>
                    </div>

                </el-aside>
                <el-main>
                    <div class="demo-image">
                        <div v-for="it in pictureurls"
                             :key="it.id"
                             class="block">

                            <el-image
                                    style="width: 100px; height: 120px;object-fit: scale-down"
                                    :src="it.idviews"
                            ></el-image>
                            <span class="demonstration">{{ it.id }}</span>
                        </div>
                        <div v-for="it in pictureurls"
                             :key="it.id"
                             class="blocks">

                            <el-image
                                    style="width: 100px; height: 100px;
                                           object-fit: scale-down"
                                    :src="it.idviews"
                            ></el-image>
                            <span class="demonstration">{{ it.id }}</span>
                        </div>
                    </div>

                </el-main>

                <el-aside  class="el-aside-1"
                        style="">
                    <h1 style="font-family: 楷体;font-size: x-large">今日排行榜</h1>

                    <div>
                        <el-link href="https://element.eleme.io" target="_blank">default</el-link>


                    </div>
                    <div>
                        <el-link href="https://element.eleme.io" target="_blank">default</el-link>


                    </div></el-aside>

            </el-container>
        </el-container>
    </div>

</template>

<script>

    import * as lay from "@element-plus/icons-vue";
    import {Search,UserFilled} from "@element-plus/icons-vue";
    import { ref } from 'vue';
    export default {
        name: "layout-1",
        components: lay,ref,
        data() {

            return {
                Search,
                UserFilled,
                     message:"恐怖",
                activeIndex: '1',
                activeIndex2: '1',
                img1:require('../assets/gallery/image1.jpg'),
                pictureurls: [{id: 0, idviews: require('../assets/gallery/image1.jpg')},
                    {id: 1, idviews: require('../assets/gallery/image2.jpg')},
                    {id: 2, idviews: require('../assets/gallery/image3.jpg')},
                    {id: 3, idviews: require('../assets/gallery/image4.jpg')},
                    {id: 4, idviews: require('../assets/gallery/image1.jpg')},],
                drawer:ref(false),
            }
        },

        methods: {
            handleSelect(key, keyPath) {
                console.log(key, keyPath);
            },
        }

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

    .el-link {
        margin-right: 8px;
    }
    .el-link .el-icon--right.el-icon {
        vertical-align: text-bottom;
    }
      .demo-image .block {
          padding: 30px 0;
          text-align: center;

          display: inline-block;
          width: 20%;
          box-sizing: border-box;
          vertical-align: top;
      }
      .demo-image .demonstration {
          display: block;
          color: var(--el-text-color-secondary);
          font-size: 14px;
          margin-bottom: 20px;
      }
      .demo-image .blocks {
          padding: 30px 0;
          text-align: center;

          display: inline-block;
          width: 20%;
          box-sizing: border-box;
          vertical-align: top;
      }

</style>