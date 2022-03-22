<template>
    <el-menu
            :default-active="activeIndex"
            class="el-menu-demo"
            mode="horizontal"
            @select="handleSelect"
    >

        <div class="mingzi">
            <el-image style="width: 20px; height: 20px"
                    :src="imgurl"
                      :fit="fill"></el-image>
        </div>

        <el-icon class="is-loading" style="vertical-align: middle;">
            <loading />
        </el-icon>
        <el-col :span="6">
            <el-input class="i-input" placeholder="Please Input" >
                <el-icon><search></search></el-icon>
            </el-input>

        </el-col>
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
        <el-menu-item index="3" disabled>Info</el-menu-item>
        <el-menu-item index="4">Orders</el-menu-item>
    </el-menu>
    <div class="demo-image">
        <div v-for="fit in fits" :key="fit" class="block">
            <span class="demonstration">{{ fit }}</span>
            <el-image
                    style="width: 100px; height: 100px"
                    :src="url"
                    :fit="fit"
            ></el-image>
        </div>
    </div>

</template>


<script>

    import {Search} from "@element-plus/icons-vue";

    export default {
        // eslint-disable-next-line vue/multi-word-component-names
        name: "Menu-1",
        components: {Search},
        data() {
            return {
                activeIndex: '1',
                activeIndex2: '1',
                fits :['fill', 'contain', 'cover', 'none', 'scale-down'],
                url :
                    'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
                imgurl:require('../assets/logo.png')

            };


        },
        methods: {
            handleSelect(key, keyPath) {
                console.log(key, keyPath);
            }
        }
    }
</script>


<style>
    .i-input {
        margin: 10px auto auto auto;
    }
    demo-image .block {
        padding: 30px 0;
        text-align: center;
        border-right: solid 1px var(--el-border-color-base);
        display: inline-block;
        width: 20%;
        box-sizing: border-box;
        vertical-align: top;
    }
    .demo-image .block:last-child {
        border-right: none;
    }
    .demo-image .demonstration {
        display: block;
        color: var(--el-text-color-secondary);
        font-size: 14px;
        margin-bottom: 20px;
    }

</style>