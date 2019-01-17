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
        <Header title="Basic" />
        <hr class="hr-30">
        <Header title="Equipment" />
        <hr class="hr-30">
        <Header title="Photos" />
        <hr class="hr-30">
        <Header title="Contact" />
        <hr class="hr-30">
      </el-main>
    </el-container>
  </section>
</template>

<script>
import SideMenu from '~/components/SideMenu.vue';
import Breadcrumb from '~/components/Breadcrumb.vue';
import Header from '~/components/Header.vue';

export default {
  components: {
    SideMenu,
    Breadcrumb,
    Header
  },
  middleware: 'auth',
  created() {
    console.log(this.$route)
    this.id = this.$route.params.id;
  },
  mounted() {
    // console.log(this.$route.params)
    // this.id = this.$route.params.id;
  },
  data() {
    return {
      id: ""
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
