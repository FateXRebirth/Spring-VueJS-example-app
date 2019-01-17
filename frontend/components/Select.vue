<template>
  <el-select v-model="value" @change="handleChange($event)" :placeholder="type">
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
    handleChange(event) {
      this.$emit('callback', this.value);
    }
  }
}
</script>

<style lang="scss" scoped>
.el-select {
  width: 100%;
}
</style>
