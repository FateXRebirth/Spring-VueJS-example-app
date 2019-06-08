<template>
  <el-select v-model="option" @change="HandleChange" value-key="label" :placeholder="GetPlaceholder(type)" :loading="loading">
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
  props: ['options', 'type', 'default'],
  data() {
    return {
      option: null,
      loading: true
    }
  },
  mounted() {
    if(this.default) {
      const value = this.default;
      this.option = _.find(this.options, function(option) {
        return option.value == value;
      })
    }
  },
  watch: {
    options: function(newValue, oldValue) {
      this.loading = false;
      this.option = null;
    }
  },
  methods: {
    HandleChange() {
      this.$emit('callback', this.type, this.option);
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
