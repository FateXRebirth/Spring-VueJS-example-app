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
          <el-col :span="8" v-for="car in Cars" :key="car.carID">
            <Car :Car="car" :Favorite="FavoriteCars.includes(car.carID)" />
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
    let Cars = [], FavoriteCars = [], Result;
    const User = store.getters.getAuthenticatedUser;
    Result = await app.$axios({
      method: 'get',
      url: '/users/favorite/' + User.ID,
      headers: {
        'User': User.Username,
        'ID': User.ID,
        'Authorization': User.Token
      },
    })
    if(Result.data.returnCode == 0) {
      Cars = Result.data.returnData.cars
    } else {
        throw new Error(Result.data.returnMessage)
    }

    FavoriteCars = store.getters.isAuthenticated ? store.getters.getAuthenticatedUser.FavoriteCars : [];

    return {
      Cars: Cars,
      FavoriteCars: FavoriteCars
    }
  },
  data() {
    return {
      Cars: [],
      FavoriteCars: []
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
