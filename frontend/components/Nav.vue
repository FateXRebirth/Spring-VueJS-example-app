<template>
  <section class="nav">
    <ul class="links">
      <li class="link link--left" :class="{ active: path == '/'}"><a href="/">Home</a></li>
      <li class="link link--left" :class="{ active: path == '/dashboard'}"><a href="/dashboard">Dashboard</a></li>
      <li class="link link--left" :class="{ active: path == '/search'}"><a href="/search">Search</a></li>
      <li class="link link--left" :class="{ active: path == '/buy'}"><a href="/buy">Buy</a></li>
      <li class="link link--right" :class="{ active: path == '/logout'}" v-if="auth"><a href="#" @click="logout();">Logout</a></li>
      <li class="link link--right" :class="{ active: path == '/login'}" v-if="!auth"><a href="/login">Login</a></li>
      <li class="link link--right" :class="{ active: path == '/register'}" v-if="!auth"><a href="/register">Register</a></li>
      <div class="clear-fix"></div>
    </ul>
  </section>
</template>

<script>
export default {
  computed: {
    path: function() {
      return this.$router.history.current.path;
    },
    auth: function() {
      return this.$store.getters.getAuthenticatedUser;
    }
  },
  methods: {
    logout: function() {
      this.$axios.get('/api/logout', {
        // Send the client cookies to the server
        credentials: 'same-origin',
        method: 'POST'
      })
      .then((res) => {
        if (res.data.returnCode === 0) {
          this.$store.dispatch('logout'); 
          this.$message({
            showClose: true,
            message: 'Logout Successfully',
            type: 'success',
            duration: 1500
          });
          setTimeout(function() {
            window.location.href = '/';
          }, 1500)
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.nav {
  height: 50px;
  line-height: 60px;
  border-bottom: 1px #e6e6e6 solid;
  width: 50%;
  margin: 0 auto;
  & .links {
    list-style: none;
    height: 100%;
    & .link {
      display: inline-block;
      padding: 0 20px;
      color: #909399;
      height: 100%;
      & a {
        display: inline-block;
        text-decoration: none;
        color: #909399;
        height: 100%;
        width: 100%;
        &:hover {
          color: black;
        }
      }
      &--left {
        // margin-right: 20px;
        float: left;
      }
      &--right {
        // margin-left: 20px;
        float: right;
      }
      &.active {
        & a {
          text-decoration: none;
          color: #39AF78;
          border-bottom: 1px #39AF78 solid;
        }
      }
    }
  }
}
</style>
