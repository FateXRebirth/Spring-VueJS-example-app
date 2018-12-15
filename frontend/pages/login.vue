<template>
  <section class="container">
    <div>
      <logo/>
      <h1 class="title">
        Nuxt
      </h1>
      <el-form :label-position="labelPosition" :model="loginForm" :rules="rules" ref="loginForm">
        <el-form-item label="Account" prop="account">
            <el-input type="text" v-model="loginForm.account" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="Password" prop="password">
            <el-input type="password" v-model="loginForm.password" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="submitForm('loginForm')">Login</el-button>
            <el-button @click="resetForm('loginForm')">Reset</el-button>
        </el-form-item>
      </el-form>
      <span class="link"><nuxt-link to="/register">Sign Up</nuxt-link> for enjoying more powerful feature!</span>
    </div>
  </section>
</template>

<script>
import Logo from '~/components/Logo.vue'

export default {
  components: {
    Logo
  },
  data() {
     var validateAccount = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('Please input the account'));
      } else {
        callback();
      }
    };
    var validatePassword = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('Please input the password'));
      } else if (value.length < 5 || value.length > 12) {
        callback(new Error('Password must be greater than 5 and less than 12'));
      } else {
        callback();
      }
    };
    return {
      labelPosition: 'top',
      loginForm: {
        account: '',
        password: '',
      },
      rules: {
        account: [
          { validator: validateAccount, trigger: 'blur' }
        ],
        password: [
          { validator: validatePassword, trigger: 'blur' }
        ],
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$store.dispatch('login', { username:"admin", password: "admin"});
      // this.$refs[formName].validate((valid) => {
      //   if (valid) {
      //     this.$axios.$post("/login", {
      //       username: this.loginForm.account,
      //       password: this.loginForm.password
      //     })
      //     .then((res) => {
      //       console.log(res.returnCode)
      //     })
      //   } else {
      //     console.log('error submit!!');
      //     return false;
      //   }
      // });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  },
}
</script>

<style lang="scss" scoped>
.container
{
  min-height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
}
.title
{
  font-family: "Quicksand", "Source Sans Pro", -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif; /* 1 */
  display: block;
  font-weight: 300;
  font-size: 100px;
  color: #35495e;
  letter-spacing: 1px;
}
.subtitle
{
  font-weight: 300;
  font-size: 42px;
  color: #526488;
  word-spacing: 5px;
  padding-bottom: 15px;
}
.link {
  color: #999;
  a {
    &:hover {
      color: red;
    }
  }
}
</style>
