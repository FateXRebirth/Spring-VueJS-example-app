<template>
  <el-select v-model="value" @change="handleChange" :placeholder="type">
    <el-option
      v-for="item in options"
      :key="item.value"
      :label="item.label"
      :value="item.value">
    </el-option>
  </el-select>
</template>

<script>
export default {
  props: ['data', 'type'],
  data() {
    return {
      value: "",
      options: this.data
    }
  },
  methods: {
    handleChange(value) {
      this.$emit('callback', this.type, this.value);
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
