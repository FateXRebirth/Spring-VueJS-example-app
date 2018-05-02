<template>
  <div id="app">
    <router-view></router-view>
    <router-view name="sidebar"></router-view>
  </div>

</template>

<script>
export default {
  name: 'app'
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
* {
  -moz-box-sizing: border-box;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  font-family: Arial, Helvetica, sans-serif;
}
</style>