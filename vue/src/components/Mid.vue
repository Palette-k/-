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
</template>

<script>
export default {
  name: "Mid"
}
</script>

<style scoped>

</style>