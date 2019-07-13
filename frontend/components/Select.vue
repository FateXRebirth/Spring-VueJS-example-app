<template>
  <el-select v-model="option" @change="HandleChange" value-key="label" :placeholder="GetPlaceholder(type)" :loading="loading" :disabled="isDisable">
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
  props: ['options', 'type', 'isDisable', 'value'],
  data() {
    return {
      option: null,
      loading: true,
      done: false
    }
  },
  watch: {
    options: function(newValue, oldValue) {
      this.loading = false;
      this.option = null;
      if(this.value && !this.done) {
        this.done = true;
        const value = this.value;
        this.option = _.find(this.options, function(option) {
          return option.value == value;
        })
        this.HandleChange()
      }
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
