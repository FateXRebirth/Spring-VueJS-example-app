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
    
        <Header title="Equipment" />
        <hr class="hr-30">
        <el-checkbox :indeterminate="EquipmentIsIndeterminate1" v-model="EquipmentCheckAll1" @change="handleCheckAllChange1"> 內外裝配備 </el-checkbox>
        <div style="margin: 15px 0;"></div>
        <el-checkbox-group v-model="EquipmentCheckedOption1" @change="handleCheckedCitiesChange1">
          <el-checkbox-button v-for="option in EquipmentOption1" :label="option" :key="option">{{option}}</el-checkbox-button>
        </el-checkbox-group>
        <hr class="hr-30">
        <el-checkbox :indeterminate="EquipmentIsIndeterminate2" v-model="EquipmentCheckAll2" @change="handleCheckAllChange2"> 安全配備 </el-checkbox>
        <div style="margin: 15px 0;"></div>
        <el-checkbox-group v-model="EquipmentCheckedOption2" @change="handleCheckedCitiesChange2">
          <el-checkbox-button v-for="option in EquipmentOption2" :label="option" :key="option">{{option}}</el-checkbox-button>
        </el-checkbox-group>
        <hr class="hr-30">
        <Header title="Photos" />
        <hr class="hr-30">
        <Header title="Contact" />
        <hr class="hr-30">

        </el-form>
      </el-main>
    </el-container>
  </section>
</template>

<script>
import SideMenu from '~/components/SideMenu.vue';
import Breadcrumb from '~/components/Breadcrumb.vue';
import Header from '~/components/Header.vue';
import Select from '~/components/Select.vue';

const YearOptions = [{
        value: '2008',
        label: '2008'
      }, {
        value: '2009',
        label: '2009'
      }, {
        value: '2010',
        label: '2010'
      }, {
        value: '2011',
        label: '2011'
      }, {
        value: '2012',
        label: '2012'
      }];
const MonthOptions = [{
        value: '1',
        label: '1'
      }, {
        value: '2',
        label: '2'
      }, {
        value: '3',
        label: '3'
      }];
const EquipmentOption1 = ['衛星導航', '恆溫空調', '影音系統', '免鑰匙啟閉系統', '電動座椅', '皮椅', '方向盤控制鈕', 'HID', '電動滑側門', '天窗'];
const EquipmentOption2 = ['日行燈', 'ABS', '巡跡防滑系統', '胎壓偵測', '倒車影像', '定速', '車側盲點偵測系統', '車道偏移系統', '安全氣囊'];

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
      BrandOptions: [],
      SeriesOptions: [],
      CategoryOptions: [],
      YearOptions: YearOptions,
      MonthOptions: MonthOptions,
      TransmissionOptions: [],
      GearTypeOptions: [],
      GasTypeOptions: [],
      EngineOptions: [],
      PassengerOptions: [],
      ColorOptions: [],      
      EquipmentCheckAll1: false,
      EquipmentCheckedOption1: [],
      EquipmentIsIndeterminate1: false,
      EquipmentOption1: EquipmentOption1,
      EquipmentCheckAll2: false,
      EquipmentCheckedOption2: [],
      EquipmentIsIndeterminate2: false,
      EquipmentOption2: EquipmentOption2,
      labelPosition: 'top',
      carForm: {
        brand: '',
        series: '',
        category: '',
        year: '',
        month: '',
      },
    }
  },
  methods: {
    GetValue(type, value) {

    },
    handleCheckAllChange1(value) {
      this.EquipmentCheckedOption1 = value ? EquipmentOption1 : [];
      this.EquipmentIsIndeterminate1 = false;
    },
    handleCheckAllChange2(value) {
      this.EquipmentCheckedOption2 = value ? EquipmentOption2 : [];
      this.EquipmentIsIndeterminate2 = false;
    },
    handleCheckedCitiesChange1(value) {
      let checkedCount = value.length;
      this.EquipmentCheckAll1 = checkedCount === this.EquipmentOption1.length;
      this.EquipmentIsIndeterminate1 = checkedCount > 0 && checkedCount < this.EquipmentOption1.length;
    },
    handleCheckedCitiesChange2(value) {
      let checkedCount = value.length;
      this.EquipmentCheckAll2 = checkedCount === this.EquipmentOption2.length;
      this.EquipmentIsIndeterminate2 = checkedCount > 0 && checkedCount < this.EquipmentOption2.length;
    }
  }
}
</script>

<style lang="scss" scoped>

</style>