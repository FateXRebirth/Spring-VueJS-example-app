<template>
  <section class="container">
    <hr class="hr-30">
    <Breadcrumb />
    <hr class="hr-30">
    <Header title="搜尋" />
    <div>
      <span>排序：</span>
      <button @click="Sort('Brand')">Brand</button>
      <button @click="Sort('Model')">Model</button>
      <button @click="Sort('Year')">Year</button>
      <button @click="Sort('Random')">Random</button>
    </div>
   <div>
      <span>品牌：</span>
      <button @click="Filter('Brand', Brand.Name)"  v-for="Brand in Brands" :key="Brand.Name">{{Brand.Name}}</button>
      <button @click="Filter('Brand', null)">不拘</button>
    </div>
     <div>
      <span>車型：</span>
      <button @click="Filter('Model', Model.Name)" v-for="Model in Models" :key="Model.Name">{{Model.Name}}</button>
      <button @click="Filter('Model', null)">不拘</button>
    </div>
    <div>
      <span>年份：</span>
      <button @click="Filter('Year', Year.Name)" v-for="Year in Years" :key="Year.Name">{{Year.Name}}</button>
      <button @click="Filter('Year', null)">不拘</button>
    </div>
    <p v-for="Car in Cars" :key="Car.VIN">
      <span> {{ Car.Brand }} </span>
      <span> {{ Car.Model }} </span>
      <span> {{ Car.Year }} </span>
    </p>
  </section>
</template>

<script>
// const json = require('~/static/data');
// console.log(json);

import Breadcrumb from '~/components/Breadcrumb.vue';
import Header from '~/components/Header.vue';

export default {
  components: {
    Breadcrumb,
    Header
  },
  head () {
    return {
      script: [
        { src: 'https://cdnjs.cloudflare.com/ajax/libs/lodash.js/4.17.11/lodash.min.js' }
      ]
    }
  },
  watch: {
    Brands: function(newValue, oldValue) {
      console.log(newValue);
    },
    Models: function(newValue, oldValue) {
      console.log(newValue);
    },
    Years: function(newValue, oldValue) {
      console.log(newValue);
    },
    Cars: function(newValue, oldValue) {
      console.log(newValue);
    }
  },
  mounted() {
    this.Brands = [];
    var arr = []
    _.forEach(_.countBy(this.Cars, function(o) { return o.Brand; }), function(a, b, c) {
      var obj = {};
      obj['ID'] = a;
      obj['Name'] = b;
      arr.push(obj);
    });
    this.Brands = arr;

    this.Models = [];
    var arr = []
    _.forEach(_.countBy(this.Cars, function(o) { return o.Model; }), function(a, b, c) {
      var obj = {};
      obj['ID'] = a;
      obj['Name'] = b;
      arr.push(obj);
    });
    this.Models = arr;

    this.Years = [];
    var arr = []
    _.forEach(_.countBy(this.Cars, function(o) { return o.Year; }), function(a, b, c) {
      var obj = {};
      obj['ID'] = a;
      obj['Name'] = b;
      arr.push(obj);
    });
    this.Years = arr;    

    // this.$axios.get('data.json', {
    // // Send the client cookies to the server
    //   credentials: 'same-origin',
    //   method: 'GET',
    //   headers: {
    //     'Content-Type': 'application/json'
    //   },
    // })
    // .then((res) => {
    //   console.log(res)
    // })
    // fetch('data.json')
    //   .then(r => r.json())
    //   .then(json => {
    //     this.AllCars = json;
    //     this.Cars = json;
    // })

    this.AllCars = this.Cars;

  },
  methods: {
    Sort: function(type) {
      if(type == 'Random') {
        this.Cars = _.sampleSize(this.Cars, this.Cars.length);
      } else {
        this.Cars = _.sortBy(this.Cars, [type]);
      }
    },
    Filter: function(type, value) {
      if(value == null) {
        this.Cars = _.reject(this.Cars, function(o) {
          return o[type] == value;
        })
      } else {
         this.Cars = _.filter(this.Cars, function(o) {
          return o[type] == value;
        })
      }
    }
  },
  data() {
    return {
      Brands: [],
      Models: [],
      Years: [],
      AllCars: [],
      Cars: [
      {
        "VIN": "WAUVT58E25A349315",
        "Brand": "Pontiac",
        "Model": "Aztek",
        "Year": 2003
      }, {
        "VIN": "1FTWW3A50AE788980",
        "Brand": "Buick",
        "Model": "Century",
        "Year": 1994
      }, {
        "VIN": "2C3CCAGG1CH039102",
        "Brand": "Chevrolet",
        "Model": "Tahoe",
        "Year": 2001
      }, {
        "VIN": "1FTEW1E83AF057040",
        "Brand": "Honda",
        "Model": "Accord Crosstour",
        "Year": 2011
      }, {
        "VIN": "5N1AR1NB1BC380182",
        "Brand": "Mazda",
        "Model": "B-Series",
        "Year": 1987
      }, {
        "VIN": "1ZVBP8JS7B5627526",
        "Brand": "Toyota",
        "Model": "Land Cruiser",
        "Year": 1995
      }, {
        "VIN": "1GYFC23219R004569",
        "Brand": "Buick",
        "Model": "Coachbuilder",
        "Year": 1994
      }, {
        "VIN": "1G6KF57924U747089",
        "Brand": "Buick",
        "Model": "Regal",
        "Year": 1994
      }, {
        "VIN": "WBAKF5C53CE804163",
        "Brand": "Audi",
        "Model": "A4",
        "Year": 2004
      }, {
        "VIN": "3N1AB7AP7FY186060",
        "Brand": "Lotus",
        "Model": "Exige",
        "Year": 2006
      }, {
        "VIN": "JN1AZ4EH1DM147042",
        "Brand": "Buick",
        "Model": "LaCrosse",
        "Year": 2009
      }, {
        "VIN": "1GD01XEGXFZ722801",
        "Brand": "Lexus",
        "Model": "RX",
        "Year": 2004
      }, {
        "VIN": "WBA3B1C5XF5334338",
        "Brand": "Infiniti",
        "Model": "G",
        "Year": 1993
      }, {
        "VIN": "1G6DG8E57D0616805",
        "Brand": "Ford",
        "Model": "F250",
        "Year": 1999
      }, {
        "VIN": "3D73Y3HL3AG109853",
        "Brand": "Honda",
        "Model": "Accord",
        "Year": 2007
      }, {
        "VIN": "5UXFA93526L113229",
        "Brand": "Audi",
        "Model": "A8",
        "Year": 2006
      }, {
        "VIN": "19UUA8F52CA608943",
        "Brand": "Toyota",
        "Model": "Prius",
        "Year": 2010
      }, {
        "VIN": "WAULT58E33A845812",
        "Brand": "Hyundai",
        "Model": "Equus",
        "Year": 2011
      }, {
        "VIN": "1G6KE57Y21U145816",
        "Brand": "Lexus",
        "Model": "GX",
        "Year": 2010
      }, {
        "VIN": "WAUDH94F57N973754",
        "Brand": "Rolls-Royce",
        "Model": "Ghost",
        "Year": 2012
      }]
    }
  },
}
</script>

<style lang="scss" scoped>

</style>
