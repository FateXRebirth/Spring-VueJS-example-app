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
        Index
      </el-main>
    </el-container>
  </section>
</template>

<script>
import SideMenu from '~/components/SideMenu.vue';
import Breadcrumb from '~/components/Breadcrumb.vue';

export default {
  components: {
    SideMenu,
    Breadcrumb
  },
  middleware: 'auth'
}
</script>

<style lang="scss" scoped>
.el-container {
  min-height: 750px;
}
</style>
