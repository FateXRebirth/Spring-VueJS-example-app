<template>
  <div class="loader-wrapper">
    <!-- <div class="loader"></div> -->
    <el-progress type="circle" :percentage="percentage" color="#39AF78"></el-progress>
  </div>
</template>

<script>
export default {
  data: function() {
    return {
      percentage: 0,
    }
  },
  beforeMount() {
    const self = this;
    var id = setInterval(frame, 10);
    function frame() {
      if (self.percentage >= 100) {
          clearInterval(id);
          $(self.$el).fadeOut(1000)
      } else {
        self.percentage++;
      }
    }
  },
  mounted: () => {
    // $('.loader-wrapper').fadeOut(1000);
  }
}
</script>

<style lang="scss" scoped>
.el-progress {
  position: absolute;
  z-index: 9999;
  top: 50%;
  left: 50%;
  transform: translate(-50%);
}
.loader-wrapper {
  position: fixed;
  z-index: 9999;
  width: 100vw;
  height: 100vh;
  background-color: white;
}
.loader {
  border: 20px #f3f3f3 solid;
  border-top: 20px #39AF78 solid;
  border-radius: 50%;
  width: 150px;
  height: 150px;
  position: relative;
  top: 50%;
  left: 50%;
  margin-top: -75px;
  margin-left: -75px;
  animation: spin 2s linear infinite;
}

@keyframes spin {
    0% { transform: rotate(0deg); }
    100% { transform: rotate(360deg); }
}
</style>
