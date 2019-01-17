<template>
  <section class="container">
    <hr class="hr-30">
      <Breadcrumb />
    <hr class="hr-30">
    <el-container>
      <el-aside width="200px">
        <SideMenu />
      </el-aside>
      <el-main>   
        <Header title="Information" />
        <hr class="hr-30">
        <el-form :model="infoForm" status-icon :rules="rules" ref="infoForm" label-width="100px" label-position="labelPosition">
          <el-form-item label="Type">
            個人
          </el-form-item>
          <el-form-item label="Account" prop="account">
            <el-input v-model="infoForm.account"></el-input>
          </el-form-item>
          <el-form-item label="E-mail" prop="email">
            <el-input v-model="infoForm.email" :disabled="true"></el-input>
          </el-form-item>
          <el-form-item label="Password" v-if="!change">
            <el-input type="password" v-model="infoForm.passwordOld" :disabled="true"></el-input>
          </el-form-item>
          <div v-if="change">
            <el-form-item label="Password" prop="password">
              <el-input type="password" v-model="infoForm.password"></el-input>
            </el-form-item>
            <el-form-item label="Confirmation" prop="confirmation">
              <el-input type="password" v-model="infoForm.confirmation"></el-input>
            </el-form-item>
          </div>
          <el-form-item>
            <el-button type="primary" @click="submitForm('infoForm')">Submit</el-button>
            <el-button @click="resetForm('infoForm')">Reset</el-button>
            <el-button type="warning" @click="changePassword()" v-if="!change">Change Password</el-button>
            <el-button type="danger" @click="changePassword()" v-if="change">Cancel</el-button>
          </el-form-item>
        </el-form>
      </el-main>
    </el-container>
  </section>
</template>

<script>
import SideMenu from '~/components/SideMenu.vue';
import Breadcrumb from '~/components/Breadcrumb.vue';
import Header from '~/components/Header.vue';

export default {
  components: {
    SideMenu,
    Breadcrumb,
    Header
  },
  middleware: 'auth',
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
          if (this.infoForm.confirmation !== '') {
            this.$refs.infoForm.validateField('confirmation');
          }
          callback();
        }
      };
    var validateConfirmation = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('Please input the password again'));
      } else if (value.length < 6 || value.length > 12) {
        callback(new Error('Password must be greater than 6 and less than 12'));
      } else if (value !== this.infoForm.password) {
        callback(new Error('Two inputs don\'t match!'));
      } else {
        callback();
      }
    }; 
    return {
      change: false,
      labelPosition: 'left',
      infoForm: {
        account: 'admin',
        email: 'admin@example.com',
        passwordOld: '123456',
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
    changePassword() {
      if(this.change == false) {
        this.change = true;
      } else {
        this.change = false;
        this.infoForm.password = '';
        this.infoForm.confirmation = '';
      }
    },
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
.el-container {
  min-height: 750px;
}
</style>
