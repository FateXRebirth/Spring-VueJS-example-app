<template>
  <section class="container">
    <hr class="hr-30">
      <Breadcrumb />
    <hr class="hr-30">
    <el-container>
      <el-aside width="600px">
        <hr class="hr-20">
        <Carousel :images="Car.photos" width="600"/>
      </el-aside>
      <el-main class="spec">
        <h1 class="title">{{ Car.brandName + ' ' + Car.seriesName + ' ' + Car.categoryName + ' ' + Car.year }}</h1>
        <hr class="hr-20">
        <div class="tag">
          <el-tag>售後保固</el-tag>
          <el-tag type="success">里程保證</el-tag>
          <el-tag type="info">實車保證</el-tag>
          <el-tag type="warning">三大保證</el-tag>
          <el-tag type="danger">非營業車</el-tag>
        </div>
        <hr class="hr-20">
        <p class="description">
          2012/04月出廠,2014/04月在台領牌(在台一手車)
          原始購車資料進口來源齊全 2副鑰匙
          現里程8萬多公里里程保證⋯⋯
          全車原出廠板件 保證絕無重大事故.無泡水.無車身引擎號碼非法變造 車況保證
          配備~天窗.九安.雙電動椅.恆溫.方向盤快撥.定速.四條米其林新胎(買到賺到)
          配合銀行低頭款低利率低月付 讓你輕鬆買車
          此車已通過第三方公正單位YES認證 讓你不懂中古車也能買好車
        </p>
        <h3 class="price"><strong> {{ Car.price }} </strong> 萬</h3>
        <hr class="hr-10">
        <div class="contact">
          <span class="name"><i class="el-icon-info"></i> 姓名：<strong> {{ Car.name }} </strong></span>
          <span class="phone"><i class="el-icon-phone"></i> 電話：<strong> {{ Car.phone }} </strong></span>
          <span class="line"><i class="el-icon-mobile-phone"></i> Line：<strong>Abc123</strong></span>
          <span class="address"><i class="el-icon-location"></i> 地址：<strong> {{ Car.city + Car.area + Car.address }} </strong></span>
          <hr class="hr-10">
          <span class="call" v-show="IsNotOwner" @click="Contact()"><i class="el-icon-chat-dot-round"></i> 立即聯絡 </span>
        </div>        
      </el-main>
    </el-container>
    <hr class="hr-30">
    <Header title="車輛資訊" />
    <section class="info">
      <h6>基本資訊</h6>
      <div class="info-block">
        <span>出廠年份<strong>{{ Car.year + '年' + Car.month + '月'}} </strong></span>
        <span>里程數<strong>{{ Car.mileage }} 公里</strong></span>
        <span>車輛所在地<strong>{{ Car.city }}</strong></span>
        <span>排氣量<strong>{{ Car.engineDisplacement }}</strong></span>
        <span>變速系統<strong>{{ Car.gearType }}</strong></span>
        <span>傳動系統<strong>{{ Car.transmission }}</strong></span>
        <span>外觀顏色<strong>{{ Car.color }}</strong></span>
        <span>燃料<strong>{{ Car.gasType }}</strong></span>
        <span>乘坐人數<strong>{{ Car.passenger }}</strong></span>
        <hr class="clear-fix">
      </div>
      <h6>內外裝配備</h6>
      <div class="info-block">
        <span v-for="option in EquipmentOptions" :key="option.value" :class="{ 'active': (option.value & Car.equipment) != 0 }"><i :class="[  (option.value & Car.equipment) != 0 ? 'el-icon-circle-check' : 'el-icon-circle-check-outline' ]"></i> {{ option.label }}</span>
        <hr class="clear-fix">
      </div>
      <h6>安全配備</h6>
      <div class="info-block">
        <span v-for="option in SafetyOptions" :key="option.value" :class="{ 'active': (option.value & Car.safety) != 0 }"><i :class="[  (option.value & Car.safety) != 0 ? 'el-icon-circle-check' : 'el-icon-circle-check-outline' ]"></i> {{ option.label }}</span>
        <hr class="clear-fix">
      </div>
    </section>
    <hr class="hr-50">
  </section>
</template>

<script>
import Breadcrumb from '~/components/Breadcrumb.vue';
import Carousel from '~/components/Carousel.vue';
import Header from '~/components/Header.vue';

