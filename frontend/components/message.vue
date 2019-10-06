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
      <div class="messages" id="messages">
        <div class="notice" v-show="notice"><span>對面現在不在線上，您可以留言給他</span><i class="el-icon-close" @click="notice = false"></i></div>
        <div class="message message--receiver"><i class="el-icon-user-solid"></i><p class="text">他說：abc好車網 爛爛爛 abc好車網 爛爛爛 abc好車網 爛爛爛 abc好車網 爛爛爛 abc好車網 爛爛爛 abc好車網 爛爛爛</p></div>
        <div class="message message--sender"><i class="el-icon-s-custom"></i><p class="text">我說：abc好車網 讚讚讚 abc好車網 讚讚讚 abc好車網 讚讚讚 abc好車網 讚讚讚 abc好車網 讚讚讚 abc好車網 讚讚讚</p></div>
        <div class="message message--sender" v-for="message in messages" :key="message"><i class="el-icon-s-custom"></i><p class="text">我說：{{ message }}</p></div>
      </div>
      <div class="input">
        <textarea cols="30" rows="10" class="content" placeholder="輸入訊息..." id="content" @keyup.enter.exact="HandleClick()"></textarea>
        <button id="submit" class="submit" @click="HandleClick()">送出</button>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  data() {
    return {
      notice: true,
      messages: []
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
    },
    HandleClick: function() {
      this.messages.push(document.getElementById('content').value);
      setTimeout(() => {
        document.getElementById('content').value = '';
        document.getElementById('messages').scrollTo({ top: 330 });
      })
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
    & .notice {
      position: absolute;
      top: 30px;
      width: 100%;
      height: 30px;
      line-height: 30px;
      background: #fffaeb;
      border: 1px solid #feecb6;
      color: #67541F;
      font-size: 12px;
      font-weight: bold;
      text-align: center;
      & .el-icon-close {
        position: absolute;
        top: 50%;
        right: 0;
        margin-top: -10px;
        margin-right: 10px;
        font-size: 20px;
      }
    }
    & .messages {
      height: 330px;
      width: 100%;
      overflow-y: scroll;
      background-color: #f6f6f6;
      & .message {
        margin: 10px;
        display: flex;
        &--sender {
          flex-direction: row-reverse;
        }
        &--receiver {
        }
        & .text {
          max-width: calc(100% - 40px);
          background-color: #39AF78;
          color: white;
          padding: 10px;
          border-radius: 10px;
          word-break: break-all;
        }
        & .el-icon-user-solid {
          font-size: 30px;
          margin-right: 10px;
        }
        & .el-icon-s-custom {
          font-size: 30px;
          margin-left: 10px;
        }
      }
    }
    & .input {
      clear: both;
      & .content {
        width: 100%;
        height: 100px;
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
