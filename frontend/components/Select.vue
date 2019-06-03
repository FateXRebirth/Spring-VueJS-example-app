<template>
  <el-select v-model="value" @change="HandleChange" value-key="label" :placeholder="GetPlaceholder(type)">
    <el-option
      v-for="option in options"
      :key="option.value"
      :label="option.label"
      :value="option">
    </el-option>
  </el-select>
</template>

<script>
export default {
  props: ['data', 'type', 'default'],
  data() {
    return {
      value: null,
      options: this.data
    }
  },
  mounted() {
    if(this.default) {
      const value = this.default;
      this.value = _.find(this.options, function(option) {
        return option.value == value;
      })
    }
  },
  methods: {
    HandleChange() {
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
