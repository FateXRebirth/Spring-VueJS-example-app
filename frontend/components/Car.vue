<template>
  <a @click="Go(Car.carID)">
    <el-card shadow="hover" class="Car">
      <img :src="Car.image" class="Image">
      <hr class="hr-20">
      <p class="Name">{{ GetName }}</p>
      <hr class="hr-10">
      <p class="Detail">{{ GetDetail }}</p>
      <hr class="hr-20">
      <span @click.stop="RemoveFavorite(Car.carID)" class="Star" v-if="IsFavorite"><i class="el-icon-star-on">已收藏</i></span>
      <span @click.stop="AddFavorite(Car.carID)" class="Star" v-else><i class="el-icon-star-off">未收藏</i></span>
      <span class="Price">{{ Car.price }}萬</span>
      <hr class="clear-fix">
    </el-card>
  </a>
</template>

<script>
export default {
  props: ['Car', 'Favorite'],
  data() {
    return {
      IsFavorite: this.Favorite
    }
  },
  computed: {
    GetName: function() {
      return this.Car.brandName + ' ' + this.Car.seriesName + ' ' + this.Car.categoryName + ' ( ' + this.Car.year + ' ) ';
    },
    GetDetail: function() {
      return this.Car.year + '年 | ' + this.Car.mileage + '公里 | ' + this.Car.city + '| ' + (Math.abs(new Date().getTime() - new Date(this.Car.modifyDate).getTime()) / 86400000).toFixed(0) + '天前更新';
    }
  },
  methods: {
    Go(CarID) {
      console.log(this.$router);
      this.$router.push('/car/' + CarID );
    },
    Update(authUser) {
      this.$axios.post(process.env.BASE_URL + '/api/session', {
        id: authUser.ID,
        type: authUser.Type,
        account: authUser.Username,
        token: authUser.Token,
        favoriteCars: authUser.FavoriteCars
      })
      .then((res) => {
        if(res.data.returnCode != 0) {
            throw new Error("Server Error");
        }
      })
    },
    AddFavorite(CarID) {
      let User = this.$store.getters.getAuthenticatedUser;
      if(User.FavoriteCars.indexOf(CarID) !== -1) return;
      this.$axios({
        method: 'post',
        url: '/users/favorite/' + CarID,
        headers: {
          'User': User.Username,
          'ID': User.ID,
          'Authorization': User.Token
        },
      }).then((res) => {
        if (res.data.returnCode != 0) {
          this.$message({
            showClose: true,
            message: res.data.returnMessage,
            type: 'error',
            duration: 1500
          });
          throw new Error(res.data.returnMessage)
        } else {
          this.IsFavorite = true;
          User.FavoriteCars.push(CarID);
          // Update Session
          this.Update(User);
          // Update Vuex
          this.$store.dispatch('login', { authUser: User });
        }
      })
    },
    RemoveFavorite(CarID) {
      let User = this.$store.getters.getAuthenticatedUser;
      if(User.FavoriteCars.indexOf(CarID) === -1) return;
      this.$axios({
        method: 'delete',
        url: '/users/favorite/' + CarID,
        headers: {
          'User': User.Username,
          'ID': User.ID,
          'Authorization': User.Token
        },
      }).then((res) => {
        if (res.data.returnCode != 0) {
          this.$message({
            showClose: true,
            message: res.data.returnMessage,
            type: 'error',
            duration: 1500
          });
          throw new Error(res.data.returnMessage)
        } else {
          this.IsFavorite = false;
          User.FavoriteCars.splice(User.FavoriteCars.indexOf(CarID), 1);
          // Update Session
          this.Update(User);
          // Update Vuex
          this.$store.dispatch('login', { authUser: User });
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.Car {
  cursor: pointer;
  position: relative;
  margin: 10px 0px;
  .Image {
    max-width: 100%;
    display: block;
  }
  .Name {

  }
  .Detail {
    color: #999999;
    font-size: 13px;
  }
  .Price {
    float: right;
    color: red;
    font-size: 20px;
    font-weight: 500;
  }
  .Star {
    float: left;
    color: red;
    font-size: 18px;
    & .el-icon-star-on {
      color: red;
    }
    & .el-icon-star-off {
      color: #999999;
    }
  }
}
</style>
