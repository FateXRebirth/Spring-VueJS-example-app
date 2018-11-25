<template>
  <div class="progress-wrapper">
    <div id="myProgress">
        <div id="myBar">0%</div>
    </div>
  </div>
</template>

<script>

export default {
  mounted: () => {
    $(document).ready(() => {
      var elem = document.getElementById("myBar"); 
      var width = 1;
      var id = setInterval(frame, 10);
      function frame() {
        if (width >= 100) {
            clearInterval(id);
            $('.progress-wrapper').fadeOut(1000);
        } else {
            width++; 
            elem.style.width = width + '%'; 
            elem.innerHTML = width * 1 + '%';
        }
      }
    })
  }
}
</script>

<style lang="scss" scoped>
.progress-wrapper {
  position: fixed;
  z-index: 9999;
  width: 100vw;
  height: 100vh;
  background-color: rgba($color: gray, $alpha: 0.9);
}
#myProgress {
  position: absolute;
  bottom: 0;
  width: 100%;
  background-color: grey;
}
#myBar {
  width: 0%;
  height: 30px;
  background-color: #39AF78;
  text-align: center; /* To center it horizontally (if you want) */
  line-height: 30px; /* To center it vertically */
  color: white; 
}
</style>
