<template>
  <div class="block">
    <el-carousel height="360px">
      <el-carousel-item v-for="item in 8" :key="item">
        <img :src="'images/kv/kv' + item + '.jpeg'" alt="">
      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<style lang="scss" scoped>
.block {
  width: 100%;
}
.el-carousel {
  & img {
    width: 1200px;
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
