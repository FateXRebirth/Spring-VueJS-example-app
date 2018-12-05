<template>
  <main class="dashboard">
    <side-menu/>
    <div class="container">
      <section class="dashboard__header">
        <logo/><h1>NUXT</h1>
        <h3>Universal Vue.js Applications</h3>
      </section>
      <hr class="hr-30">
      <div class="dashboard__nav">
        <nav-menu/>
      </div>
    </div>
  </main>
</template>

<script>
import Logo from '~/components/Logo.vue';
import Carousel from '~/components/Carousel.vue';
import NavMenu from '~/components/NavMenu.vue';
import SideMenu from '~/components/SideMenu.vue';

export default {
  components: {
    Logo,
    Carousel,
    NavMenu,
    SideMenu
  },
}
</script>

<style lang="scss" scoped>
.dashboard {
  height: 2000px;
  background: url('https://source.unsplash.com/1920x1080/?car');
  &__header {
    text-align: center;
    color: #35495e;
    h1 {
      display: inline-block;
      margin-left: 20px;
      font-size: 80px;
      font-weight: 400;
    }
    h3 {
      font-size: 40px;
      font-weight: 200;
    }
  }
  &__nav {

  }
}
</style>
