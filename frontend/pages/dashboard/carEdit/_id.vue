<template>
  <section class="container">
    <hr class="hr-30">
      <Breadcrumb />
    <hr class="hr-30">
    <el-container>
      <el-aside width="200px">
        <SideMenu />
      </el-aside>
      <el-main class="carNew">   
        <el-form :label-position="labelPosition" label-width="100px" :model="carForm">
        <Header title="Basic" />
        <hr class="hr-30">
        <el-row :gutter="10">
          <el-col :span="5">
            <el-form-item label="Brand" prop="brand">
              <Select :options="BrandOptions" type="Brand" @callback="GetValue" />
            </el-form-item>
            </el-col>
          <el-col :span="6">
            <el-form-item label="Series" prop="series">
              <Select :options="SeriesOptions" type="Series" @callback="GetValue" />
            </el-form-item>
          </el-col>
          <el-col :span="5">
            <el-form-item label="Category" prop="category">
              <Select :options="CategoryOptions" type="Category" @callback="GetValue" />
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item label="Year" prop="year">
              <Select :options="YearOptions" type="Year" @callback="GetValue" />
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item label="Month" prop="month">
              <Select :options="MonthOptions" type="Month" @callback="GetValue" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="10">
          <el-col :span="4">
            <el-form-item label="Transmission" prop="transmission">
              <Select :options="TransmissionOptions" type="Transmission" @callback="GetValue" />
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item label="GearType" prop="gearType">
              <Select :options="GearTypeOptions" type="GearType" @callback="GetValue" />
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item label="GasType" prop="gasType">
              <Select :options="GasTypeOptions" type="GasType" @callback="GetValue" />
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item label="Engine" prop="engine">
              <Select :options="EngineOptions" type="Engine" @callback="GetValue" />
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item label="Passenger" prop="passenger">
              <Select :options="PassengerOptions" type="Passenger" @callback="GetValue" />
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item label="Color" prop="color">
              <Select :options="ColorOptions" type="Color" @callback="GetValue" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="10">
          <el-col :span="6">
            <el-form-item label="Mileage" prop="mileage">
              <el-input v-model="carForm.mileage" placeholder="mileage here"><template slot="append">公里</template></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="Price" prop="price">
              <el-input v-model="carForm.price" placeholder="price here"><template slot="append">萬</template></el-input>
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
        <hr class="hr-60">
        <el-upload
          class="upload-demo"
          drag
          action="https://jsonplaceholder.typicode.com/posts/"
          :on-preview="handlePreview"
          :on-remove="handleRemove"
          :on-success="handleSuccess"
          :on-error="handleError"
          :on-progress="handleProgress"
          :on-change="handleChange"
          :before-upload="beforeUpload"
          :before-remove="beforeRemove"
          :file-list="fileList"
          multiple>
          <i class="el-icon-upload"></i>
          <div class="el-upload__text">
            Drop file here or <em>click to upload</em>
            <div class="el-upload__tip" slot="tip">jpg/png files with a size less than 500kb</div>
          </div>  
        </el-upload>
        <hr class="hr-30">
        <Header title="Contact" />
        <hr class="hr-30">
        <div class="contactInfo">
          <el-form-item prop="name">
            <el-input v-model="carForm.name" placeholder="name here">
              <template slot="prepend"><i class="el-icon-info"></i>Name</template>
            </el-input>
          </el-form-item>
          <el-form-item prop="cellphone">
           <el-input v-model="carForm.cellphone" placeholder="cellphone here">
            <template slot="prepend"><i class="el-icon-phone"></i>Cellphone</template>
          </el-input>
          </el-form-item>
          <el-form-item prop="address">
           <el-input v-model="carForm.address" placeholder="address here">
            <template slot="prepend"><i class="el-icon-location"></i>Address</template>
          </el-input>
          </el-form-item>
        </div>
        </el-form>
        <hr class="hr-50">
        <el-button type="warning" icon="el-icon-edit-outline" @click="Modify">Modify</el-button>
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
        name: '',
        cellphone: '',
        address: ''
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
    },
    handlePreview(file) {
      console.log("handlePreview")
      console.log(file)
    },
    handleRemove(file, fileList) {
      console.log("handleRemove")
      console.log(file)
      console.log(fileList)
    },
    handleSuccess(response, file, fileList) {
      console.log("handleSuccess")
      console.log(response)
      console.log(file)
      console.log(fileList)
    },
    handleError(err, file, fileList) {
      console.log("handleError")
      console.log(err)
      console.log(file)
      console.log(fileList)
    },
    handleProgress(event, file, fileList) {
      console.log("handleProgress")
      console.log(event)
      console.log(file)
      console.log(fileList)
    },
    handleChange(file, fileList) {
      console.log("handleChange")
      console.log(file)
      console.log(fileList)
    },
    beforeUpload(file) {
      console.log("beforeUpload")
      console.log(file)
    },
    beforeRemove(file, fileList) {
      console.log("beforeRemove")
      console.log(file)
      console.log(fileList)
    }
  }
}
</script>

<style lang="scss" scoped>
.contactInfo {
  width: 600px;
  .el-input {
    margin: 10px 0px;
  }
}
.el-button {
  margin: 0 auto;
  display: block;
}
</style>
