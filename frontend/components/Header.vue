<template>
  <div class="header">
    <h5 class="header__title">
      {{ title }}
    </h5>
    <a class="header__link" :href="url" v-if="subTitle" >{{ subTitle }}<i class="el-icon-caret-right"></i></a>
  </div>
</template>

<script>
export default {
  props: {
    title: String,
    subTitle: String,
    url: String    
  }
}
</script>

<style lang="scss" scoped>
.header {
  position: relative;
  padding: 8px 0px;
  &:before {
    content: '';
    width: 65px;
    height: 1px;
    position: absolute;
    bottom: 0;
    background-color: #39AF78;
  }
  &:after {
    content: '';
    left: 65px;
    right: 0;
    height: 1px;
    position: absolute;
    bottom: 0;
    background-color: rgba($color: #39AF78, $alpha: 0.7);
  }
  &__title {
    padding-left: 20px;
    border-left: 5px #39AF78 solid;
    height: 30px;
    line-height: 30px;
  }
  &__link {
    position: absolute;
    right: 0;
    top: 50%;
    transform: translateY(-50%);
    text-decoration: none;
  }
}
</style>
