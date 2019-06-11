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
          <button type="button" @click="Test">上傳至Firebase</button>
          <div class="photos">
            <div v-for="photo in Photos" :key="photo.src" class="photo">
              <div class="img" :style="GenerateSource(photo.src)"></div>
              <button type="button" class="delete" @click="HandlePhotoDelete(photo)"><i class="el-icon-close"></i></button>
            </div>
            <!-- <div class="photo">
              <div class="img" style="background-image: url(https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png);" alt=""></div>
              <button type="button" class="delete" @click="Delete(photo)"></button>
            </div>
            <div class="photo">
              <div class="img" style="background-image: url(https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png);" alt=""></div>
            </div> -->
            <label for="file" class="upload" :style="{ display: this.Photos.length < 5 ? 'flex': 'none' }" v><i class="el-icon-plus"></i></label>
            <input type="file" id="file" @change="HandlePhotoUpload" accept="image/*" multiple>
          </div>
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
  head () {
    return {
      script: [
        { src: 'https://www.gstatic.com/firebasejs/5.8.1/firebase.js' }
      ]
    }
  },
  middleware: 'auth',
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

    // if (!firebase.apps.length) {
    //   firebase.initializeApp(process.env.FIREBASE_CONFIG);
    //   this.storage = firebase.storage().ref();
    // }
    firebase.initializeApp(process.env.FIREBASE_CONFIG);
    this.storage = firebase.storage().ref();

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
      Photos: [],
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
    'carForm': {
      handler(newVal, oldVal) {
        console.log(JSON.stringify(newVal));
      },
      // deep: true,
      // immediate: true,
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
    'Photos': function(newValue, oldValue) {
      console.log(JSON.stringify(newValue))
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
    GenerateSource: function(src) {
      return `background-image: url(${src});`;
    },
    HandlePhotoUpload(e) {
      if(e.target.files.length > 5) {
        alert("上傳數量超過上限！");
        return;
      }
      for (var i = 0; i < e.target.files.length; i++) {
        let Source = e.target.files[i];
        let reader = new FileReader();
        reader.readAsDataURL(Source);
        reader.onload = function(event) {
          Source.src = event.target.result;
          this.Photos.push(Source);
        }.bind(this);
      }
    },
    HandlePhotoDelete(photo) {      
      return this.Photos.splice(this.Photos.indexOf(photo), 1);
    },
    Test() {
      for (var i = 0; i < this.Photos.length; i++) {
        let file = this.Photos[i];
        let metadata = {
          contentType: file.type
        };
        let uploadTask = this.storage.child('images/' + file.name).put(file, metadata);
        // Listen for state changes, errors, and completion of the upload.
        uploadTask.on(firebase.storage.TaskEvent.STATE_CHANGED, // or 'state_changed'
          function(snapshot) {
            // Get task progress, including the number of bytes uploaded and the total number of bytes to be uploaded
            var progress = (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
            console.log('Upload is ' + progress + '% done');
            switch (snapshot.state) {
              case firebase.storage.TaskState.PAUSED: // or 'paused'
                console.log('Upload is paused');
                break;
              case firebase.storage.TaskState.RUNNING: // or 'running'
                console.log('Upload is running');
                break;
            }
          }, function(error) {
            // A full list of error codes is available at
            // https://firebase.google.com/docs/storage/web/handle-errors
            switch (error.code) {
              case 'storage/unauthorized':
                // User doesn't have permission to access the object
                break;
              case 'storage/canceled':
                // User canceled the upload
                break;
              case 'storage/unknown':
                // Unknown error occurred, inspect error.serverResponse
                break;
            }
        }, function() {
          // Upload completed successfully, now we can get the download URL
          uploadTask.snapshot.ref.getDownloadURL().then(function(downloadURL) {
            console.log('File available at', downloadURL);
          });
        });
      }
    }
  }
};
</script>

<style lang="scss" scoped>
.el-button {
  margin: 0 auto;
  display: block;
}
.photos {
  width: 100%;
  display: flex;
  justify-content: flex-start;
  align-items: center;
  flex-wrap: wrap;
  & .photo {
    position: relative;
    margin-right: 9px;
    padding: 5px;
    width: 19%;
    height: 150px;
    border: 1px solid #d9d9d9;
    & .img {
      width: 100%;
      height: 100%;
      background-position: center;
      background-repeat: no-repeat;
      background-size: contain;
    }
    & .delete {
      position: absolute;
      top: 0;
      margin-top: -15px;
      right: 0;
      margin-right: -10px;
      border-radius: 50%;
      height: 35px;
      width: 35px;
      background-color: #39AF78;
      color: white;
    }
  }
  #file {
    display: none;
  }
  & .upload {
    cursor: pointer;
    display: flex;
    justify-content: center;
    align-items: center;
    margin: 0 5px;
    width: 18%;
    height: 150px;
    border: 1px dashed #d9d9d9;
    & .el-icon-plus {
      &:before {
        font-size: 30px;
      }
    }
  }
}
</style>