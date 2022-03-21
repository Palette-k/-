<template>
    <div class="demo-image">
        <div v-for="it in pictureurls"
             :key="it.id"
             class="block">

            <el-image
                    style="width: 100px; height: 120px;object-fit: scale-down"
                    :src="it.idviews"
            ></el-image>
            <span class="demonstration">{{ it.id }}</span>
            <el-rate  v-model="it.value"
                      show-score
                      score-template="{value} "
                      disabled/>
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
            <el-rate  v-model="it.value"
                      show-score

                      score-template="{value} "
                      disabled/>
        </div>
    </div>
</template>

<script>
    import * as music from "@element-plus/icons-vue"
    import {ref} from "vue";
    export default {
        name: "music-1",
        components:music,
        data(){
            return{
                pictureurls: [{id: 0,value:ref(2), idviews: require('../assets/gallery/image1.jpg')},
                    {id: 1, value:ref(3),idviews: require('../assets/gallery/image2.jpg')},
                    {id: 2, value:ref(4),idviews: require('../assets/gallery/image3.jpg')},
                    {id: 3,value:ref(2), idviews: require('../assets/gallery/image4.jpg')},
                    {id: 4, value:ref(4),idviews: require('../assets/gallery/image1.jpg')},],

            }
        }
    }
</script>

<style scoped>
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