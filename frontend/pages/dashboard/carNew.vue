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
        <el-form
          :label-position="labelPosition"
          label-width="100px"
          :rules="rules"
          :model="carForm"
          ref="carForm"
        >
          <Header title="Basic"/>
          <hr class="hr-30">
          <el-row :gutter="10">
            <el-col :span="5">
              <el-form-item label="Brand" prop="brand">
                <Select :data="BrandOptions" type="Brand" @callback="Callback"/>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="Series" prop="series">
                <Select :data="SeriesOptions" type="Series" @callback="Callback"/>
              </el-form-item>
            </el-col>
            <el-col :span="5">
              <el-form-item label="Category" prop="category">
                <Select :data="CategoryOptions" type="Category" @callback="Callback"/>
              </el-form-item>
            </el-col>
            <el-col :span="4">
              <el-form-item label="Year" prop="year">
                <Select :data="YearOptions" type="Year" @callback="Callback"/>
              </el-form-item>
            </el-col>
            <el-col :span="4">
              <el-form-item label="Month" prop="month">
                <Select :data="MonthOptions" type="Month" @callback="Callback"/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="10">
            <el-col :span="4">
              <el-form-item label="Transmission" prop="transmission">
                <Select :data="TransmissionOptions" type="Transmission" @callback="Callback"/>
              </el-form-item>
            </el-col>
            <el-col :span="4">
              <el-form-item label="GearType" prop="gearType">
                <Select :data="GearTypeOptions" type="GearType" @callback="Callback"/>
              </el-form-item>
            </el-col>
            <el-col :span="4">
              <el-form-item label="GasType" prop="gasType">
                <Select :data="GasTypeOptions" type="GasType" @callback="Callback"/>
              </el-form-item>
            </el-col>
            <el-col :span="4">
              <el-form-item label="EngineDisplacement" prop="engineDisplacement">
                <Select
                  :data="EngineDisplacementOptions"
                  type="EngineDisplacement"
                  @callback="Callback"
                />
              </el-form-item>
            </el-col>
            <el-col :span="4">
              <el-form-item label="Passenger" prop="passenger">
                <Select :data="PassengerOptions" type="Passenger" @callback="Callback"/>
              </el-form-item>
            </el-col>
            <el-col :span="4">
              <el-form-item label="Color" prop="color">
                <Select :data="ColorOptions" type="Color" @callback="Callback"/>
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
          <el-form-item label="Contact Address" prop="address">
            <el-row :gutter="0">
              <el-col :span="5">
                <el-form-item prop="city">
                  <Select :data="CityOptions" type="City" @callback="Callback"/>
                </el-form-item>
              </el-col>
              <el-col :span="5">
                <el-form-item prop="area">
                  <Select :data="AreaOptions" type="Area" @callback="Callback"/>
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
  // middleware: 'auth',
  async asyncData({ app }) {
    // let BrandOptions = await app.$axios.get('http://127.0.0.1:8080/BrandOptions.json');
    // let SeriesOptions = await app.$axios.get('http://127.0.0.1:8080/SeriesOptions.json');
    // let CategoryOptions = await app.$axios.get('http://127.0.0.1:8080/CategoryOptions.json');
    let YearOptions = await app.$axios.get(
      "http://127.0.0.1:8080/YearOptions.json"
    );
    let MonthOptions = await app.$axios.get(
      "http://127.0.0.1:8080/MonthOptions.json"
    );
    let TransmissionOptions = await app.$axios.get(
      "http://127.0.0.1:8080/TransmissionOptions.json"
    );
    let GearTypeOptions = await app.$axios.get(
      "http://127.0.0.1:8080/GearTypeOptions.json"
    );
    let GasTypeOptions = await app.$axios.get(
      "http://127.0.0.1:8080/GasTypeOptions.json"
    );
    let EngineDisplacementOptions = await app.$axios.get(
      "http://127.0.0.1:8080/EngineDisplacementOptions.json"
    );
    let PassengerOptions = await app.$axios.get(
      "http://127.0.0.1:8080/PassengerOptions.json"
    );
    let ColorOptions = await app.$axios.get(
      "http://127.0.0.1:8080/ColorOptions.json"
    );
    let EquipmentOptions = await app.$axios.get(
      "http://127.0.0.1:8080/EquipmentOptions.json"
    );
    let SafetyOptions = await app.$axios.get(
      "http://127.0.0.1:8080/SafetyOptions.json"
    );
    let RegionOptions = await app.$axios.get(
      "http://127.0.0.1:8080/RegionOptions.json"
    );
    return {
      // BrandOptions: BrandOptions.data,
      // SeriesOptions: SeriesOptions.data,
      // CategoryOptions: CategoryOptions.data,
      YearOptions: YearOptions.data,
      MonthOptions: MonthOptions.data,
      TransmissionOptions: TransmissionOptions.data,
      GearTypeOptions: GearTypeOptions.data,
      GasTypeOptions: GasTypeOptions.data,
      EngineDisplacementOptions: EngineDisplacementOptions.data,
      PassengerOptions: PassengerOptions.data,
      ColorOptions: ColorOptions.data,
      EquipmentOptions: EquipmentOptions.data,
      SafetyOptions: SafetyOptions.data,
      CityOptions: RegionOptions.data.Countries,
      AreaOptions: RegionOptions.data.Districts,
    };
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
      CategoryOptions: [],
      YearOptions: [],
      MonthOptions: [],
      TransmissionOptions: [],
      GearTypeOptions: [],
      GasTypeOptions: [],
      EngineDisplacementOptions: [],
      PassengerOptions: [],
      ColorOptions: [],
      EquipmentCheckAll: false,
      EquipmentCheckedOption: [],
      EquipmentIsIndeterminate: false,
      EquipmentOptions: [],
      SafetyCheckAll: false,
      SafetyCheckedOption: [],
      SafetyIsIndeterminate: false,
      SafetyOptions: [],
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
      deep: true
      // immediate: true,
    }
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
    Callback(type, value) {
      this.carForm[type] = value;
    },
    EquipmentAllChange(value) {
      this.EquipmentCheckedOption = value ? this.EquipmentOptions : [];
      this.EquipmentIsIndeterminate = false;
      if (value) {
        for (var i = 0; i < this.EquipmentOptions.length; i++) {
          this.carForm.Equipment += this.EquipmentOptions[i].value;
        }
      } else {
        this.carForm.Equipment = 0;
      }
    },
    CheckedEquipmentChange(observer) {
      let checkedCount = observer.length;
      this.EquipmentCheckAll = checkedCount === this.EquipmentOptions.length;
      this.EquipmentIsIndeterminate =
        checkedCount > 0 && checkedCount < this.EquipmentOptions.length;
      if (checkedCount != 0) {
        this.carForm.Equipment = 0;
        for (var i = 0; i < observer.length; i++) {
          this.carForm.Equipment += observer[i].value;
        }
      } else {
        this.carForm.Equipment = 0;
      }
    },
    SafetyAllChange(value) {
      console.log(value);
      this.SafetyCheckedOption = value ? this.SafetyOptions : [];
      this.SafetyIsIndeterminate = false;
      if (value) {
        for (var i = 0; i < this.SafetyOptions.length; i++) {
          this.carForm.Safety += this.SafetyOptions[i].value;
        }
      } else {
        this.carForm.Safety = 0;
      }
    },
    CheckedSafetyChange(observer) {
      let checkedCount = observer.length;
      this.SafetyCheckAll = checkedCount === this.SafetyOptions.length;
      this.SafetyIsIndeterminate =
        checkedCount > 0 && checkedCount < this.SafetyOptions.length;
      if (checkedCount != 0) {
        this.carForm.Safety = 0;
        for (var i = 0; i < observer.length; i++) {
          this.carForm.Safety += observer[i].value;
        }
      } else {
        this.carForm.Safety = 0;
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