export default {
  components: {
    Breadcrumb,
    Carousel,
    Header
  },
  async asyncData({ app, store, route }) {
    const User = store.getters.getAuthenticatedUser;
    let Result;
    let Car = {};
    let EquipmentOptions = [];
    let SafetyOptions = [];

    Result = await app.$axios({
      method: 'get',
      url: '/api/cars/' + route.params.id,
    })
    if(Result.data.returnCode == 0) {
      Car = Result.data.returnData.car;
    } else {
        throw new Error(Result.data.returnMessage)
    }

    Result = await app.$axios.get('/api/specification');
    Result.data.returnData.specification.map( spec => {
      if(spec.category == "Equipment") {
        EquipmentOptions.push(spec);
      } else if(spec.category == "Safety") {
        SafetyOptions.push(spec);
      } 
    })

    return {
      Car: Car,
      EquipmentOptions: EquipmentOptions,
      SafetyOptions: SafetyOptions
    }

  },
  computed: {
    classList: function(a) {
      return {
        'el-icon-circle-check': this.type == "left",
        'el-icon-circle-check-outline': this.type == "right"
      }
    },
    Img: function() {
      return require('~/static/images/banner/banner' + this.img + '.jpeg');
    },
    IsNotOwner: function() {
      return this.$store.getters.getAuthenticatedUser.ID !== this.Car.ownerID;
    }
  },
  data() {
    return {
      Car: {},
      EquipmentOptions: [],
      SafetyOptions: []
    }
  },
  methods: {
    Contact: async function() {
      function _uuid() {
        var d = Date.now();
        if (typeof performance !== 'undefined' && typeof performance.now === 'function'){
          d += performance.now(); //use high-precision timer if available
        }
        return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
          var r = (d + Math.random() * 16) % 16 | 0;
          d = Math.floor(d / 16);
            return (c === 'x' ? r : (r & 0x3 | 0x8)).toString(16);
        });
      }
      const User = this.$store.getters.getAuthenticatedUser;
      if(User === null) {
        location.href = "/login";
        return;
      }
      document.querySelector('.talking').querySelector('.button').classList.add('hide');
      document.querySelector('.talking').querySelector('.model').classList.add('show');
      const dialogue = {
        uuid: _uuid(),
        item: this.Car.carID,
        title: `${this.Car.brandName} ${this.Car.seriesName} ${this.Car.categoryName} ${this.Car.year}`,
        sender: this.$store.getters.getAuthenticatedUser.ID,
        receiver: this.Car.ownerID,
      }
      this.$axios({
        method: 'post',
        url: '/users/dialogues',
        headers: {
          'User': User.Username,
          'ID': User.ID,
          'Authorization': User.Token
        },
        data: dialogue
      }).then((res => {
        if(res.data.returnCode != 0) {
          throw new Error(res.data.returnMessage)
        }
      }))
    }
  }
}
</script>

<style lang="scss" scoped>
.spec {
  & .title {
    font-size: 40px;
    font-weight: bold;
  }
  & .tag {
    & .el-tag {
      margin: 0px 5px;
    }
  }
  & .description {
    height: 50px;
    color: #999999;
    line-height: 150%;
    overflow: hidden;
    display: -webkit-box;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;  
  }
  & .price {
    color: red;
    font-size: 20px;
    & strong {
      font-size: 35px;
    }
  }
  & .contact {
    & span {
      margin: 7px 0px;
      display: block;
      &.warning { 
        color: #999999;
        font-size: 12px;
        margin: 8px 0px;
      }
      &.phone, &.line {
        display: inline-block;
        padding-right: 20px;
      }
      &.call {
        cursor: pointer;
        background-color: #cd001d;
        border-radius: 5px;
        text-align: center;
        color: white;
        height: 40px;
        width: 200px;
        line-height: 40px;
        font-size: 18px;
        font-weight: bold;
        & .el-icon-phone-outline {
          margin-right: 10px;
        }
      }
    }
  }
}
.info {
  & h6 {
    margin-top: 20px;
  }
  & .info-block {
    width: 100%;
    display: flex;
    justify-content: flex-start;
    align-items: center;
    flex-wrap: wrap;
    & span {
      display: block;
      padding: 10px;
      margin: 10px;
      height: 40px;
      width: 18%;
      color: #999999;
      border-bottom: 1px solid gainsboro;
      & strong {
        margin-left: 10px;
        color: black;
      }
      &.active {
        color: black;
      }
    }
  }
}

</style>
