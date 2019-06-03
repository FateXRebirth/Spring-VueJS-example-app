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
        <Header title="Upgrade" />
        <hr class="hr-30">
        <el-form :model="upgradeForm" status-icon :rules="rules" ref="upgradeForm" label-position="labelPosition">
          <el-form-item label="Type" prop="type" label-width="100px">
            <Select :data="options" :default="upgradeForm.type" type="type" @callback="Callback" />
          </el-form-item>
          <el-form-item label="Name" prop="name" label-width="100px">
            <el-input v-model="upgradeForm.name" placeholder="name here"></el-input>
          </el-form-item>
          <el-form-item label="Phone" prop="phone" label-width="100px">
            <el-input v-model="upgradeForm.phone" placeholder="phone here"></el-input>
          </el-form-item>
          <el-form-item label="Address" prop="address" label-width="100px">
            <el-input v-model="upgradeForm.address" placeholder="address here"></el-input>
          </el-form-item>
          <div class="actions">
            <el-button type="primary" @click="submitForm('upgradeForm')">Submit</el-button>
            <el-button @click="resetForm('upgradeForm')">Reset</el-button>
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
import Select from '~/components/Select.vue';

export default {
  components: {
    SideMenu,
    Breadcrumb,
    Header,
    Select
  },
  middleware: 'auth',
  async asyncData({ app, store }) {
    const User = store.getters.getAuthenticatedUser;
    let Result = await app.$axios.get('/users/' + User.ID);
    if(Result.data.returnCode == 0) {
      return {
        upgradeForm: {
          type: Result.data.returnData.user.type,
          name: Result.data.returnData.user.name,
          phone: Result.data.returnData.user.phone,
          address: Result.data.returnData.user.address
        },
      }
    } else {
        throw new Error(Result.data.returnMessage)
    }
  },
  data() {
    var validateType = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('Please select the type'));
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
        callback(new Error('Please input the cellphone'));
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
      options: [{
        value: '1',
        label: '原廠認證'
      }, {
        value: '2',
        label: '公會認證'
      }, {
        value: '3',
        label: '網路好店'
      }, {
        value: '4',
        label: '平輸好店'
      }],
      labelPosition: 'left',
      rules: {
        type: [
          { validator: validateType, trigger: 'change' }
        ],
        name: [
          { validator: validateName, trigger: 'blur' }
        ],
        phone: [
          { validator: validatePhone, trigger: 'blur' }
        ],
        address: [
          { validator: validateAddress, trigger: 'blur' }
        ]
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const User = this.$store.getters.getAuthenticatedUser;
          const data = {
            type: this.upgradeForm.type.value,
            name: this.upgradeForm.name,
            phone: this.upgradeForm.phone,
            address: this.upgradeForm.address,
          }
          this.$axios.put('/users/upgrade/' + User.ID, data)
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
    },
    Callback(type, value) {
      this.upgradeForm[type] = value;
    }
  }
}
</script>

<style lang="scss" scoped>
.actions {
  margin-left: 100px;
}
.el-input, .el-select {
  width: 300px !important;
}
</style>
