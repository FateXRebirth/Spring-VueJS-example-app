<template>
  <section class="container">
    <hr class="hr-30">
    <Breadcrumb />
    <hr class="hr-30">
    <Header title="搜尋" />
    <hr class="hr-10">
    <div class="grid-container">

      <div class="grid-item title">熱門</div>
      <div class="grid-item option">
        <button class="el-button is-round" :class="{ active: Condition.Brand == Brand.value }" @click="Condition.Brand = Brand.value;" v-for="Brand in HotBrandOptions" :key="Brand.value">{{ Brand.label }}</button>
      </div>

      <div class="grid-item title">廠牌</div>
      <div class="grid-item option">
        <button class="el-button is-round" :class="{ active: Condition.FirstWord == key }" @click="Condition.FirstWord = key;" v-for="(value, key) in OrderedBrandOptions" :key="key">{{ key }}</button>
      </div>

      <template v-if="Condition.FirstWord != ''">
        <div class="grid-item title"></div>
        <div class="grid-item option">
          <button class="el-button is-round" :class="{ active: Condition.Brand == Brand.value }" @click="Condition.Brand = Brand.value;" v-for="Brand in FilteredBrandOptions" :key="Brand.value">{{ Brand.label }}</button>
        </div>
      </template>

      <template v-if="Condition.Brand != ''">
        <div class="grid-item title">車型</div>
        <div class="grid-item option">
          <button class="el-button is-round" :class="{ active: Condition.Series == Series.value }" @click="Condition.Series = Series.value"  v-for="Series in FilteredSeriesOptions" :key="Series.value">{{ Series.label }}</button>
          <button class="el-button is-round" :class="{ active: Condition.Series == '' }" @click="Condition.Series = ''">不拘</button>
        </div>
      </template>

      <template v-if="Condition.Series != ''">
        <div class="grid-item title">車款</div>
        <div class="grid-item option">
          <button class="el-button is-round" :class="{ active: Condition.Category == Category.value }" @click="Condition.Category = Category.value"  v-for="Category in FilteredCategoryOptions" :key="Category.value">{{ Category.label }}</button>
          <button class="el-button is-round" :class="{ active: Condition.Category == '' }" @click="Condition.Category = ''">不拘</button>
        </div>
      </template>

      <div class="grid-item title">價格</div>
      <div class="grid-item option">
        <button class="el-button is-round" @click="Condition.Pirce = Price.minValue"  v-for="Price in PriceOptions" :key="Price.minValue">{{ Price.label }}</button>
        <button class="el-button is-round" @click="Condition.Pirce = ''">不拘</button>
      </div>

      <div class="grid-item title">年份</div>
      <div class="grid-item option">
        <button class="el-button is-round" @click="Condition.Year = Year.minValue"  v-for="Year in YearOptions" :key="Year.minValue">{{ Year.label }}</button>
        <button class="el-button is-round" @click="Condition.Year = ''">不拘</button>
      </div>

      <div class="grid-item title">里程數</div>
      <div class="grid-item option">
        <button class="el-button is-round" @click="Condition.Mileage = Mileage.minValue"  v-for="Mileage in MileageOptions" :key="Mileage.minValue">{{ Mileage.label }}</button>
        <button class="el-button is-round" @click="Condition.Mileage = ''">不拘</button>
      </div>

      <div class="grid-item title">更多</div>
      <div class="grid-item option">
        <el-select v-model="Condition.City" placeholder="地區">
          <el-option value="" label="不限"></el-option>
          <el-option
            v-for="city in CityOptions"
            :key="city.value"
            :label="city.label"
            :value="city.value">
          </el-option>
        </el-select>
        <el-select v-model="Condition.Transmission" placeholder="傳動系統">
          <el-option value="" label="不限"></el-option>
          <el-option
            v-for="transmission in TransmissionOptions"
            :key="transmission.value"
            :label="transmission.label"
            :value="transmission.value">
          </el-option>
        </el-select>
        <el-select v-model="Condition.GearType" placeholder="變速系統">
          <el-option value="" label="不限"></el-option>
          <el-option
            v-for="gearType in GearTypeOptions"
            :key="gearType.value"
            :label="gearType.label"
            :value="gearType.value">
          </el-option>
        </el-select>
        <el-select v-model="Condition.GasType" placeholder="燃料">
          <el-option value="" label="不限"></el-option>
          <el-option
            v-for="gasType in GasTypeOptions"
            :key="gasType.value"
            :label="gasType.label"
            :value="gasType.value">
          </el-option>
        </el-select>
        <el-select v-model="Condition.EngineDisplacement" placeholder="排氣量">
          <el-option value="" label="不限"></el-option>
          <el-option
            v-for="engineDisplacement in EngineDisplacementOptions"
            :key="engineDisplacement.value"
            :label="engineDisplacement.label"
            :value="engineDisplacement.value">
          </el-option>
        </el-select>
        <el-select v-model="Condition.Passenger" placeholder="乘坐人數">
          <el-option value="" label="不限"></el-option>
          <el-option
            v-for="passenger in PassengerOptions"
            :key="passenger.value"
            :label="passenger.label"
            :value="passenger.value">
          </el-option>
        </el-select>
        <el-select v-model="Condition.Color" placeholder="顏色">
          <el-option value="" label="不限"></el-option>
          <el-option
            v-for="color in ColorOptions"
            :key="color.value"
            :label="color.label"
            :value="color.value">
          </el-option>
        </el-select>
      </div>

    </div>

    <hr class="hr-30">
    <el-row :gutter="20">
      <el-col :span="6" v-for="car in Cars" :key="car.CarID">
        <Car :Car="car" />
      </el-col>
    </el-row>
    <hr class="hr-30">

  </section>
