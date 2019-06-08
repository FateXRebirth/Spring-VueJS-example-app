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
        <el-form :model="infoForm" status-icon :rules="rules" ref="infoForm" label-position="labelPosition">
          <el-form-item label="Type" label-width="100px">
            <span v-if="infoForm.type == 0">個人</span>
            <span v-if="infoForm.type == 1">原廠認證</span>
            <span v-if="infoForm.type == 2">公會認證</span>
            <span v-if="infoForm.type == 3">網路好店</span>
            <span v-if="infoForm.type == 4">平輸好店</span>
          </el-form-item>
          <el-form-item label="Account" prop="account" label-width="100px"> 
            <el-input v-model="infoForm.account" placeholder="account here"></el-input>
          </el-form-item>
          <el-form-item label="E-mail" prop="email" label-width="100px">
            <el-input v-model="infoForm.email" :disabled="true" placeholder="e-mail here"></el-input>
          </el-form-item>
          <el-form-item label="Password" v-if="!change" label-width="100px">
            <el-input type="password" v-model="infoForm.passwordOld" :disabled="true" placeholder="password here"></el-input>
          </el-form-item>
          <div v-if="change">
            <el-form-item label="Password" prop="password" label-width="100px">
              <el-input type="password" v-model="infoForm.password" placeholder="password here"></el-input>
            </el-form-item>
            <el-form-item label="Confirmation" prop="confirmation" label-width="100px">
              <el-input type="password" v-model="infoForm.confirmation" placeholder="confirmation here"></el-input>
            </el-form-item>
          </div>
          <div v-if="infoForm.type != 0">
            <el-form-item label="Name" prop="name" label-width="100px"> 
              <el-input v-model="infoForm.name" placeholder="name here"></el-input>
            </el-form-item>
            <el-form-item label="Phone" prop="phone" label-width="100px"> 
              <el-input v-model="infoForm.phone" placeholder="phone here"></el-input>
            </el-form-item>
            <el-form-item label="address" prop="address" label-width="100px"> 
              <el-input v-model="infoForm.address" placeholder="address here"></el-input>
            </el-form-item>
          </div>
          <div class="actions">
            <el-button type="primary" @click="submitForm('infoForm')">Submit</el-button>
            <el-button @click="resetForm('infoForm')">Reset</el-button>
            <el-button type="warning" @click="changePassword()" v-if="!change">Change Password</el-button>
            <el-button type="danger" @click="changePassword()" v-if="change">Cancel</el-button>
          </div>
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
  async asyncData({ app, store }) {
    const User = store.getters.getAuthenticatedUser;
    let Result = await app.$axios.get('/users/' + User.ID);
    if(Result.data.returnCode == 0) {
      return {
        infoForm: {
          account: Result.data.returnData.user.account,
          email: Result.data.returnData.user.email,
          passwordOld: Result.data.returnData.user.password,
          password: Result.data.returnData.user.password,
          confirmation: "",
          type: Result.data.returnData.user.type,
          name: User.Type != 0 ? Result.data.returnData.user.name : "",
          phone: User.Type != 0 ? Result.data.returnData.user.phone : "",
          address: User.Type != 0 ? Result.data.returnData.user.address : "",
        },
      }
    } else {
        throw new Error(Result.data.returnMessage)
    }
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
    var validateName = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('Please input the name'));
      } else {
        callback();
      }
    };
    var validatePhone = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('Please input the phone'));
      } else {
        callback();
      }
    };
    var validateAddress = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('Please input the address'));
      } else {
        callback();
      }
    };
    return {
      change: false,
      labelPosition: 'left',
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
        name: [
          { validator: validateName, trigger: 'blur' }
        ],
        phone: [
          { validator: validatePhone, trigger: 'blur' }
        ],
        address: [
          { validator: validateAddress, trigger: 'blur' }
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
          const User = this.$store.getters.getAuthenticatedUser;
          const data = {
            account: this.infoForm.account,
            password: this.infoForm.password,
            name: this.infoForm.name ? this.infoForm.name : "",
            phone: this.infoForm.phone ? this.infoForm.phone : "",
            address: this.infoForm.address ? this.infoForm.address : ""
          }
          this.$axios.put('/users/edit/' + User.ID, data)
          .then((res) => {
            if (res.data.returnCode != 0) {
              this.$message({
                showClose: true,
                message: res.data.returnMessage,
                type: 'error',
                duration: 1500
              });
              throw new Error(res.data.returnMessage)
            } else {
              this.$message({
                showClose: true,
                message: res.data.returnMessage,
                type: 'success',
                duration: 1500
              });
              setTimeout(function() {
                window.location.reload();
              }, 1500)
            }
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
  }
}
</script>

<style lang="scss" scoped>
.actions {
  margin-left: 100px;
}
.el-input {
  width: 300px;
}
</style>
