<template>
  <div id="myProgress">
    <div id="myBar">0%</div>
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
#myProgress {
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