</template>

<script>
import Breadcrumb from '~/components/Breadcrumb.vue';
import Header from '~/components/Header.vue';
import Car from '~/components/Car.vue';

import * as CONSTANTS from '~/static/constants';

export default {
  components: {
    Breadcrumb,
    Header,
    Car
  },
  head () {
    return {
      script: [
        { src: 'https://cdnjs.cloudflare.com/ajax/libs/lodash.js/4.17.11/lodash.min.js' }
      ]
    }
  },
  async asyncData({ app, store, route }) {
    let Result;
    let BrandOptions = [], SeriesOptions = [], CategoryOptions = [];

    Result = await app.$axios.get('/api/cars');
    let Cars = Result.data.returnData.cars;

    Result = await app.$axios.get('/api/brand');
    Result.data.returnData.brand.map( brand => {
      let Brand = {};
      Brand.label = brand.name;
      Brand.value = brand.id;
      BrandOptions.push(Brand);
    })

    Result = await app.$axios.get('/api/series');
    Result.data.returnData.series.map( series => {
      let Series = {};
      Series.label = series.name;
      Series.value = series.id;
      Series.BrandID = series.brandID;
      SeriesOptions.push(Series);
    })

    Result = await app.$axios.get('/api/category');
    Result.data.returnData.category.map( category => {
      let Category = {};
      Category.label = category.name;
      Category.value = category.id;
      Category.BrandID = category.brandID;
      Category.SeriesID = category.seriesID;
      CategoryOptions.push(Category);
    })

    return {
      Cars: Cars,
      BrandOptions: BrandOptions,
      SeriesOptions: SeriesOptions,
      CategoryOptions: CategoryOptions
    }

  },
  async created() {
    let Result;

    Result = await this.$axios.get('/api/specification');
    Result.data.returnData.specification.map( spec => {
      if(spec.category == "Transmission") {
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
      }
    })

    Result = await this.$axios.get('/api/region');
    Result.data.returnData.region.map( region => {
      if(region.country == 0) {
        this.CityOptions.push(region);
      }
    })

    this.PriceOptions = CONSTANTS.PriceRange;
    this.YearOptions = CONSTANTS.YearRange;
    this.MileageOptions = CONSTANTS.MileageRange;

  },
  watch: {
    'Condition': {
      handler(newVal, oldVal) {
        console.log(JSON.stringify(newVal));
      },
      deep: true,
      // immediate: true,
    },
    Cars: function(newValue, oldValue) {
      console.log(newValue);
    }
  },
  computed: {
    HotBrandOptions: function() {
      return this.BrandOptions.filter(function(brand) {
        return CONSTANTS.HOT_BRANDS.includes(brand.value);
      })
    },
    OrderedBrandOptions: function() {
      let firstWordObject = {};
      this.BrandOptions.map(function(brand) {
        if(!firstWordObject.hasOwnProperty(brand.label[0])) {
          firstWordObject[brand.label[0]] = [];
        }
        firstWordObject[brand.label[0]].push(brand);
      })
      return firstWordObject;
    },
    FilteredBrandOptions: function() {
      return this.OrderedBrandOptions[this.Condition.FirstWord];
    },
    FilteredSeriesOptions: function() {
      const vm = this;
      return this.SeriesOptions.filter(function(series) {
        return series.BrandID == vm.Condition.Brand;
      })
    },
    FilteredCategoryOptions: function() {
      const vm = this;
      return this.CategoryOptions.filter(function(category) {
        return category.SeriesID == vm.Condition.Series;
      })
    },
  },
  methods: {
    Sort: function(type) {
      if(type == 'Random') {
        this.Cars = _.sampleSize(this.Cars, this.Cars.length);
      } else {
        this.Cars = _.sortBy(this.Cars, [type]);
      }
    },
    Search() {

    },
  },
  data() {
    return {
      Cars: [],
      Condition: {
        FirstWord: "",
        Brand: "",
        Series: "",
        Category: "",
        Price: "",
        Year: "",
        Mileage: "",
        City: "",
        Transmission: "",
        GearType: "",
        GasType: "",
        EngineDisplacement: "",
        Passenger: "",
        Color: ""
      },
      BrandOptions: [],
      SeriesOptions: [],
      CategoryOptions: [],
      PriceOptions: [],
      MileageOptions: [],
      YearOptions: [],
      TransmissionOptions: [],
      GearTypeOptions: [],
      GasTypeOptions: [],
      EngineDisplacementOptions: [],
      PassengerOptions: [],
      ColorOptions: [],
      CityOptions: []
    }
  },
}
</script>

<style lang="scss" scoped>
.grid-container {
  grid-row-gap: 10px;
  display: grid;
  grid-template-columns: 60px auto;
  align-items: center;
  & .title {
    // display: flex;
    // align-items: center;
  }
  & .option {
    display: flex;
    flex-wrap: wrap;
    align-items: center;
    justify-content: flex-start;
    padding-bottom: 10px;
    border-bottom: 1px solid #999999;
    & .el-select {
      width: 150px;
      margin: 0px 5px;
    }
    & .el-button {
      padding: 5px 10px;
      margin: 0 5px;
      background-color: transparent;
      border-color: transparent;
      border-radius: 20px;
      height: 30px;
      &:hover, &.active {
        color: white;
        border-color: #39AF78;
        background-color: #39AF78;
      }
    }
    &:last-child {
      border: none;
    }
  }
}

</style>
