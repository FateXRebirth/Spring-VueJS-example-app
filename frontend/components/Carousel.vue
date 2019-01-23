<template>
  <div class="block">
    <el-carousel height="360px">
      <el-carousel-item v-for="item in 8" :key="item">
        <img :src="'images/kv/kv' + item + '.jpeg'" alt="">
      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<style>
.block {
  width: 100%;
}
</style>
