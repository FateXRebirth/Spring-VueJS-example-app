<template>
  <div v-if="type == 'left'">
    <div class="banner" :class="classList">
      <h1 class="title">{{ content }} </h1>
      <img :src="Img" alt="" class="thumbnail">
    </div>
  </div> 
  <div v-else>
    <div class="banner" :class="classList">
      <img :src="Img" alt="" class="thumbnail">
      <h1 class="title">{{ content }} </h1>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    content: String,
    img: Number,
    type: String
  },
  computed: {
    classList: function() {
      return {
        'banner--left': this.type == "left",
        'banner--right': this.type == "right"
      }
    },
    Img: function() {
      return require('~/static/images/kv' + this.img + '.jpeg');
    }
  },
  mounted() {
    const element = $(this.$el).find('.banner');
    const pos = $(element).offset().top + $(element).outerHeight();
    $(window).scroll( () => {
      let height = $(window).scrollTop() + $(window).height();
      if( height > pos ) {
        $(element).addClass('active');
        // $(element).stop().fadeOut(500);
      } else {
        $(element).removeClass('active');
        // $(element).stop().fadeIn(500);
      }
    })
  }
}
</script>

<style lang="scss" scoped>
.banner {
  margin: 20px 0px;
  height: 300px;
  width: 100%;
  // border: 1px #666 solid;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  display: flex;
  justify-content: space-between;
  align-items: center;
  & .title {
    text-align: center;
    width: 200px;
    position: relative;
  }
  &--left {
    transition: all 1s;
    opacity: 0;
    & .title {
      transform: translateX(50%);
    }
    &.active {
      opacity: 1;
    }
  }
  &--right {
    transition: all 1s;
    opacity: 0;
    & .title {
      transform: translateX(-50%);
    }
    &.active {
      opacity: 1;
    }
  }
}
</style>
