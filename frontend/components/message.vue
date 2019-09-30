<template>
  <section class="talking">
    <div class="button" @click="HandleOpen()">
       <i class="el-icon-chat-dot-square"></i> &nbsp; 聊聊
      <div class="notice"></div>
    </div>
    <div class="model show">
      <div class="header">與ＸＸＸ的對話
        <div class="close" @click="HandleClose()"><i class="el-icon-minus"></i></div>
      </div>
      <div class="messages">
        <p class="message message--receiver">我說：abc好車網 讚讚讚 abc好車網 讚讚讚 abc好車網 讚讚讚 abc好車網 讚讚讚 abc好車網 讚讚讚 abc好車網 讚讚讚</p>
        <p class="message message--sender">他說：abc好車網 爛爛爛 abc好車網 爛爛爛 abc好車網 爛爛爛 abc好車網 爛爛爛 abc好車網 爛爛爛 abc好車網 爛爛爛</p>
      </div>
      <div class="input">
        <textarea cols="30" rows="10" class="content" placeholder="輸入訊息..."></textarea>
        <button class="submit">送出</button>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  data() {
    return {
      Active: false
    }
  },
  methods: {
    HandleOpen: function() {
      this.$el.querySelector('.button').classList.add('hide');
      this.$el.querySelector('.model').classList.add('show');
    },
    HandleClose: function() {
      this.$el.querySelector('.model').classList.remove('show');
      this.$el.querySelector('.button').classList.remove('hide');
    }
  }
}
</script>

<style lang="scss" scoped>
.talking {
  & .button {
    cursor: pointer;
    position: fixed;
    right: 0;
    bottom: 0;
    margin-bottom: 100px;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100px;
    width: 50px;
    writing-mode: vertical-lr;
    font-size: 20px;
    color: white;
    background-color: #39AF78;
    border-top-left-radius: 20px;
    border-bottom-left-radius: 20px;
    text-align: center;
    transition: 0.3s all ease-in-out;
    &.hide {
      margin-right: -50px;
    }
    & .notice {
      position: absolute;
      left: 0;
      top: 0;
      margin-left: -5px;
      margin-top: -5px;
      width: 20px;
      height: 20px;
      border-radius: 50%;
      background-color: lightcoral;
    }
  }
  & .model {
    position: fixed;
    right: 0;
    bottom: 0;
    width: 500px;
    height: 500px;
    background-color: white;
    box-shadow: 0 -2px 10px rgba(0,0,0,.25);
    border-radius: 8px 8px 0 0;
    margin-right: -100vw;
    transition: 0.3s all ease-in-out;
    &.show {
      margin-right: initial;
    }
    & .header {
      position: relative;
      height: 30px;
      line-height: 30px;
      text-align: center;
      width: 100%;
      background-color: #39AF78;
      & .close {
        cursor: pointer;
        display: flex;
        justify-content: center;
        align-items: center;
        color: white;
        position: absolute;
        top: 50%;
        margin-top: -10px;
        right: 10px;
        width: 20px;
        height: 20px;
        & .el-icon-minus {
          font-size: 20px;
        }
      }
    }
    & .messages {
      height: 300px;
      width: 100%;
      overflow-y: scroll;
      background-color: #f6f6f6;
      border: 1px red solid;
      & .message {
        margin: 5px 0px;
        &--sender {
          float: right;
        }
        &--receiver {
          float: left;
        }
      }
    }
    & .input {
      clear: both;
      & .content {
        width: 100%;
        height: 120px;
        border: none;
        padding: 5px;
        outline: 0;
        resize: none;
      }
      & .submit {
        float: right;
        margin-right: 15px;
        display: block;
        width: 70px;
        height: 30px;
        line-height: 30px;
        color: white;
        border: none;
        background-color: #ff5722;
      }
    }
  }
}
</style>
