<template>
  <div> Should appear if you click Post
      <router-view/>
      <DataTable/>
  </div>
</template>

<script>
import DataTable from './el-datatable.vue'

export default {
  name: 'ThePost',
  components: {
    DataTable
  }
}
</script>

<style>

</style>
