<template>
  <div id="root">
    <img class="logo" src="../assets/logo.png">
    <h1 class="slogan">{{ msg }}</h1>
    <router-link to="/index" class="link">Get Know More...</router-link>
    <div class="more">
      <h2>Essential Links</h2>
      <ul>
        <li><a href="https://vuejs.org" target="_blank">Core Docs</a></li>
        <li><a href="https://forum.vuejs.org" target="_blank">Forum</a></li>
        <li><a href="https://chat.vuejs.org" target="_blank">Community Chat</a></li>
        <li><a href="https://twitter.com/vuejs" target="_blank">Twitter</a></li>
        <li><a href="http://vuejs-templates.github.io/webpack/" target="_blank">Docs for This Template</a></li>
      </ul>
      <h2>Ecosystem</h2>
      <ul>
        <li><a href="http://router.vuejs.org/" target="_blank">vue-router</a></li>
        <li><a href="http://vuex.vuejs.org/" target="_blank">vuex</a></li>
        <li><a href="http://vue-loader.vuejs.org/" target="_blank">vue-loader</a></li>
        <li><a href="https://github.com/vuejs/awesome-vue" target="_blank">awesome-vue</a></li>
      </ul>
    </div>
  </div>

</template>

<script>
export default {
  name: 'BaseRoot',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

#root {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
  position: relative;
  height: 1000px;
}

.slogan {
  margin-top: 50px;
  font-size: 50px;
}

.link {
  position: absolute;
  text-decoration: unset;
  font-size: 30px;
  display: block;
  width: 300px;
  height: 100px;
  left: 50%;
  top: 50%;
  margin-left: -150px;
}

.more {
  position: absolute;
  bottom: 0;
  height: 100px;
  width: 600px;
  margin-left: -300px;
  left: 50%;
}

h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 10px;
}
a {
  color: #42b983;
}

</style>