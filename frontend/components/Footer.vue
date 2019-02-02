<template>
  <section class="footer">
    <div class="button" @click="GoTop();">
      <i class="el-icon-arrow-up"></i>
    </div>
    <h1>Copyright © 2018 Kevin Peng. All rights reserved.</h1>
  </section>
</template>

<script>
export default {
  mounted() {
    $(window).scroll( () => {
      if($(window).scrollTop() > 200) {
        $(this.$el).find('div.button').addClass('active');
      } else {
        $(this.$el).find('div.button').removeClass('active');
      }
    })
  },
  methods: {
    GoTop: function() {
      $('html, body').stop().animate({
				scrollTop: 0
			}, 500);
    }
  }
}
</script>

<style lang="scss" scoped>
.footer {
  height: 100px;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #f6f6f6;
  & .button {
    color: white;
    display: none;
    justify-content: center;
    align-items: center;
    position: fixed;
    right: 0;
    bottom: 0;
    margin-right: 30px;
    margin-bottom: 50px;
    background-color: #41B883;
    height: 50px;
    width: 50px;
    border-radius: 50%; 
    cursor: pointer;
    & .el-icon-arrow-up {
      font-size: 30px;
    }
    &.active {
      display: flex;
    }
  }
}
</style>
