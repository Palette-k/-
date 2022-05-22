<template xmlns:el-col="http://www.w3.org/1999/html">
    <!--固定菜单-->
    <el-affix>
    <el-menu
            :default-active="activeIndex"

            mode="horizontal"
            @select="handleSelect"
            class="menu"
            >

        <!--logo标志-->
        <el-col :span="1">
        <el-image style="width: 50px; height: 50px;margin-right: 10px"
                  :src="logo"
        ></el-image>
        </el-col>
        <!--输入框-->
        <el-col :span="6" style="margin:0 5cm 0 5cm;position: relative">
            <el-input class="i-input"
                      placeholder="Please Input"
                      v-model="message"
                      @keydown.enter="toSearch()"
            >

                <el-button>
                <!--搜索标志-->
                <template #suffix>
                    <el-icon style="top: 2px"><Search /></el-icon>
                </template>
                </el-button>

            </el-input>
        </el-col>
        <!--menu-->
        <el-menu-item index="1" style="color: #c45656 !important; font-size: x-large;">
          <router-link :to="{path:'movie',query:{catelogId:40}}">
               电影
          </router-link>
        </el-menu-item>

        <el-menu-item index="2" style="color: #53b83e ;font-size: x-large">
          <router-link :to="{path:'movie',query:{catelogId:41}}">
            音乐
          </router-link>
        </el-menu-item>
        <el-menu-item index="3" style="color: #3575e6;font-size: x-large">
          <router-link :to="{path:'movie',query:{catelogId:42}}">
          读书
          </router-link>
        </el-menu-item>
        <el-menu-item v-if="this.$store.state.id == ''" index="4"><a href="/login">登录</a></el-menu-item>
        <personal-int >
        </personal-int>

    </el-menu>
        </el-affix>
</template>

<script>

    import {Clock,Avatar,UserFilled,Search,ArrowRightBold,Message,} from '@element-plus/icons-vue'
    import {ref} from "vue";
    import cookie from "js-cookie";
    import PersonalInt from "./personal";
    export default {
        name: "menu-1",
        components: {PersonalInt},
        data(){
            return{
                Clock,
                Avatar,
                Message,
                Search,
                ArrowRightBold,
                UserFilled,
                activeIndex: '1',
                activeIndex2: '1',
                drawer:ref(false),
                logo:require("../assets/gallery/2.png"),
                username:'',
                message:''

            }
            },

        methods:{
            handleSelect(key, keyPath) {
                console.log(key, keyPath);
            },
          //头部显示登录信息
          getLogin() {
            let token = cookie.get('token');
            if (token) {
              this.username = cookie.get('username');
              console.log(this.username);
            }
          },
          toSearch(){
            this.$router.push({
              path: '/movie',
              // name: 'mallList',
              query: {
                keyword: this.message
              }
            })

          }
        },
      created() {
        this.getLogin()
      }
    }
</script>

<style scoped>
.el-drawer .el-row{
    margin-bottom: 12%;

}
    .menu{
        align-items: center;
    }
    a{
        text-decoration: none;

    }
</style>