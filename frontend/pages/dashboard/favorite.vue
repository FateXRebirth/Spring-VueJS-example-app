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
        <el-row :gutter="20">
          <el-col :span="6" v-for="car in Cars" :key="car.CarID">
            <Car :Car="car" />
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
  async asyncData({ app, store, route }) {
    const User = store.getters.getAuthenticatedUser;
    let Result = await app.$axios({
      method: 'get',
      url: '/api/cars',
      headers: {
        'User': User.Username,
        'ID': User.ID,
        'Authorization': User.Token
      },
    })
    if(Result.data.returnCode == 0) {
      return {
        Cars: Result.data.returnData.cars
      }
    } else {
        throw new Error(Result.data.returnMessage)
    }
  },
  data() {
    return {
      Cars: [],
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
