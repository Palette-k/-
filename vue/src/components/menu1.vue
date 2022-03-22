<template>
    <el-affix>
    <el-menu
            :default-active="activeIndex"
            class="el-menu-demo"
            mode="horizontal"
            @select="handleSelect"
            style="width: 100%;height: 100%;">
        <!--logo标志-->
        <el-image style="width: 50px; height: 50px;margin-right: 10px"
                  :src="img1"
        ></el-image>

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
        <el-menu-item index="3">登录</el-menu-item>
        <el-menu-item>
        </el-menu-item>
        <!--头像-->
        <el-button type="text"
                   @click="drawer = true"
                   style="margin-top: 10px">
            <el-avatar :icon="UserFilled"
                       :size="small"></el-avatar>
        </el-button>

        <el-drawer v-model="drawer" title="I am the title" :with-header="false">
            <el-container>
                <el-header style="border-bottom: solid 1px #eeeff3">
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
        </el-affix>
</template>

<script>
    import * as menu from '@element-plus/icons-vue'
    import {Search} from "@element-plus/icons-vue";
    import {UserFilled} from "@element-plus/icons-vue";
    import {ref} from "vue";
    export default {
        name: "menu-1",
        components:menu,
        data(){
            return{
                Search,
                UserFilled,
                activeIndex: '1',
                activeIndex2: '1',
                drawer:ref(false),

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

</style>