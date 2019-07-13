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
                <Select :options="BrandOptions" type="brand" @callback="Callback" :isDisable="true" :value="carForm.brand" />
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="Series" prop="series">
                <Select :options="FilteredSeriesOptions" type="series" @callback="Callback" :isDisable="true" :value="carForm.series" />
              </el-form-item>
            </el-col>
            <el-col :span="5">
              <el-form-item label="Category" prop="category">
                <Select :options="FilteredCategoryOptions" type="category" @callback="Callback" :isDisable="true" :value="carForm.category" />
              </el-form-item>
            </el-col>
            <el-col :span="4">
              <el-form-item label="Year" prop="year">
                <Select :options="YearOptions" type="year" @callback="Callback" :isDisable="true" :value="carForm.year" />
              </el-form-item>
            </el-col>
            <el-col :span="4">
              <el-form-item label="Month" prop="month">
                <Select :options="MonthOptions" type="month" @callback="Callback" :isDisable="true" :value="carForm.month "/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="10">
            <el-col :span="4">
              <el-form-item label="Transmission" prop="transmission">
                <Select :options="TransmissionOptions" type="transmission" @callback="Callback" :value="carForm.transmission"/>
              </el-form-item>
            </el-col>
            <el-col :span="4">
              <el-form-item label="GearType" prop="gearType">
                <Select :options="GearTypeOptions" type="gearType" @callback="Callback" :value="carForm.gearType" />
              </el-form-item>
            </el-col>
            <el-col :span="4">
              <el-form-item label="GasType" prop="gasType">
                <Select :options="GasTypeOptions" type="gasType" @callback="Callback" :value="carForm.gasType" />
              </el-form-item>
            </el-col>
            <el-col :span="4">
              <el-form-item label="EngineDisplacement" prop="engineDisplacement">
                <Select
                  :options="EngineDisplacementOptions"
                  type="engineDisplacement"
                  @callback="Callback"
                  :value="carForm.engineDisplacement"
                />
              </el-form-item>
            </el-col>
            <el-col :span="4">
              <el-form-item label="Passenger" prop="passenger">
                <Select :options="PassengerOptions" type="passenger" @callback="Callback" :value="carForm.passenger" />
              </el-form-item>
            </el-col>
            <el-col :span="4">
              <el-form-item label="Color" prop="color">
                <Select :options="ColorOptions" type="color" @callback="Callback" :value="carForm.color" />
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
          <div class="photos">
            <div v-for="photo in carForm.photos" :key="photo.url" class="photo">
              <div class="img" :style="GenerateSource(photo.url)"></div>
              <button type="button" class="delete" @click="HandlePhotoDelete(photo)"><i class="el-icon-close"></i></button>
            </div>
            <label for="file" class="upload" :style="{ display: this.carForm.photos.length < 5 ? 'flex': 'none' }" v><i class="el-icon-plus"></i></label>
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
              <el-form-item label="Contact Phone" prop="phone">
                <el-input v-model="carForm.phone" placeholder="Phone"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-form-item label="Contact Address">
            <el-row :gutter="0">
              <el-col :span="5">
                <el-form-item prop="city">
                  <Select :options="CityOptions" type="city" @callback="Callback" :value="carForm.city" />
                </el-form-item>
              </el-col>
              <el-col :span="5">
                <el-form-item prop="area">
                  <Select :options="FilteredAreaOptions" type="area" @callback="Callback" :value="carForm.area" />
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
  async asyncData({ app, store, route }) {
    const User = store.getters.getAuthenticatedUser;
    let Result = await app.$axios({
      method: 'get',
      url: '/cars/' + route.params.id,
      headers: {
        'User': User.Username,
        'ID': User.ID,
        'Authorization': User.Token
      },
    })
    if(Result.data.returnCode == 0) {
      return {
        carForm: Result.data.returnData.car
      }
    } else {
        throw new Error(Result.data.returnMessage)
    }
  },
  async created () {
    let Result;

    Result = await this.$axios.get('/api/specification');
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
      } 
    })

    Result = await this.$axios.get('/api/region');
    Result.data.returnData.region.map( region => {
      if(region.country == 0) {
        this.CityOptions.push(region);
      } else if(region.country != 0) {
        this.AreaOptions.push(region);
      }
    })

    Result = await this.$axios.get('/api/brand');
    Result.data.returnData.brand.map( brand => {
      let Brand = {};
      Brand.label = brand.name;
      Brand.value = brand.id;
      this.BrandOptions.push(Brand);
    })

    Result = await this.$axios.get('/api/series');
    Result.data.returnData.series.map( series => {
      let Series = {};
      Series.label = series.name;
      Series.value = series.id;
      Series.BrandID = series.brandID;
      this.SeriesOptions.push(Series);
    })

    Result = await this.$axios.get('/api/category');
    Result.data.returnData.category.map( category => {
      let Category = {};
      Category.label = category.name;
      Category.value = category.id;
      Category.BrandID = category.brandID;
      Category.SeriesID = category.seriesID;
      this.CategoryOptions.push(Category);
    })

    const Vue = this;
    this.FilteredSeriesOptions = _.filter(this.SeriesOptions, function(series) {
      return series.BrandID == Vue.carForm.brand;
    })
    this.FilteredCategoryOptions = [];

    this.FilteredCategoryOptions = _.filter(this.CategoryOptions, function(category) {
      return category.SeriesID == Vue.carForm.series;
    })

    this.FilteredAreaOptions = _.filter(this.AreaOptions, function(area) {
      return area.country == Vue.carForm.city;
    })

    if (!firebase.apps.length) {
      firebase.initializeApp(process.env.FIREBASE_CONFIG);
      this.storage = firebase.storage().ref();
    }

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
      FilteredAreaOptions: [],
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
        phone: [
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
        phone: "",
        city: "",
        area: "",
        address: "",
        photos: []
      }
    };
  },
  watch: {
    'carForm': {
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
      this.FilteredCategoryOptions = [];
    },
    'carForm.series': function(newValue, oldValue) {
      this.FilteredCategoryOptions = _.filter(this.CategoryOptions, function(category) {
        return category.SeriesID == newValue;
      })
    },
    'carForm.city': function(newValue, oldValue) {
      this.FilteredAreaOptions = _.filter(this.AreaOptions, function(area) {
        return area.country == newValue;
      })
    }
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const User = this.$store.getters.getAuthenticatedUser;
          const data = {
            brand: this.carForm.brand,
            series: this.carForm.series,
            category: this.carForm.category,
            year: this.carForm.year,
            month: this.carForm.month,
            transmission: this.carForm.transmission,
            geartype: this.carForm.gearType,
            gastype: this.carForm.gasType,
            enginedisplacement: this.carForm.engineDisplacement,
            passenger: this.carForm.passenger,
            color: this.carForm.color,
            mileage: this.carForm.mileage,
            price: this.carForm.price,
            equipment: this.carForm.equipment,
            safety: this.carForm.safety,
            name: this.carForm.name,
            phone: this.carForm.phone,
            city: this.carForm.city,
            area: this.carForm.area,
            address: this.carForm.address,
            modifyDate: new Date().toISOString(),
            photos: this.carForm.photos
          }
          this.$axios({
            method: 'put',
            url: '/cars/' + this.carForm.carID,
            headers: {
              'User': User.Username,
              'ID': User.ID,
              'Authorization': User.Token
            },
            data: data
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
                window.location.href = '/dashboard/management';
              }, 1500)
            }
          })
        } else {
          console.log('Validation Failure');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    Callback(type, option) {
      this.carForm[type] = option.value;
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
      const Vue = this;
      if(e.target.files.length > 5) {
        alert("上傳數量超過上限！");
        return;
      }
      for (var i = 0; i < e.target.files.length; i++) {
        // let Source = e.target.files[i];
        // let reader = new FileReader();
        // reader.readAsDataURL(Source);
        // reader.onload = function(event) {
        //   Source.src = event.target.result;
        //   this.Photos.push(Source);
        // }.bind(this);

        let file = e.target.files[i];      
        file.label = (new Date().getTime()) + '_' + file.name;
        let metadata = {
          contentType: file.type
        };
        let uploadTask = this.storage.child('images/' + file.label).put(file, metadata);
        uploadTask.on(firebase.storage.TaskEvent.STATE_CHANGED, {
          'complete': function() {
            uploadTask.snapshot.ref.getDownloadURL().then(function(downloadURL) {
              // console.log('File available at', downloadURL);
              file.url = downloadURL;
              Vue.carForm.photos.push(file);
            });
          }
        });
      }
    },
    HandlePhotoDelete(photo) {    
      // Create a reference to the file to delete
      var desertRef = this.storage.child('images/' + photo.label);
      // Delete the file
      desertRef.delete().then(function() {
        // File deleted successfully
      }).catch(function(error) {
        // Uh-oh, an error occurred!
      });
      return this.carForm.photos.splice(this.carForm.photos.indexOf(photo), 1);
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