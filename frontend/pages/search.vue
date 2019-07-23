<template>
  <section class="container">
    <hr class="hr-30">
    <Breadcrumb />
    <hr class="hr-30">
    <Header title="搜尋" />
    <hr class="hr-10">
    <div class="grid-container">
      <template>
        <div class="grid-item title">廠牌</div>
        <div class="grid-item option">
          <button @click="Condition.Brand = Brand.value"  v-for="Brand in BrandOptions" :key="Brand.value">{{ Brand.label }}</button>
          <button @click="Condition.Brand = ''">不拘</button>
        </div>
      </template>

      <template v-if="Condition.Brand != ''">
        <div class="grid-item title">車型</div>
        <div class="grid-item option">
          <button @click="Condition.Series = Series.value"  v-for="Series in FilteredSeriesOptions" :key="Series.value">{{ Series.label }}</button>
          <button @click="Condition.Series = ''">不拘</button>
        </div>
      </template>

      <template v-if="Condition.Series != ''">
        <div class="grid-item title">車款</div>
        <div class="grid-item option">
          <button @click="Condition.Category = Category.value"  v-for="Category in FilteredCategoryOptions" :key="Category.value">{{ Category.label }}</button>
          <button @click="Condition.Category = ''">不拘</button>
        </div>
      </template>

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
    let Cars = [], BrandOptions = [], SeriesOptions = [], CategoryOptions = [];

    Result = await app.$axios.get('/api/cars');
    console.log(Result)
    Cars = Result.data.returnData.cars;

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
        Brand: "",
        Series: "",
        Category: "",
      },
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
      CityOptions: [],
      AreaOptions: [],
      FilteredAreaOptions: [],
    }
  },
}
</script>

<style lang="scss" scoped>
.grid-container {
  grid-row-gap: 10px;
  display: grid;
  grid-template-columns: 80px auto;
  align-items: center;
  & .title {
    // display: flex;
    // align-items: center;
  }
  & .option {
    & button {
      padding: 5px 10px;
      margin: 0 5px;
      background-color: transparent;
      border-color: transparent;
      border-radius: 20px;
      height: 30px;
      &:hover {
        color: white;
        background-color: #39AF78;
      }
    }
  }
}

</style>
