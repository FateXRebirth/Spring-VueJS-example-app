<template>
   <ul class="menu">
    <li class="submenu submenu--title">
      Dashboard
    </li>
    <li class="submenu">
      <a href="/dashboard" :class="{ active: PATH == '/dashboard'}">
        <i class="el-icon-menu"></i> Home
      </a>
    </li>
    <li class="submenu">
      <a href="/dashboard/info" :class="{ active: PATH == '/dashboard/info'}">
        <i class="el-icon-info"></i> Info
      </a>
    </li>
    <li class="submenu">
      <a href="/dashboard/favorite" :class="{ active: PATH == '/dashboard/favorite'}">
        <i class="el-icon-star-on"></i> Favorite
      </a>
    </li>
    <li class="submenu">
      <a href="/dashboard/message" :class="{ active: PATH == '/dashboard/message'}">
        <i class="el-icon-message"></i> Message
      </a>
    </li>
    <li class="submenu" v-if="TYPE == 0">
      <a href="/dashboard/upgrade" :class="{ active: PATH == '/dashboard/upgrade'}">
        <i class="el-icon-news"></i> Upgrade
      </a>
    </li>
    <li class="submenu">
      <a href="/dashboard/management" :class="{ active: PATH == '/dashboard/management'}">
        <i class="el-icon-setting"></i> Management
      </a>
    </li>
  </ul>
</template>

<script>
export default {
  computed: {
    PATH: function() {
      return this.$router.history.current.path;
    },
    TYPE: function() {
      return this.$store.getters.getAuthenticatedUser.Type;
    }
  }
}
</script>

<style lang="scss" scoped>
.menu {
  min-height: 750px;
  background-color: #f3f3f3;
  background-image: linear-gradient(to bottom,#f3f3f3 0,#fff 100%);
  background-repeat: repeat-x;
  .submenu {
    padding: 20px 0px;
    padding-left: 20px;
    a {
      // color: #909399;
      color: #666666;
      text-decoration: none;
      &.active {
        color: #39AF78;
      }
    }
    i {
      padding-right: 10px;
      &:before {
        font-size: 20px;
      }
    }
    &--title {
      text-align: center;
      padding-left: 0;
      color: white;
      font-weight: 600;
      font-size: 25px;
      background-color: rgba($color: #39AF78, $alpha: 0.7);
    }
  }
}
</style>
