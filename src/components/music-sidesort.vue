<template>
    <h1 style="font-family: 楷体;font-size: x-large">今日排行榜</h1>

    <div>
        <el-link href="https://element.eleme.io" target="_blank">default</el-link>


    </div>
    <div>
        <el-link href="https://element.eleme.io" target="_blank">default</el-link>


    </div>
</template>

<script>
    import * as mrightside from '@element-plus/icons-vue'
    export default {
        name: "side-sort",
        components:mrightside,
    }
</script>

<style scoped>
    .el-link {
        margin-right: 8px;
    }
    .el-link .el-icon--right.el-icon {
        vertical-align: text-bottom;
    }

</style>