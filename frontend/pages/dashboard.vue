<template>
  <main class="dashboard">   
    <div class="container">
      <!-- <section class="dashboard__header">
        <logo/><h1>NUXT</h1>
        <h3>Universal Vue.js Applications</h3>
      </section> -->
      <!-- <hr class="hr-30"> -->
      <div class="dashboard__nav">
    
      </div>
      <div class="dashboard__banner">
        <Banner type="left" content="Best Cars" :img="1"/>
        <Banner type="right" content="Best Price" :img="2"/>
        <Banner type="left" content="Best Quality" :img="3"/>
        <Banner type="right" content="Best Service" :img="4"/>
        <Banner type="left" content="Best Choice" :img="5"/>
        <Banner type="right" content="Best Feedback" :img="6"/>
      </div>
    </div>
  </main>
</template>

<script>
import Logo from '~/components/Logo.vue';
import Carousel from '~/components/Carousel.vue';
import NavMenu from '~/components/NavMenu.vue';
import Banner from '~/components/Banner.vue';

export default {
  components: {
    Logo,
    Carousel,
    NavMenu,
    Banner,
  },
}
</script>

<style lang="scss" scoped>

</style>
