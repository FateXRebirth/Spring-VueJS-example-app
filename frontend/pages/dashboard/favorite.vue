<template>
  <section class="container">
    <hr class="hr-30">
      <Breadcrumb />
    <hr class="hr-30">
    <el-container>
      <el-aside width="200px">
        <SideMenu />
      </el-aside>
      <el-main>   
        <Header title="Favorite" />
        <hr class="hr-30">
        <el-row :gutter="10">
          <el-col :span="8" v-for="car in Cars" :key="car.cover">
            <Car :img="car.cover"/>
            <hr class="hr-10">
          </el-col>
        </el-row>
      </el-main>
    </el-container>
  </section>
</template>

<script>
import SideMenu from '~/components/SideMenu.vue';
import Breadcrumb from '~/components/Breadcrumb.vue';
import Header from '~/components/Header.vue';
import Car from '~/components/Car.vue';

export default {
  components: {
    SideMenu,
    Breadcrumb,
    Header,
    Car
  },
  middleware: 'auth',
  data() {
    return {
      Cars: [
        {
          cover: '/images/sample/car-sample1.jpg'
        },
        {
          cover: '/images/sample/car-sample2.jpg'
        },
        {
          cover: '/images/sample/car-sample3.jpg'
        },
        {
          cover: '/images/sample/car-sample1.jpg'
        },
        {
          cover: '/images/sample/car-sample2.jpg'
        },
        {
          cover: '/images/sample/car-sample3.jpg'
        }
      ]
    }
  }
}
</script>

<style lang="scss" scoped>
.el-container {
  min-height: 750px;
}
</style>
