<template>
  <section class="container">
    <div>
      <logo/>
      <h1 class="title">
        Nuxt
      </h1>
      <hr class="hr-20">
      <el-form :label-position="labelPosition" :model="registerForm" :rules="rules" ref="registerForm">
        <el-form-item label="Account" prop="account">
          <el-input type="text" v-model="registerForm.account" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="Email" prop="email">
          <el-input type="email" v-model="registerForm.email" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="Password" prop="password">
          <el-input type="password" v-model="registerForm.password" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="Confirm" prop="confirmation">
          <el-input type="password" v-model="registerForm.confirmation" auto-complete="off"></el-input>
        </el-form-item>
        <hr class="hr-20">
        <el-button type="primary" @click="submitForm('registerForm')">Register</el-button>
        <el-button @click="resetForm('registerForm')">Reset</el-button>
      </el-form>
      <hr class="hr-30">
      <span class="link"> Already have a account ? <a href="/login"> Login </a> Now ! </span>
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
    var validateEmail = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('Please input the email'));
      } else {
        callback();
      }
    };
    var validatePassword = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('Please input the password'));
        } else if (value.length < 6 || value.length > 12) {
        callback(new Error('Password must be greater than 6 and less than 12'));
        } else {
          if (this.registerForm.confirmation !== '') {
            this.$refs.registerForm.validateField('confirmation');
          }
          callback();
        }
      };
    var validateConfirmation = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('Please input the password again'));
      } else if (value.length < 6 || value.length > 12) {
        callback(new Error('Password must be greater than 6 and less than 12'));
      } else if (value !== this.registerForm.password) {
        callback(new Error('Two inputs don\'t match!'));
      } else {
        callback();
      }
    }; 
    return {
      labelPosition: 'left',
      registerForm: {
        account: '',
        email: '',
        password: '',
        confirmation: '',
      },
      rules: {
        account: [
          { validator: validateAccount, trigger: 'blur' }
        ],
        email: [
          { validator: validateEmail, trigger: 'blur' }
        ],
        password: [
          { validator: validatePassword, trigger: 'blur' }
        ],
        confirmation: [
          { validator: validateConfirmation, trigger: 'blur' }
        ],
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          alert('submit!');
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
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
