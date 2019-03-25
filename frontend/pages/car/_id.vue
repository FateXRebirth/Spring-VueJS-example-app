<template>
  <section class="container">
    <hr class="hr-30">
      <Breadcrumb />
    <hr class="hr-30">
    <el-container>
      <el-aside width="600px">
        <hr class="hr-20">
        <Carousel :images="Images" width="600"/>
      </el-aside>
      <el-main class="spec">
        <h1 class="title">Volkswagen Golf 1.6 2012</h1>
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
        <h3 class="price"><strong>49.8 </strong> 萬</h3>
        <hr class="hr-10">
        <div class="contact">
          <span class="name"><i class="el-icon-info"></i> 姓名：<strong>宋亮修</strong></span>
          <span class="phone"><i class="el-icon-phone"></i> 電話：<strong>0929617710</strong></span>
          <span class="line"><i class="el-icon-mobile-phone"></i> Line：<strong>Abc123</strong></span>
          <span class="address"><i class="el-icon-location"></i> 地址：<strong>賞車地址：高雄市左營區曾子路467-1號</strong></span>
          <span class="call"><i class="el-icon-phone-outline"></i> 立即聯絡 </span>
          <span class="warning">註:撥打此電話所產生之費用將依據您電信公司的網外費率計費。</span>
        </div>        
      </el-main>
    </el-container>
    <hr class="hr-30">
    <Header title="車輛資訊" />
    <section class="info">
      <h6>基本資訊</h6>
      <div class="info-block">
        <span>出廠年份<strong>2012年09月</strong></span>
        <span>里程數<strong>58,405公里</strong></span>
        <span>車輛所在地<strong>高雄市</strong></span>
        <span>排氣量<strong>1.6L</strong></span>
        <span>變速系統<strong>自手排</strong></span>
        <span>傳動系統<strong>前輪驅動</strong></span>
        <span>外觀顏色<strong>銀色</strong></span>
        <span>燃料<strong>柴油</strong></span>
        <span>乘坐人數<strong>5人</strong></span>
        <hr class="clear-fix">
      </div>
      <h6>內外裝配備</h6>
      <div class="info-block">
        <span><i class="el-icon-circle-check"></i>天窗</span>
        <span><i class="el-icon-circle-check"></i>電動側滑門</span>
        <span><i class="el-icon-circle-check"></i>HID</span>
        <span><i class="el-icon-circle-check"></i>皮椅</span>
        <span><i class="el-icon-circle-check"></i>電動座椅</span>
        <span><i class="el-icon-circle-check"></i>免鑰匙啓閉系統</span>
        <span><i class="el-icon-circle-check"></i>影音系統</span>
        <span><i class="el-icon-circle-check"></i>恆溫空調</span>
        <span><i class="el-icon-circle-check"></i>衛星導航</span>
        <span><i class="el-icon-circle-check"></i>方向盤控制鈕</span>
        <hr class="clear-fix">
      </div>
      <h6>安全配備</h6>
      <div class="info-block">
        <span><i class="el-icon-circle-check-outline"></i>日行燈</span>
        <span><i class="el-icon-circle-check-outline"></i>ABS</span>
        <span><i class="el-icon-circle-check-outline"></i>安全氣囊</span>
        <span><i class="el-icon-circle-check-outline"></i>巡跡防滑系統</span>
        <span><i class="el-icon-circle-check-outline"></i>胎壓偵測</span>
        <span><i class="el-icon-circle-check-outline"></i>倒車影像</span>
        <span><i class="el-icon-circle-check-outline"></i>定速</span>
        <span><i class="el-icon-circle-check-outline"></i>車側盲點偵測系統</span>
        <span class="active"><i class="el-icon-circle-check-outline"></i>車道偏移系統</span>
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
  data() {
    return {
      Images: [
        "/images/sample/car-sample1.jpg",
        "/images/sample/car-sample2.jpg",
        "/images/sample/car-sample3.jpg",
        "/images/sample/car-sample4.jpg"
      ],
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
    & span {
      display: block;
      padding: 10px;
      margin: 10px;
      float: left;
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
