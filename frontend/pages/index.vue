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
    <Carousel :images="Images" width="1200" height="360"/>
    <hr class="hr-50">
    <Banner type="left" content="車的好壞，由你決定！" :active="true" :img="3"/>
    <hr class="hr-50">
    <section class="Rank">
      <Header title="最新網路好店中古車" subTitle="看更多網路好店中古車" url="" />
      <hr class="hr-30">
      <el-row :gutter="10">
        <el-col :span="8" v-for="car in Cars" :key="car.carID">
          <Car :Car="car" />
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
        <el-col :span="8" v-for="car in Cars" :key="car.carID">
          <Car :Car="car" />
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
  async asyncData({ app, store, route }) {
    let Result;
    Result = await app.$axios({
      method: 'post',
      url: '/api/cars',
      data: {
        brand: 0,
        series: 0,
        category: 0,
        priceMin: 0,
        priceMax: 0,
        yearMin: 0,
        yearMax: 0,
        mileageMin: 0,
        mileageMax: 0,
        city: 0,
        transmission: 0,
        gearType: 0,
        gasType: 0,
        engineDisplacement: 0,
        passenger: 0,
        color: 0,
        keyword: ""
      }
    })
    if(Result.data.returnCode == 0) {
      return {
        Cars: Result.data.returnData.cars
      }
    } else {
        throw new Error(Result.data.returnMessage);
    }
  },
  data() {
    return {
      Cars: [],
      Images: [
        {
          name: "kv1.jpeg",
          url: "/images/kv/kv1.jpeg",
        },
        {
          name: "kv2.jpeg",
          url: "/images/kv/kv2.jpeg",
        },
        {
          name: "kv3.jpeg",
          url: "/images/kv/kv3.jpeg",
        },
        {
          name: "kv4.jpeg",
          url: "/images/kv/kv4.jpeg",
        },
      ],
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
