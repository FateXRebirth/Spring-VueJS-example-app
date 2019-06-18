<template>
  <section class="nav">
    <ul class="links">
      <li class="logo-left"><a href="/"><img src="/images/logo.png"/></a></li>
      <li class="logo-center"><a href="/"><img src="/images/logo.svg"/></a></li>
      <li class="link link--left" :class="{ active: path == '/'}"><a href="/">Home</a></li>
      <li class="link link--left" :class="{ active: path == '/search'}"><a href="/search">Search</a></li>
      <li class="link link--right" :class="{ active: path == '/dashboard'}"><a href="/dashboard">Dashboard</a></li>
      <li class="link link--right" :class="{ active: path == '/logout'}" v-if="auth"><a href="#" @click="logout();">Logout</a></li>
      <li class="link link--right" :class="{ active: path == '/login'}" v-if="!auth"><a href="/login">Login</a></li>
      <li class="link link--right" :class="{ active: path == '/register'}" v-if="!auth"><a href="/register">Register</a></li>
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
      // Clear Session
      this.$axios.get(process.env.BASE_URL + '/api/session')
      .then((res) => {
        if(res.data.returnCode != 0) {
          throw new Error("Server Error");
        } else {
          this.$message({
            showClose: true,
            message: 'Logout Successfully',
            type: 'success',
            duration: 1500
          });
          const Vue = this;
          setTimeout(function() {
            window.location.href = '/';
            // Clear Client
            this.$store.dispatch('logout'); 
          }, 1500)
        }
      })
    }
  },
  mounted() {
    $(window).scroll( () => {
      if($(window).scrollTop() > 50) {
        $(this.$el).addClass("active");
      } else {
        $(this.$el).removeClass("active");
      }
    })
  }
}
</script>

<style lang="scss" scoped>
* {
  transition: all 0.3s ease;
}
.nav {
  height: 50px;
  line-height: 50px;
  border-bottom: 1px #e6e6e6 solid;
  width: 100%;
  & .links {
    position: relative;
    list-style: none;
    width: 1200px;
    margin: 0 auto;
    display: flex;
    justify-content: flex-end;
    & .logo-center {
      display: none;
    }
    & .logo-left {
      display: block;
      position: absolute;
      left: 0;
      & img {
        height: 50px;
        width: auto;
      }
    }
    & .link {
      display: inline-block;
      padding: 0 20px;
      color: #909399;
      height: 50px;
      & a {
        display: inline-block;
        text-decoration: none;
        color: #909399;
        &:hover {
          color: black;
        }
      }
      &.active {
        border-bottom: 1px #39AF78 solid;
        & a {
          font-weight: bold;
          color: #39AF78;
        }
      }
    }
  }

  &.active {
    // background-color: #41B883;
    background-color: #39AF78;
    position: fixed;
    top: 0;
    z-index: 10;
    border: none;
    & .links {
      display: block;
      & .link {
        &--left {
          float: left;
        }
        &--right {
          float: right;
        }
        & a {
          color: white;
        }
        &.active {
          background-color: rgba(#34495D, 0.3);
          border: none;
        }
      }
    }
    & .logo-left {
      display: none;
    }
    & .logo-center {
      display: block;
      position: absolute;
      left: 50%;
      transform: translateX(-50%);
      & img {
        height: 50px;
        display: block;
      }
    }
  }
}
</style>
