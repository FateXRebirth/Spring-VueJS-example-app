<template>
  <section class="container">
    <!-- <div>
      <logo/>
      <h1 class="title">
        Nuxt
      </h1>
      <h2 class="subtitle">
        My fine Nuxt.js project
      </h2>
      <div class="links">
        <a href="https://nuxtjs.org/" target="_blank" class="button--green">Documentation</a>
        <a href="https://github.com/nuxt/nuxt.js" target="_blank" class="button--grey">GitHub</a>
      </div>
    </div> -->
    <Carousel/>
    <hr class="hr-50">
    <Banner type="left" content="車的好壞，由你決定！" :active="true" :img="3"/>
    <hr class="hr-50">
    <section class="Rank">
      <Header title="最新網路好店中古車" subTitle="看更多網路好店中古車" url="" />
      <hr class="hr-30">
      <el-row :gutter="10">
        <el-col :span="8" v-for="car in Cars" :key="car.cover">
          <Car :img="car.cover"/>
        </el-col>
      </el-row>
    </section>
    <hr class="hr-50">
    <Banner type="right" content="專注完美，近乎苛求！" :active="true" :img="2"/>
    <hr class="hr-50">
    <section class="Rank">
      <Header title="最新平行輸入中古車" subTitle="看更多平行輸入中古車" url="" />
      <hr class="hr-30">
      <el-row :gutter="10">
        <el-col :span="8" v-for="car in Cars" :key="car.cover">
          <Car :img="car.cover"/>
        </el-col>
      </el-row>
    </section>
    <hr class="hr-50">
  </section>
</template>

<script>
import Logo from '~/components/Logo.vue'
import Carousel from '~/components/Carousel.vue';
import Banner from '~/components/Banner.vue';
import Car from '~/components/Car.vue';
import Header from '~/components/Header.vue';

export default {
  components: {
    Logo,
    Carousel,
    Banner,
    Car,
    Header
  },
  mounted(redirect) {
    setTimeout(function() {
      // window.location.href = 'dashboard';
    }, 3000)
  },
  data() {
    return {
      Cars: [
        {
          cover: 'images/sample/car-sample1.jpg'
        },
        {
          cover: 'images/sample/car-sample2.jpg'
        },
        {
          cover: 'images/sample/car-sample3.jpg'
        }
      ]
    }
  }
}
</script>

<style lang="scss" scoped>
// .container
// {
//   min-height: 100vh;
//   display: flex;
//   justify-content: center;
//   align-items: center;
//   text-align: center;
// }
// .title
// {
//   font-family: "Quicksand", "Source Sans Pro", -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif; /* 1 */
//   display: block;
//   font-weight: 300;
//   font-size: 100px;
//   color: #35495e;
//   letter-spacing: 1px;
// }
// .subtitle
// {
//   font-weight: 300;
//   font-size: 42px;
//   color: #526488;
//   word-spacing: 5px;
//   padding-bottom: 15px;
// }
// .links
// {
//   padding-top: 15px;
// }
</style>
