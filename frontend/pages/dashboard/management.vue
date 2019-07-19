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
        <Header title="Management" />
        <hr class="hr-30">
        <el-button type="primary" icon="el-icon-edit-outline" @click="HandleCreate">刊登車輛</el-button>
        <hr class="hr-30">
        <el-table :data="Cars" style="width: 100%">
          <el-table-column label="狀態" width="70" align="center">
            <template slot-scope="scope">
              <span v-if="scope.row.status == 1">上架</span>
              <span v-else-if="scope.row.status == 0">下架</span>
            </template>
          </el-table-column>
          <el-table-column label="更新日期" width="100" align="center">
            <template slot-scope="scope">
              <span>{{ new Date(scope.row.modifyDate).getMonth() + 1 + ' / ' + new Date(scope.row.modifyDate).getDate() }}</span>
            </template>
          </el-table-column>
          <el-table-column label="封面" width="180" align="center">
            <template slot-scope="scope">
              <img class="cover" :src="scope.row.image" alt="">
            </template>
          </el-table-column>
          <el-table-column label="車輛資訊" width="250" algin="left">
            <template slot-scope="scope">
              <span>{{ scope.row.brandName + ' ' + scope.row.seriesName + ' ' + scope.row.categoryName + ' ( ' + scope.row.year + ' ) ' }}</span>
            </template>
          </el-table-column>
          <el-table-column label="價錢" width="80" align="center">
            <template slot-scope="scope">
              <span>{{ scope.row.price }} 萬</span>
            </template>
          </el-table-column>
          <!-- <el-table-column label="View" width="80" align="center">
            <template slot-scope="scope">
              <span>{{ scope.row.price }}</span>
            </template>
          </el-table-column> -->
          <el-table-column label="動作" align="right">
            <template slot-scope="scope">
              <el-button size="medium" type="success" @click="HandleStatus(scope.row.carID, 1)" v-if="scope.row.status == 0">上架</el-button>
              <el-button size="medium" type="warning" @click="HandleStatus(scope.row.carID, 0)" v-if="scope.row.status == 1">下架</el-button>
              <el-button size="medium" @click="HandleEdit(scope.row.carID)">編輯</el-button>
              <el-button size="medium" type="danger" @click="HandleDelete(scope.row.carID)">刪除</el-button>
            </template>
          </el-table-column>
        </el-table>
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
  async asyncData({ app, store }) {
    const User = store.getters.getAuthenticatedUser;
    let Result = await app.$axios({
      method: 'get',
      url: '/cars',
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
      tableData: [{
        CarID: 1,
        Status: 1,
        ModifyDate: "10/10",
        Img: "https://www.google.com.tw/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
        Name: "AUDI A4 Avant 1.8 TFSI (2012)",
        Price: 99,
        View: 32
      }, {
        CarID: 2,
        Status: 0,
        ModifyDate: "10/10",
        Img: "https://www.google.com.tw/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
        Name: "BMW 2 SERIES GRAND TOURER 218i (2016)",
        Price: 200,
        View: 99
      }]
    }
  },
  methods: {
    HandleCreate() {
      this.$router.push('carNew');
    },
    HandleEdit(CarID) {
      this.$router.push('carEdit/' + CarID );
    },
    HandleDelete(CarID) {
      const User = this.$store.getters.getAuthenticatedUser;
      const decision = confirm("你確定要刪除嗎？");
      if(!decision) return;
      this.$axios({
        method: 'delete',
        url: '/cars/' + CarID,
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
          this.$message({
            showClose: true,
            message: res.data.returnMessage,
            type: 'success',
            duration: 1500
          });
          setTimeout(function() {
            window.location.reload();
          }, 1500)
        }
      })
    },
    HandleStatus(CarID, Status) {
      const User = this.$store.getters.getAuthenticatedUser;
      this.$axios({
        method: 'post',
        url: '/cars/' + CarID + "?Status=" + Status,
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
          this.$message({
            showClose: true,
            message: res.data.returnMessage,
            type: 'success',
            duration: 1500
          });
          setTimeout(function() {
            window.location.reload();
          }, 1500)
        }
      })
    },
  }
}
</script>

<style lang="scss" scoped>
.cover {
  display: block;
  width: 100px;
  height: auto;
  margin: 0 auto;
}
.el-button--mini {
  padding: 5px 10px;
}
</style>
