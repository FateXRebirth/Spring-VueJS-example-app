<template>
  <div class="block">
    <el-carousel height="360px" :style="{ height: height + 'px'}">
      <el-carousel-item v-for="image in images" :key="image">
        <img :src="image" :style="{ width: width + 'px'}">
      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<script>
export default {
   props: ['images', 'width', 'height'],
}
</script>


<style lang="scss" scoped>
.block {
  width: 100%;
}
.el-carousel {
  & img {
    height: auto;
  }
}

.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}
</style>
