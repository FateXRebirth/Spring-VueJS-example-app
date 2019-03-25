<template>
  <el-breadcrumb separator="/">
    <el-breadcrumb-item v-for="path in paths" :key="path.url">
      <a :href="path.url">{{ path.name }}</a>
    </el-breadcrumb-item>
  </el-breadcrumb>
</template>

<script>
export default {
  computed: {
    paths: function() {
      let paths = [];
      let path = {};
      this.$router.history.current.path.split('/').forEach((data) => {
        if(data == '') {
          path.url = '/';
          path.name = 'Home';
        } else {
          path.url = data;
          path.name = data.replace(/^\w/, c => c.toUpperCase());
        }
        paths.push(path);
        path = {};
      })
      return paths;
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
