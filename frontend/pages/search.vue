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
        <button class="el-button is-round" :class="{ active: Condition.brand == Brand.value }" @click="Condition.brand = Brand.value; Condition.series = ''; Condition.category = ''; FirstWord = '';" v-for="Brand in HotBrandOptions" :key="Brand.value">{{ Brand.label }}</button>
      </div>

      <div class="grid-item title">廠牌</div>
      <div class="grid-item option">
        <button class="el-button is-round" :class="{ active: FirstWord == key }" @click="FirstWord = key; Condition.brand = ''; Condition.series = ''; Condition.category = '';" v-for="(value, key) in OrderedBrandOptions" :key="key">{{ key }}</button>
        <button class="el-button is-round" :class="{ active: Condition.brand == '' }" @click="Condition.brand = ''">不拘</button>
      </div>

      <template v-if="FirstWord != ''">
        <div class="grid-item title"></div>
        <div class="grid-item option">
          <button class="el-button is-round" :class="{ active: Condition.brand == Brand.value }" @click="Condition.brand = Brand.value; Condition.series = ''; Condition.category = '';" v-for="Brand in FilteredBrandOptions" :key="Brand.value">{{ Brand.label }}</button>
        </div>
      </template>

      <template v-if="Condition.brand != ''">
        <div class="grid-item title">車型</div>
        <div class="grid-item option">
          <button class="el-button is-round" :class="{ active: Condition.series == Series.value }" @click="Condition.series = Series.value; Condition.category = '';"  v-for="Series in FilteredSeriesOptions" :key="Series.value">{{ Series.label }}</button>
          <button class="el-button is-round" :class="{ active: Condition.series == '' }" @click="Condition.series = ''">不拘</button>
        </div>
      </template>

      <template v-if="Condition.series != ''">
        <div class="grid-item title">車款</div>
        <div class="grid-item option">
          <button class="el-button is-round" :class="{ active: Condition.category == Category.value }" @click="Condition.category = Category.value"  v-for="Category in FilteredCategoryOptions" :key="Category.value">{{ Category.label }}</button>
          <button class="el-button is-round" :class="{ active: Condition.category == '' }" @click="Condition.category = ''">不拘</button>
        </div>
      </template>

      <div class="grid-item title">價格</div>
      <div class="grid-item option">
        <button class="el-button is-round" :class="{ active: Condition.priceMin == Price.minValue && Condition.priceMax == Price.maxValue }" @click="Condition.priceMin = Price.minValue; Condition.priceMax = Price.maxValue"  v-for="Price in PriceOptions" :key="Price.minValue">{{ Price.label }}</button>
        <button class="el-button is-round" :class="{ active: Condition.priceMin == '' && Condition.priceMax == '' }" @click="Condition.priceMin = ''; Condition.priceMax = ''">不拘</button>
      </div>

      <div class="grid-item title">年份</div>
      <div class="grid-item option">
        <button class="el-button is-round" :class="{ active: Condition.yearMin == Year.minValue && Condition.yearMax == Year.maxValue }" @click="Condition.yearMin = Year.minValue; Condition.yearMax = Year.maxValue"  v-for="Year in YearOptions" :key="Year.minValue">{{ Year.label }}</button>
        <button class="el-button is-round" :class="{ active: Condition.yearMin == '' && Condition.yearMax == '' }" @click="Condition.yearMin = ''; Condition.yearMax = ''">不拘</button>
      </div>

      <div class="grid-item title">里程數</div>
      <div class="grid-item option">
        <button class="el-button is-round" :class="{ active: Condition.mileageMin == Mileage.minValue && Condition.mileageMax == Mileage.maxValue }" @click="Condition.mileageMin = Mileage.minValue; Condition.mileageMax = Mileage.maxValue"  v-for="Mileage in MileageOptions" :key="Mileage.minValue">{{ Mileage.label }}</button>
        <button class="el-button is-round" :class="{ active: Condition.mileageMin == '' && Condition.mileageMax == '' }" @click="Condition.mileageMin = ''; Condition.mileageMax = ''">不拘</button>
      </div>

      <div class="grid-item title">更多</div>
      <div class="grid-item option">
        <el-select v-model="Condition.city">
          <el-option value="" label="地區"></el-option>
          <el-option
            v-for="city in CityOptions"
            :key="city.value"
            :label="city.label"
            :value="city.value">
          </el-option>
        </el-select>
        <el-select v-model="Condition.transmission">
          <el-option value="" label="傳動系統"></el-option>
          <el-option
            v-for="transmission in TransmissionOptions"
            :key="transmission.value"
            :label="transmission.label"
            :value="transmission.value">
          </el-option>
        </el-select>
        <el-select v-model="Condition.gearType">
          <el-option value="" label="變速系統"></el-option>
          <el-option
            v-for="gearType in GearTypeOptions"
            :key="gearType.value"
            :label="gearType.label"
            :value="gearType.value">
          </el-option>
        </el-select>
        <el-select v-model="Condition.gasType">
          <el-option value="" label="燃料"></el-option>
          <el-option
            v-for="gasType in GasTypeOptions"
            :key="gasType.value"
            :label="gasType.label"
            :value="gasType.value">
          </el-option>
        </el-select>
        <el-select v-model="Condition.engineDisplacement">
          <el-option value="" label="排氣量"></el-option>
          <el-option
            v-for="engineDisplacement in EngineDisplacementOptions"
            :key="engineDisplacement.value"
            :label="engineDisplacement.label"
            :value="engineDisplacement.value">
          </el-option>
        </el-select>
        <el-select v-model="Condition.passenger">
          <el-option value="" label="乘坐人數"></el-option>
          <el-option
            v-for="passenger in PassengerOptions"
            :key="passenger.value"
            :label="passenger.label"
            :value="passenger.value">
          </el-option>
        </el-select>
        <el-select v-model="Condition.color">
          <el-option value="" label="顏色"></el-option>
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
    <section class="Bar">
      <div class="Sort">
        排序方式：
        <el-select v-model="Sorting">
          <el-option value="" label="預設排序"></el-option>
          <el-option value="1" label="價格由 低 到 高"></el-option>
          <el-option value="2" label="價格由 高 到 低"></el-option>
          <el-option value="3" label="年份由 新 到 舊"></el-option>
          <el-option value="4" label="年份由 舊 到 新"></el-option>
          <el-option value="5" label="更新日由 最近 到 遙遠"></el-option>
          <el-option value="6" label="更新日由 遙遠 到 最近"></el-option>
        </el-select>
      </div>
      <input v-model="Condition.keyword"><i class="el-icon-search"></i><span>總共 {{ AllCars.length }} 筆車</span>
    </section>

    <hr class="hr-30">
    <template v-if="AllCars.length > 0">
       <el-row :gutter="20" v-loading="Loading" >
        <el-col :span="6" v-for="car in Cars" :key="car.carID">
          <Car :Car="car" :Favorite="FavoriteCars.includes(car.carID)" />
        </el-col>
      </el-row>
    </template>
    <template v-else>
      <hr class="hr-100">
      <img src="~/static/images/sample/nocars.png" class="NoCars">
      <hr class="hr-50">
      <p class="Message">找不到相關結果，請變更搜尋條件</p>
      <hr class="hr-100">
    </template>
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
    let BrandOptions = [], SeriesOptions = [], CategoryOptions = [], AllCars = [], FavoriteCars = [], Result;

    Result = await app.$axios({
      method: 'post',
      url: '/api/cars',
      data: {
        brand: 0,
        series: 0,
        category: 0,
        priceMin: 0,
        priceMax: 0,
        yearMin: 0,
        yearMax: 0,
        mileageMin: 0,
        mileageMax: 0,
        city: 0,
        transmission: 0,
        gearType: 0,
        gasType: 0,
        engineDisplacement: 0,
        passenger: 0,
        color: 0,
        keyword: ""
      }
    })
    if(Result.data.returnCode == 0) {
      AllCars = Result.data.returnData.cars;
    } else {
      throw new Error(Result.data.returnMessage)
    }

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

    FavoriteCars = store.getters.isAuthenticated ? store.getters.getAuthenticatedUser.FavoriteCars : [];

    return {
      AllCars: AllCars,
      FavoriteCars: FavoriteCars,
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
      async handler(newVal, oldVal) {
        this.Loading = true;
        const Request = {
          brand: this.Condition.brand ? this.Condition.brand : 0,
          series: this.Condition.series ? this.Condition.series : 0,
          category: this.Condition.category ? this.Condition.category : 0,
          priceMin: this.Condition.priceMin ? this.Condition.priceMin : 0,
          priceMax: this.Condition.priceMax ? this.Condition.priceMax : 0,
          yearMin: this.Condition.yearMin ? this.Condition.yearMin : 0,
          yearMax: this.Condition.yearMax ? this.Condition.yearMax : 0,
          mileageMin: this.Condition.mileageMin ? this.Condition.mileageMin : 0,
          mileageMax: this.Condition.mileageMax ? this.Condition.mileageMax : 0,
          city: this.Condition.city ? this.Condition.city : 0,
          transmission: this.Condition.transmission ? this.Condition.transmission : 0,
          gearType: this.Condition.gearType ? this.Condition.gearType : 0,
          gasType: this.Condition.gasType ? this.Condition.gasType : 0,
          engineDisplacement: this.Condition.engineDisplacement ? this.Condition.engineDisplacement : 0,
          passenger: this.Condition.passenger ? this.Condition.passenger : 0,
          color: this.Condition.color ? this.Condition.color : 0,
          keyword: this.Condition.keyword ? this.Condition.keyword :  "",
        }
        let Result = await this.$axios({
          method: 'post',
          url: '/api/cars',
          data: Request
        })
        if(Result.data.returnCode == 0) {
          this.AllCars = Result.data.returnData.cars;
          this.Loading = false;
        } else {
          throw new Error(Result.data.returnMessage)
        }
      },
      deep: true,
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
      return this.OrderedBrandOptions[this.FirstWord];
    },
    FilteredSeriesOptions: function() {
      const vm = this;
      return this.SeriesOptions.filter(function(series) {
        return series.BrandID == vm.Condition.brand;
      })
    },
    FilteredCategoryOptions: function() {
      const vm = this;
      return this.CategoryOptions.filter(function(category) {
        return category.SeriesID == vm.Condition.series;
      })
    },
    Cars: function() {
      this.Loading = true;
      let Cars = Array.from(this.AllCars);
      // let Cars =  [...this.AllCars];
      // let Cars = this.AllCars.slice();
      if(this.Sorting == 1) {
        Cars.sort(function(a, b) {
          if ( a.price < b.price ) { return -1; }
          if ( a.price > b.price ) { return 1;  }
          return 0;
        })
      } else if(this.Sorting == 2) {
        Cars.sort(function(a, b) {
          if ( a.price > b.price ) { return -1; }
          if ( a.price < b.price ) { return 1;  }
          return 0;
        })
      } else if(this.Sorting == 3) {
        Cars.sort(function(a, b) {
          if ( a.year > b.year ) { return -1; }
          if ( a.year < b.year ) { return 1;  }
          return 0;
        })
      } else if(this.Sorting == 4) {
        Cars.sort(function(a, b) {
          if ( a.year < b.year ) { return -1; }
          if ( a.year > b.year ) { return 1;  }
          return 0;
        })
      } else if(this.Sorting == 5) {
        Cars.sort(function(a, b) {
          if ( new Date(a.modifyDate).getTime() > new Date(b.modifyDate).getTime() ) { return -1; }
          if ( new Date(a.modifyDate).getTime() < new Date(b.modifyDate).getTime() ) { return 1;  }
          return 0;
        })
      } else if(this.Sorting == 6) {
        Cars.sort(function(a, b) {
          if ( new Date(a.modifyDate).getTime() < new Date(b.modifyDate).getTime() ) { return -1; }
          if ( new Date(a.modifyDate).getTime() > new Date(b.modifyDate).getTime() ) { return 1;  }
          return 0;
        })
      }
      setTimeout(() => {
        this.Loading = false;
      }, 300)
      return Cars;
    }
  },
  data() {
    return {
      Condition: {
        brand: "",
        series: "",
        category: "",
        priceMin: "",
        priceMax: "",
        yearMin: "",
        yearMax: "",
        mileageMin: "",
        mileageMax: "",
        city: "",
        transmission: "",
        gearType: "",
        gasType: "",
        engineDisplacement: "",
        passenger: "",
        color: "",
        keyword: ""
      },
      AllCars: [],
      FavoriteCars: [],
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
      CityOptions: [],
      FirstWord: "",
      Sorting: "",
      Loading: false
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
.Bar {
  position: relative;
  display: flex;
  justify-content: flex-end;
  align-items: center;
  & input {
    border: none;
    border-bottom: 1px solid #999999;
    width: 300px;
    height: 30px;
    outline-color: #39AF78;
    &:focus {
      & + i {
        color: #39AF78;
      }
    }
  }
  & i {
    margin: 0px 20px;
    font-size: 30px;
  }
  & span {
    margin: 0px 20px;
    font-size: 20px;
  }
  & .Sort {
    position: absolute;
    left: 0;
  }
}
.NoCars {
  display: block;
  margin: 0 auto;
  width: 10vw;
}
.Message {
  text-align: center;
  font-size: 25px;
}

</style>
