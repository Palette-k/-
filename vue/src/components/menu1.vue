<template xmlns:el-col="http://www.w3.org/1999/html">
    <!--固定菜单-->
    <el-affix>
    <el-menu
            :default-active="activeIndex"

            mode="horizontal"
            @select="handleSelect"
            >

        <!--logo标志-->
        <el-col :span="1">
        <el-image style="width: 50px; height: 50px;margin-right: 10px"
                  :src="logo"
        ></el-image>
        </el-col>
        <!--输入框-->
        <el-col
                :span="6"
                style="margin-top: 12px;margin-right: 9cm">
            <el-input class="i-input"
                      placeholder="Please Input"
            >
                <!--搜索标志-->
                <template #suffix>
                    <el-icon style="top: 10px"><Search /></el-icon>
                </template>
            </el-input>
        </el-col>
        <!--menu-->
        <el-menu-item index="1" style="color: #c45656; font-size: x-large">电影</el-menu-item>

        <el-menu-item index="2" style="color: #b88230 ;font-size: x-large">音乐</el-menu-item>
        <el-menu-item index="3" style="color: #3a8ee6;font-size: x-large">读书</el-menu-item>
        <el-menu-item index="4">登录</el-menu-item>
        <!--头像-->
        <el-button type="text"
                   @click="drawer = true"
                   style="margin-top: 10px;margin-left: 5%">
            <el-avatar :icon="UserFilled"
                       :size="small"></el-avatar>
        </el-button>
        <!--个人中心-->
        <el-drawer v-model="drawer" class="footer" :with-header="false">
            <el-row >  <el-avatar :icon="UserFilled"
                                  :size='number' style="margin: 0 auto"></el-avatar>
            </el-row >
            <el-row style="border-bottom: solid 1px #c6e2ff;" :gutter="2" >
                <el-col :span="6">
                    <h3>账号</h3>
                </el-col>
                <!--这里要记得导入id-->
                <el-col :span="6">
                    <h5 style="font-family: Arial">记得导入id</h5>
                </el-col>
            </el-row>
            <el-row  style="border-bottom: solid 1px #c6e2ff">
                <el-button type="text">
                    个人中心
                    <el-icon><avatar /></el-icon>
                </el-button>
            </el-row>
            <el-row style="border-bottom: solid 1px #c6e2ff">
                <el-button type="text">历史记录
                    <el-icon><clock /></el-icon></el-button>

            </el-row>
            <el-row style="border-bottom: solid 1px #c6e2ff">
                <el-button type="text" >消息
                    <el-icon><message /></el-icon></el-button>
            </el-row>
            <el-row :align="bottom">
                <el-col :span="12">  <el-button>
                    退出
                    <el-icon><arrow-right-bold /></el-icon>
                </el-button></el-col>
            </el-row>
    </el-drawer>
    </el-menu>
        </el-affix>
</template>

<script>
    import * as menu from '@element-plus/icons-vue'

    import {Clock,Avatar,UserFilled,Search,ArrowRightBold,Message,} from '@element-plus/icons-vue'
    import {ref} from "vue";
    export default {
        name: "menu-1",
        components:menu,
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
                logo:require("../assets/gallery/a28fd3b6488c5b3f406d73b9ad88af2.png")

            }
            },

        methods:{
            handleSelect(key, keyPath) {
                console.log(key, keyPath);
            },

        },
    }
</script>

<style scoped>
.el-drawer .el-row{
    margin-bottom: 12%;

}
</style>