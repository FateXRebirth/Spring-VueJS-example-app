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
        <el-form :label-position="labelPosition" label-width="100px" :model="carForm">
        <Header title="Basic" />
        <hr class="hr-30">
        <el-row :gutter="10">
          <el-col :span="5">
            <el-form-item label="Brand" prop="brand">
              <Select :data="BrandOptions" type="Brand" @callback="GetValue" />
            </el-form-item>
            </el-col>
          <el-col :span="6">
            <el-form-item label="Series" prop="series">
              <Select :data="SeriesOptions" type="Series" @callback="GetValue" />
            </el-form-item>
          </el-col>
          <el-col :span="5">
            <el-form-item label="Category" prop="category">
              <Select :data="CategoryOptions" type="Category" @callback="GetValue" />
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item label="Year" prop="year">
              <Select :data="YearOptions" type="Year" @callback="GetValue" />
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item label="Month" prop="month">
              <Select :data="MonthOptions" type="Month" @callback="GetValue" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="10">
          <el-col :span="4">
            <el-form-item label="Transmission" prop="transmission">
              <Select :data="TransmissionOptions" type="Transmission" @callback="GetValue" />
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item label="GearType" prop="gearType">
              <Select :data="GearTypeOptions" type="GearType" @callback="GetValue" />
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item label="GasType" prop="gasType">
              <Select :data="GasTypeOptions" type="GasType" @callback="GetValue" />
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item label="Engine" prop="engine">
              <Select :data="EngineOptions" type="Engine" @callback="GetValue" />
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item label="Passenger" prop="passenger">
              <Select :data="PassengerOptions" type="Passenger" @callback="GetValue" />
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item label="Color" prop="color">
              <Select :data="ColorOptions" type="Color" @callback="GetValue" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="10">
          <el-col :span="6">
            <el-form-item label="Mileage" prop="mileage">
              <el-input v-model="carForm.mileage"><template slot="append">公里</template></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="Price" prop="price">
              <el-input v-model="carForm.price"><template slot="append">萬</template></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6"></el-col>
          <el-col :span="6"></el-col>
        </el-row>
        
          
         
         
        </el-form>

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
import Select from '~/components/Select.vue';

export default {
  components: {
    SideMenu,
    Breadcrumb,
    Header,
    Select
  },
  middleware: 'auth',
  data() {
    return {
      options: [{
        value: '1',
        label: '原廠認證'
      }, {
        value: '2',
        label: '公會認證'
      }, {
        value: '3',
        label: '網路好店'
      }, {
        value: '4',
        label: '平輸好店'
      }],
      labelPosition: 'top',
      carForm: {
        account: 'admin',
        email: 'admin@example.com',
        passwordOld: '123456',
        password: '',
        confirmation: '',
        type: ""
      },
    }
  },
  methods: {
    handleAction(CarID, Action) {
      console.log("Target: %s, Action: %s", CarID, Action);
    },
    GetValue(type, value) {

    }
  }
}
</script>

<style lang="scss" scoped>

</style>
