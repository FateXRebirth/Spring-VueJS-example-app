<template>
  <section class="container">
    <div>
      <logo/>
      <h1 class="title">
        Nuxt
      </h1>
      <hr class="hr-20">
      <el-form :label-position="labelPosition" :model="loginForm" :rules="rules" ref="loginForm">
        <el-form-item label="Account" prop="account">
            <el-input type="text" v-model="loginForm.account" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="Password" prop="password">
            <el-input type="password" v-model="loginForm.password" auto-complete="off"></el-input>
        </el-form-item>
        <hr class="hr-20">
        <el-button type="primary" @click="submitForm('loginForm')">Login</el-button>
        <el-button @click="resetForm('loginForm')">Reset</el-button>
      </el-form>
      <hr class="hr-30">
      <span class="link"><a href="/register">Sign Up</a> for enjoying more powerful feature!</span>
      <hr class="hr-30">
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
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const username = this.loginForm.account;
          const password = this.loginForm.password; 
          this.$axios.post('/api/login', {
          // Send the client cookies to the server
            credentials: 'same-origin',
            method: 'POST',
            headers: {
              'Content-Type': 'application/json'
            },
            body: JSON.stringify({
              username,
              password
            })
          })
          .then((res) => {
            if (res.data.returnCode === 1) {
              throw new Error(res.data.returnMessage)
            } else {
              return res.data.username;
            }
          })
          .then((authUser) => {
            this.$store.dispatch('login', authUser);
            this.$message({
              showClose: true,
              message: 'Login Successfully',
              type: 'success',
              duration: 1500
            });
            // this.$router.push('/dashboard');
            setTimeout(function() {
              window.location.href = '/dashboard';
            }, 1500)
          })
          .catch((err) => {
            console.log(err)
            this.$message({
              showClose: true,
              message: 'Username or Password Not Found',
              type: 'error'
            });
          })
        } else {
          console.log('Validation Failure');
          return false;
        }
      });
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
  min-height: 700px;
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
span {
  width: 350px;
  display: block;
  margin: 0 auto;
}
</style>
