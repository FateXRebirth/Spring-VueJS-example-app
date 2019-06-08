<template>
  <section class="container">
    <hr class="hr-30">
    <Breadcrumb/>
    <hr class="hr-30">
    <el-container>
      <el-aside width="200px">
        <SideMenu/>
      </el-aside>
      <el-main class="carNew">
        <el-form :label-position="labelPosition" label-width="100px" :rules="rules" :model="carForm" ref="carForm">
          <Header title="Basic"/>
          <hr class="hr-30">
          <el-row :gutter="10">
            <el-col :span="5">
              <el-form-item label="Brand" prop="brand">
                <Select :options="BrandOptions" type="brand" @callback="Callback"/>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="Series" prop="series">
                <Select :options="FilteredSeriesOptions" type="series" @callback="Callback"/>
              </el-form-item>
            </el-col>
            <el-col :span="5">
              <el-form-item label="Category" prop="category">
                <Select :options="FilteredCategoryOptions" type="category" @callback="Callback"/>
              </el-form-item>
            </el-col>
            <el-col :span="4">
              <el-form-item label="Year" prop="year">
                <Select :options="YearOptions" type="year" @callback="Callback"/>
              </el-form-item>
            </el-col>
            <el-col :span="4">
              <el-form-item label="Month" prop="month">
                <Select :options="MonthOptions" type="month" @callback="Callback"/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="10">
            <el-col :span="4">
              <el-form-item label="Transmission" prop="transmission">
                <Select :options="TransmissionOptions" type="transmission" @callback="Callback"/>
              </el-form-item>
            </el-col>
            <el-col :span="4">
              <el-form-item label="GearType" prop="gearType">
                <Select :options="GearTypeOptions" type="gearType" @callback="Callback"/>
              </el-form-item>
            </el-col>
            <el-col :span="4">
              <el-form-item label="GasType" prop="gasType">
                <Select :options="GasTypeOptions" type="gasType" @callback="Callback"/>
              </el-form-item>
            </el-col>
            <el-col :span="4">
              <el-form-item label="EngineDisplacement" prop="engineDisplacement">
                <Select
                  :options="EngineDisplacementOptions"
                  type="engineDisplacement"
                  @callback="Callback"
                />
              </el-form-item>
            </el-col>
            <el-col :span="4">
              <el-form-item label="Passenger" prop="passenger">
                <Select :options="PassengerOptions" type="passenger" @callback="Callback"/>
              </el-form-item>
            </el-col>
            <el-col :span="4">
              <el-form-item label="Color" prop="color">
                <Select :options="ColorOptions" type="color" @callback="Callback"/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="10">
            <el-col :span="6">
              <el-form-item label="Mileage" prop="mileage">
                <el-input v-model="carForm.mileage" placeholder="Mileage">
                  <template slot="append">公里</template>
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="Price" prop="price">
                <el-input v-model="carForm.price" placeholder="Price">
                  <template slot="append">萬</template>
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6"></el-col>
            <el-col :span="6"></el-col>
          </el-row>

          <Header title="Equipment"/>
          <hr class="hr-30">
          <el-checkbox
            :indeterminate="EquipmentIsIndeterminate"
            v-model="EquipmentCheckAll"
            @change="EquipmentAllChange"
          >內外裝配備</el-checkbox>
          <div style="margin: 15px 0;"></div>
          <el-checkbox-group v-model="EquipmentCheckedOption" @change="CheckedEquipmentChange">
            <el-checkbox-button
              v-for="option in EquipmentOptions"
              :label="option"
              :key="option.value"
              :value="option.value"
            >{{option.label}}</el-checkbox-button>
          </el-checkbox-group>
          <hr class="hr-30">
          <el-checkbox
            :indeterminate="SafetyIsIndeterminate"
            v-model="SafetyCheckAll"
            @change="SafetyAllChange"
          >安全配備</el-checkbox>
          <div style="margin: 15px 0;"></div>
          <el-checkbox-group v-model="SafetyCheckedOption" @change="CheckedSafetyChange">
            <el-checkbox-button
              v-for="option in SafetyOptions"
              :label="option"
              :key="option.value"
              :value="option.value"
            >{{option.label}}</el-checkbox-button>
          </el-checkbox-group>
          <hr class="hr-30">
          <Header title="Photos"/>
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
            multiple
          >
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">Drop file here or
              <em>click to upload</em>
              <div class="el-upload__tip" slot="tip">jpg/png files with a size less than 500kb</div>
            </div>
          </el-upload>
          <hr class="hr-30">
          <Header title="Contact"/>
          <hr class="hr-30">
          <el-row :gutter="10">
            <el-col :span="12">
              <el-form-item label="Contact Name" prop="name">
                <el-input v-model="carForm.name" placeholder="Name"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="Contact Phone" prop="cellphone">
                <el-input v-model="carForm.cellphone" placeholder="Cellphone"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-form-item label="Contact Address">
            <el-row :gutter="0">
              <el-col :span="5">
                <el-form-item prop="city">
                  <Select :options="CityOptions" type="city" @callback="Callback"/>
                </el-form-item>
              </el-col>
              <el-col :span="5">
                <el-form-item prop="area">
                  <Select :options="AreaOptions" type="area" @callback="Callback"/>
                </el-form-item>
              </el-col>
              <el-col :span="14">
                <el-form-item prop="address">
                  <el-input v-model="carForm.address" placeholder="Address"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
          </el-form-item>
        </el-form>
        <hr class="hr-50">
        <el-button type="primary" icon="el-icon-edit-outline" @click="submitForm('carForm')">Create</el-button>
      </el-main>
    </el-container>
  </section>
