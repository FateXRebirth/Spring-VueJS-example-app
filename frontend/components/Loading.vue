<template>
  <div class="loader-wrapper">
    <div class="loader"></div>
  </div>
</template>

<script>
export default {
  mounted: () => {
    $('.loader-wrapper').fadeOut(1000);
  }
}
</script>

<style lang="scss" scoped>
.loader-wrapper {
  position: fixed;
  z-index: 9999;
  width: 100vw;
  height: 100vh;
  background-color: rgba($color: gray, $alpha: 0.9);
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
