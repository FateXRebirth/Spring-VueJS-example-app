<template>
  <main class="dashboard">
    <hr class="hr-30">
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
      <div class="dashboard__banner">
        <banner type="left" content="Best Cars" :img="1"/>
        <banner type="right" content="Best Price" :img="2"/>
        <banner type="left" content="Best Quality" :img="3"/>
        <banner type="right" content="Best Service" :img="4"/>
        <banner type="left" content="Best Choice" :img="5"/>
        <banner type="right" content="Best Feedback" :img="6"/>
      </div>
    </div>
  </main>
</template>

<script>
import Logo from '~/components/Logo.vue';
import Carousel from '~/components/Carousel.vue';
import NavMenu from '~/components/NavMenu.vue';
import SideMenu from '~/components/SideMenu.vue';
import Banner from '~/components/Banner.vue';

export default {
  components: {
    Logo,
    Carousel,
    NavMenu,
    SideMenu,
    Banner
  },
}
</script>

<style lang="scss" scoped>
.dashboard {
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
  &__banner {
    & .banner {
      margin: 20px 0px;
      height: 300px;
      width: 100%;
      // border: 1px #666 solid;
      box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
      display: flex;
      justify-content: space-between;
      align-items: center;
      & .title {
        text-align: center;
        width: 200px;
        position: relative;
        &--left {
          transform: translateX(50%);
        }
        &--right {
          transform: translateX(-50%);
        }
      }
    }
  }
}
</style>