</template>

<script>
import SideMenu from "~/components/SideMenu.vue";
import Breadcrumb from "~/components/Breadcrumb.vue";
import Header from "~/components/Header.vue";
import Select from "~/components/Select.vue";

export default {
  components: {
    SideMenu,
    Breadcrumb,
    Header,
    Select
  },
  middleware: 'auth',
  // async asyncData({ app }) {
  //   let Result;

  //   Result = await app.$axios.get('/brand');
  //   let BrandOptions = [];
  //   Result.data.returnData.brand.map( brand => {
  //     let Brand = {};
  //     Brand.label = brand.name;
  //     Brand.value = brand.id;
  //     BrandOptions.push(Brand);
  //   })

  //   Result = await app.$axios.get('/series');
  //   let SeriesOptions = [];
  //   Result.data.returnData.series.map( series => {
  //     let Series = {};
  //     Series.label = series.name;
  //     Series.value = series.id;
  //     Series.BrandID = series.brandID;
  //     SeriesOptions.push(Series);
  //   })

  //   Result = await app.$axios.get('/category');
  //   let CategoryOptions = [];
  //   Result.data.returnData.category.map( category => {
  //     let Category = {};
  //     Category.label = category.name;
  //     Category.value = category.id;
  //     Category.BrandID = category.brandID;
  //     Category.SeriesID = category.seriesID;
  //     CategoryOptions.push(Category);
  //   })

  //   return {
  //     BrandOptions: BrandOptions,
  //     SeriesOptions: SeriesOptions,
  //     CategoryOptions: CategoryOptions
  //   }
  // },
  async mounted() {
    let Result;

    Result = await this.$axios.get('/common/specification');
    Result.data.returnData.specification.map( spec => {
      if(spec.category == "Year") {
        this.YearOptions.push(spec);
      } else if(spec.category == "Month") {
        this.MonthOptions.push(spec);
      } else if(spec.category == "Transmission") {
        this.TransmissionOptions.push(spec);
      } else if(spec.category == "GearType") {
        this.GearTypeOptions.push(spec);
      } else if(spec.category == "GasType") {
        this.GasTypeOptions.push(spec);
      } else if(spec.category == "EngineDisplacement") {
        this.EngineDisplacementOptions.push(spec);
      } else if(spec.category == "Passenger") {
        this.PassengerOptions.push(spec);
      } else if(spec.category == "Color") {
        this.ColorOptions.push(spec);
      } else if(spec.category == "Equipment") {
        this.EquipmentOptions.push(spec);
      } else if(spec.category == "Safety") {
        this.SafetyOptions.push(spec);
      } else if(spec.category == "Country") {
        this.CityOptions.push(spec);
      } else if(spec.category == "Districts") {
        this.AreaOptions.push(spec);
      }
    })

    Result = await this.$axios.get('/brand');
    Result.data.returnData.brand.map( brand => {
      let Brand = {};
      Brand.label = brand.name;
      Brand.value = brand.id;
      this.BrandOptions.push(Brand);
    })

    Result = await this.$axios.get('/series');
    Result.data.returnData.series.map( series => {
      let Series = {};
      Series.label = series.name;
      Series.value = series.id;
      Series.BrandID = series.brandID;
      this.SeriesOptions.push(Series);
    })

    Result = await this.$axios.get('/category');
    Result.data.returnData.category.map( category => {
      let Category = {};
      Category.label = category.name;
      Category.value = category.id;
      Category.BrandID = category.brandID;
      Category.SeriesID = category.seriesID;
      this.CategoryOptions.push(Category);
    })

  },
  data() {
    var validateRequired = (rule, value, callback) => {
      if (!value) {
        callback(new Error("Please input the " + rule.field));
      } else {
        callback();
      }
    };
    return {
      BrandOptions: [],
      SeriesOptions: [],
      FilteredSeriesOptions: [],
      CategoryOptions: [],
      FilteredCategoryOptions: [],
      YearOptions: [],
      MonthOptions: [],
      TransmissionOptions: [],
      GearTypeOptions: [],
      GasTypeOptions: [],
      EngineDisplacementOptions: [],
      PassengerOptions: [],
      ColorOptions: [],
      EquipmentOptions: [],
      EquipmentCheckAll: false,
      EquipmentCheckedOption: [],
      EquipmentIsIndeterminate: false,
      SafetyOptions: [],
      SafetyCheckAll: false,
      SafetyCheckedOption: [],
      SafetyIsIndeterminate: false,
      CityOptions: [],
      AreaOptions: [],
      labelPosition: "top",
      rules: {
        brand: [
          { required: true, validator: validateRequired, trigger: "change" }
        ],
        series: [
          { required: true, validator: validateRequired, trigger: "change" }
        ],
        category: [
          { required: true, validator: validateRequired, trigger: "change" }
        ],
        year: [
          { required: true, validator: validateRequired, trigger: "change" }
        ],
        month: [
          { required: true, validator: validateRequired, trigger: "change" }
        ],
        transmission: [
          { required: true, validator: validateRequired, trigger: "change" }
        ],
        gearType: [
          { required: true, validator: validateRequired, trigger: "change" }
        ],
        gasType: [
          { required: true, validator: validateRequired, trigger: "change" }
        ],
        engineDisplacement: [
          { required: true, validator: validateRequired, trigger: "change" }
        ],
        passenger: [
          { required: true, validator: validateRequired, trigger: "change" }
        ],
        color: [
          { required: true, validator: validateRequired, trigger: "change" }
        ],
        mileage: [
          { required: true, validator: validateRequired, trigger: "blur" }
        ],
        price: [
          { required: true, validator: validateRequired, trigger: "blur" }
        ],
        name: [
          { required: true, validator: validateRequired, trigger: "blur" }
        ],
        cellphone: [
          { required: true, validator: validateRequired, trigger: "blur" }
        ],
        city: [
          { required: true, validator: validateRequired, trigger: "change" }
        ],
        area: [
          { required: true, validator: validateRequired, trigger: "change" }
        ],
        address: [
          { required: true, validator: validateRequired, trigger: "blur" }
        ]
      },
      carForm: {
        brand: "",
        series: "",
        category: "",
        year: "",
        month: "",
        transmission: "",
        gearType: "",
        gasType: "",
        engineDisplacement: "",
        passenger: "",
        color: "",
        mileage: "",
        price: "",
        equipment: "",
        safety: "",
        name: "",
        cellphone: "",
        city: "",
        area: "",
        address: ""
      }
    };
  },
  watch: {
    carForm: {
      handler(newVal, oldVal) {
        console.log(JSON.stringify(newVal));
      },
      deep: true,
      immediate: true,
    },
    'carForm.brand': function(newValue, oldValue) {
      this.FilteredSeriesOptions = _.filter(this.SeriesOptions, function(series) {
        return series.BrandID == newValue;
      })
    },
    'carForm.series': function(newValue, oldValue) {
      this.FilteredCategoryOptions = _.filter(this.CategoryOptions, function(category) {
        return category.SeriesID == newValue;
      })
    },
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          alert("submit!");
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    Callback(type, option) {
      if(type == "city" || type == "area") {
        this.carForm[type] = option.label;
      } else {
        this.carForm[type] = option.value;
      }
    },
    EquipmentAllChange(value) {
      this.carForm.equipment = 0;
      this.EquipmentCheckedOption = value ? this.EquipmentOptions : [];
      this.EquipmentIsIndeterminate = false;
      if (value) {
        for (var i = 0; i < this.EquipmentOptions.length; i++) {
          this.carForm.equipment += this.EquipmentOptions[i].value;
        }
      } else {
        this.carForm.equipment = 0;
      }
    },
    CheckedEquipmentChange(observer) {
      let checkedCount = observer.length;
      this.EquipmentCheckAll = checkedCount === this.EquipmentOptions.length;
      this.EquipmentIsIndeterminate =
        checkedCount > 0 && checkedCount < this.EquipmentOptions.length;
      if (checkedCount != 0) {
        this.carForm.equipment = 0;
        for (var i = 0; i < observer.length; i++) {
          this.carForm.equipment += observer[i].value;
        }
      } else {
        this.carForm.equipment = 0;
      }
    },
    SafetyAllChange(value) {
      this.carForm.safety = 0;
      this.SafetyCheckedOption = value ? this.SafetyOptions : [];
      this.SafetyIsIndeterminate = false;
      if (value) {
        for (var i = 0; i < this.SafetyOptions.length; i++) {
          this.carForm.safety += this.SafetyOptions[i].value;
        }
      } else {
        this.carForm.safety = 0;
      }
    },
    CheckedSafetyChange(observer) {
      let checkedCount = observer.length;
      this.SafetyCheckAll = checkedCount === this.SafetyOptions.length;
      this.SafetyIsIndeterminate =
        checkedCount > 0 && checkedCount < this.SafetyOptions.length;
      if (checkedCount != 0) {
        this.carForm.safety = 0;
        for (var i = 0; i < observer.length; i++) {
          this.carForm.safety += observer[i].value;
        }
      } else {
        this.carForm.safety = 0;
      }
    },
    handlePreview(file) {
      console.log("handlePreview");
      console.log(file);
    },
    handleRemove(file, fileList) {
      console.log("handleRemove");
      console.log(file);
      console.log(fileList);
    },
    handleSuccess(response, file, fileList) {
      console.log("handleSuccess");
      console.log(response);
      console.log(file);
      console.log(fileList);
    },
    handleError(err, file, fileList) {
      console.log("handleError");
      console.log(err);
      console.log(file);
      console.log(fileList);
    },
    handleProgress(event, file, fileList) {
      console.log("handleProgress");
      console.log(event);
      console.log(file);
      console.log(fileList);
    },
    handleChange(file, fileList) {
      console.log("handleChange");
      console.log(file);
      console.log(fileList);
    },
    beforeUpload(file) {
      console.log("beforeUpload");
      console.log(file);
    },
    beforeRemove(file, fileList) {
      console.log("beforeRemove");
      console.log(file);
      console.log(fileList);
    }
  }
};
</script>

<style lang="scss" scoped>
.el-button {
  margin: 0 auto;
  display: block;
}
</style>