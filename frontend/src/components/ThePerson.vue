<template>
  <div> Should appear if you click Person
      <router-view/>
      <DataTable/>
  </div>
</template>

<script>
import DataTable from './el-datatable.vue'

export default {
  name: 'ThePerson',
  components: {
    DataTable
  }
}
</script>

<style>

</style>
