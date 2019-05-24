<template>
  <el-select v-model="value" @change="HandleChange" :placeholder="GetPlaceholder(type)">
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
    HandleChange(value) {
      this.$emit('callback', this.type, this.value);
    },
    GetPlaceholder(type) {
      return type.charAt(0).toUpperCase() + type.slice(1);
    }
  }
}
</script>

<style lang="scss" scoped>
.el-select {
  width: 100%;
}
</style>
