<template>
  <el-card :body-style="{ padding: '0px' }">
    <img :src="img" class="image">
    <div style="padding: 14px;">
      <span>Yummy hamburger</span>
      <div class="bottom clearfix">
        <time class="time">{{ currentDate }}</time>
        <el-button type="text" class="button">Operating button</el-button>
      </div>
    </div>
  </el-card>
</template>

<script>
export default {
  props: {
    img: String
  },
  data() {
    return {
      currentDate: new Date()
    };
  }
}
</script>

<style lang="scss" scoped>
.time {
  font-size: 13px;
  color: #999;
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
}

.button {
  padding: 0;
  float: right;
}

.image {
  width: 100%;
  display: block;
}

.clearfix:before,
.clearfix:after {
    display: table;
    content: "";
}

.clearfix:after {
    clear: both
}
</style>
