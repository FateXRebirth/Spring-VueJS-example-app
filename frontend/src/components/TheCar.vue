<template>
  <div> Should appear if you click Car
      <router-view/>
      <DataTable/>
  </div>
</template>

<script>
import DataTable from './el-datatable.vue'

export default {
  name: 'TheCar',
  components: {
    DataTable
  }
}
</script>

<style>

</style>
