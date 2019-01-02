<template>
  <el-menu :default-active="activeIndex" :active-text-color="'#39AF78'" class="el-menu-demo" mode="horizontal" :router="true" @select="handleSelect">
    <el-menu-item index="/">Home</el-menu-item>
    <el-menu-item index="/dashboard">Dashboard</el-menu-item>
    <el-menu-item index="">Search</el-menu-item>
    <el-menu-item index="">Buy</el-menu-item>
       
    <el-menu-item class="reverse" index="/logout">Logout</el-menu-item>
    <el-menu-item class="reverse" index="/register">Register</el-menu-item>
    <el-menu-item class="reverse" index="/login">Login</el-menu-item>
  </el-menu>
</template>

<script>
  export default {
    data() {
      return {
        activeIndex: '1',
        activeIndex2: '1'
      };
    },
    mounted() {
      const element = this.$el;
      const pos = $(element).offset().top + $(element).outerHeight();
      $(window).scroll( () => {
        if($(window).scrollTop() > pos ) {
          $(element).addClass('hidden');
          // $(element).stop().fadeOut(500);
        } else {
          $(element).removeClass('hidden');
          // $(element).stop().fadeIn(500);
        }
      })
    },
    methods: {
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      }
    }
  }
</script>

<style lang="scss" scoped>
.el-menu {
  // display: block;
  opacity: 1;
  &.hidden {
    // display: none;
    opacity: 0;
  }
  & .reverse {
    float: right;
  }
}
</style>
