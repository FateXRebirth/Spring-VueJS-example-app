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
        <el-table :data="tableData" style="width: 100%">
          <el-table-column label="Status" width="70" align="center">
            <template slot-scope="scope">
              <span v-if="scope.row.Status == 1">上架</span>
              <span v-else-if="scope.row.Status == 0">下架</span>
            </template>
          </el-table-column>
          <el-table-column label="ModifyDate" width="100" align="center">
            <template slot-scope="scope">
              <span>{{ scope.row.ModifyDate }}</span>
            </template>
          </el-table-column>
          <el-table-column label="Img" width="180" align="center">
            <template slot-scope="scope">
              <img class="cover" :src="scope.row.Img" alt="">
            </template>
          </el-table-column>
          <el-table-column label="Name" width="230" header-align="center">
            <template slot-scope="scope">
              <span>{{ scope.row.Name }}</span>
            </template>
          </el-table-column>
          <el-table-column label="Price" width="70" align="center">
            <template slot-scope="scope">
              <span>{{ scope.row.Price }} 萬</span>
            </template>
          </el-table-column>
          <el-table-column label="View" width="80" align="center">
            <template slot-scope="scope">
              <span>{{ scope.row.View }}</span>
            </template>
          </el-table-column>
          <el-table-column label="Operations" align="right">
            <template slot-scope="scope" :test="scope">
              <el-button size="mini" type="success" @click="handleAction(scope.row.CarID, 1)" v-if="scope.row.Status == 0">Publish</el-button>
              <el-button size="mini" type="warning" @click="handleAction(scope.row.CarID, 0)" v-if="scope.row.Status == 1">UnPublish</el-button>
              <el-button size="mini" @click="handleAction(scope.row.CarID, 2)">Edit</el-button>
              <el-button size="mini" type="danger" @click="handleAction(scope.row.CarID, 3)">Delete</el-button>
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
  data() {
    return {
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
    handleAction(CarID, Action) {
      console.log("Target: %s, Action: %s", CarID, Action);
    }
  }
}
</script>

<style lang="scss" scoped>
.el-container {
  min-height: 750px;
}
.el-button--mini {
  padding: 5px 10px;
}
.cover {
  display: block;
  width: 150px;
  height: auto;
}
</style>
