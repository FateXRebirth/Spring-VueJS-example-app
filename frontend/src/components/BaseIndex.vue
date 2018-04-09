<template>
  <div>
    <div class="header"><BaseHeader/></div>
    <BaseSidebar/>
    <BaseMain/>
  </div>
</template>

<script>

import BaseHeader from './BaseHeader.vue'
import BaseSidebar from './BaseSidebar.vue'
import BaseMain from './BaseMain.vue'

export default {
  name: 'BaseIndex',
  data () {
    return {
      name: 'aside',
      name2: 'main'
    }
  },
  components: {
    BaseHeader,
    BaseSidebar,
    BaseMain
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

.header {
  height: 400px;
}

.aside {
  float: left;
  width: 30%;
}

.main {
 float: right;
 width: 70%;
}

</style>
