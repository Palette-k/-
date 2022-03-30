<template>
  <div class="demo-image">
    <el-row style="margin-top: 10%">
      <el-col v-for="it in pictureurls"
              :key="it.id"
              :span="6"
              :offset="it.id>0?3:0">
        <el-card :body-style="{ padding: '0px' }">
          <img
              :src="it.idviews"
              class="block"
          />
          <span class="demonstration">{{it.id}}</span>
          <el-rate  v-model="it.value"
                    show-score
                    score-template="{value} "
                    disabled/>
        </el-card>

      </el-col>
    </el-row>

    <el-row style="margin-top: 10%">
      <el-col v-for="it in pictureurls"
              :key="it.id"
              :span="6"
              :offset="it.id>0?3:0">
        <el-card :body-style="{ padding: '0px' }">
          <img
              :src="it.idviews"
              class="block"
          />
          <span class="demonstration">{{it.id}}</span>
          <el-rate  v-model="it.value"

                    show-score
                    score-template="{value} "
                    disabled/>
        </el-card>

      </el-col>
    </el-row>
  </div>
</template>

<script>
import * as book from "@element-plus/icons-vue"
import {ref} from "vue";
export default {
  name: "book-1",
  components:book,
  data(){
    return{
      pictureurls: [{id: 0,value:ref(2), idviews: require('../assets/gallery/image1.jpg'),},
        {id: 1, value:ref(3.5),idviews: require('../assets/gallery/image2.jpg'),},
        {id: 2, value:ref(4),idviews: require('../assets/gallery/image3.jpg'),},
        /*{id: 3,value:ref(2), idviews: require('../assets/gallery/image4.jpg'),},
        {id: 4, value:ref(4),idviews: require('../assets/gallery/image1.jpg'),},*/],

    }
  }
}
</script>

<style scoped>
.demo-image .block {
  display: inline-block;
  width: 100%;
  object-fit: scale-down;
}
.demo-image .demonstration {
  display: block;
  color: var(--el-text-color-secondary);
  font-size: 14px;
  text-align: center;
  margin-top: 20px;
  margin-bottom: 20px;
}


</style>