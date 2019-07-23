<template>
  <a :href=" 'car/' + Car.carID" style="text-decoration: none">
    <el-card shadow="hover" class="Car">
      <img :src="Car.image" class="Image">
      <hr class="hr-20">
      <p class="Name">{{ Car.brandName + ' ' + Car.seriesName + ' ' + Car.categoryName + ' ( ' + Car.year + ' ) ' }}</p>
      <hr class="hr-10">
      <p class="Detail">{{ Car.year + '年 | ' + Car.mileage + '公里 | ' + Car.city }}</p>
      <hr class="hr-20">
      <span class="Price">{{ Car.price }}萬</span>
    </el-card>
  </a>
</template>

<script>
export default {
  props: ['Car'],
}
</script>

<style lang="scss" scoped>
.Car {
  position: relative;
  .Image {
    max-width: 100%;
    display: block;
  }
  .Name {

  }
  .Detail {
    color: #999999;
    font-size: 13px;
  }
  .Price {
    position: absolute;
    right: 0;
    bottom: 0;
    margin-right: 15px;
    margin-bottom: 15px;
    color: red;
    font-size: 20px;
    font-weight: 500;
  }
}
</style>
