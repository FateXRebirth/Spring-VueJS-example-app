<template>
  <div class="index">
    <AppHeader/>
    <AppSidebar/>
    <AppMain/>
  </div>
</template>

<script>

import AppHeader from './AppHeader.vue'
import AppSidebar from './AppSidebar.vue'
import AppMain from './AppMain.vue'

export default {
  name: 'BaseIndex',
  components: {
    AppHeader,
    AppSidebar,
    AppMain
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

.index {
  min-height: 689px;
}

.header {
  height: 400px;
}

.aside {
  float: left;
  width: 30%;
  min-width: 210px;
}

.main {
  float: right;
  width: 70%;
}

</style>
