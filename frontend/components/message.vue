<template>
  <section class="talking">
    <div class="button" @click="HandleToggle()">
       <i class="el-icon-chat-dot-square"></i> &nbsp; 聊聊
      <div class="notice"></div>
    </div>
    <div class="model">
      <section class="list">
        <ul class="contacts">
          <li class="title">對話清單</li>
          <li class="contact" v-for="dialogue in dialogues" :key="dialogue.title" @click="HandleFetch(dialogue)">{{ dialogue.title }}</li>
        </ul>
      </section>
      <section class="dialogue">
        <div class="header">與 XXX 的對話
          <div class="close" @click="HandleToggle()"><i class="el-icon-minus"></i></div>
        </div>
        <div class="messages" id="messages">
          <div class="notice"><span>對面現在不在線上，您可以留言給他</span><i class="el-icon-close"></i></div>
          <div class="message" v-for="text in OrderedMessages" :key="text.time" :class="{ 'message--receiver': text.speaker != User.Username, 'message--sender': text.speaker == User.Username }">
            <i class="el-icon-user-solid" v-if="text.speaker != User.Username"></i>
            <i class="el-icon-s-custom" v-if="text.speaker == User.Username"></i>
            <p class="text">{{ text.content }}</p>
          </div>
          <div class="message" v-for="message in messages" :key="message.time" :class="{ 'message--receiver': message.speaker != User.Username, 'message--sender': message.speaker == User.Username }">
            <i class="el-icon-user-solid" v-if="message.speaker != User.Username"></i>
            <i class="el-icon-s-custom" v-if="message.speaker == User.Username"></i>
            <p class="text">{{ message.content }}</p>
          </div>
        </div>
        <div class="input">
          <textarea cols="30" rows="10" class="content" placeholder="輸入訊息..." id="content" @keyup.enter.exact="HandleType()"></textarea>
          <button id="submit" class="submit" @click.stop="HandleType()">送出</button>
        </div>
      </section>
    </div>
  </section>
</template>

<script>
export default {
  mounted() {
    $messaging.onMessage(payload => {
      this.messages.push(JSON.parse(payload.data.message));
      setTimeout(() => {
        const scrollHeight = document.getElementById('messages').scrollHeight;
        document.getElementById('messages').scrollTo({ top: scrollHeight })
      })
    })
    if(this.$store.getters.isAuthenticated) {
      this.User = this.$store.getters.getAuthenticatedUser;
      this.$axios({
        method: 'get',
        url: '/users/dialogues/' + this.User.ID,
        headers: {
          'User': this.User.Username,
          'ID': this.User.ID,
          'Authorization': this.User.Token
        },
      }).then((res => {
        if(res.data.returnCode == 0) {
          this.dialogues = res.data.returnData.dialogue;
          this.HandleFetch(this.dialogues[0]);
        } else {
            throw new Error(res.data.returnMessage)
        }
      }))
    }
  },
  data() {
    return {
      User: null,
      uuid: null,
      token: '',
      dialogues: [],
      dialogue: [],
      messages: [],
    }
  },
  computed: {
    OrderedMessages: function() {
      return this.dialogue.sort((a, b) => {
        const timeA = new Date(a.time).getTime();
        const timeB = new Date(b.time).getTime();
        if(timeA > timeB) return 1;
        if(timeA < timeB) return -1;
        return 0;
      })
    }
  },
  methods: {
    HandleToggle: function() {
      this.$el.querySelector('.button').classList.toggle('hide');
      this.$el.querySelector('.model').classList.toggle('show');
    },
    HandleFetch: function(dialogue) {
      this.uuid = dialogue.uuid;
      if(this.User.ID == dialogue.sender) {
        this.token = dialogue.receiverToken;
      } else {
        this.token = dialogue.senderToken;
      }
      this.$axios({
        method: 'get',
        url: `/users/messages/${this.uuid}`,
        headers: {
          'User': this.User.Username,
          'ID': this.User.ID,
          'Authorization': this.User.Token
        },
      }).then((res) => {
        if (res.data.returnCode == 0) {
          this.dialogue = res.data.returnData.message;
          setTimeout(() => {
            const scrollHeight = document.getElementById('messages').scrollHeight;
            document.getElementById('messages').scrollTo({ top: scrollHeight })
          })
        } else {
          throw new Error(res.data.returnMessage)
        }
      })
    },
    HandleType: function() {
      if(document.getElementById('content').value == "") return;
      const message = {
        uuid: this.uuid,
        speaker: this.User.Username,
        content: document.getElementById('content').value,
        time: new Date().toISOString()
      }
      this.messages.push(message);
      const data = {
        data: {
          message: message
        },
        to: this.token
      }
      this.$axios({
        url: 'https://fcm.googleapis.com/fcm/send',
        method: 'POST',
        headers: {
            'Authorization': `key=${$messaging.getServerKey()}`,
            'Content-Type': 'application/json',
        },
        data: data
      }).then((res) => {
        console.log(res);
      })
      this.$axios({
        method: 'post',
        url: '/users/messages',
        headers: {
          'User': this.User.Username,
          'ID': this.User.ID,
          'Authorization': this.User.Token
        },
        data: message
      }).then((res) => {
        if (res.data.returnCode != 0) {
          throw new Error(res.data.returnMessage)
        }
      })
      setTimeout(() => {
        document.getElementById('content').value = '';
        const scrollHeight = document.getElementById('messages').scrollHeight;
        document.getElementById('messages').scrollTo({ top: scrollHeight });
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
    width: 570px;
    height: 510px;
    background-color: white;
    box-shadow: 0 -2px 10px rgba(0,0,0,.25);
    border-radius: 8px 8px 0 0;
    margin-right: -100vw;
    transition: 0.3s all ease-in-out;
    display: flex;
    &.show {
      margin-right: initial;
    }
    & .list {
      flex: 3;
      border-right: 1px solid #ddd;
      & .contacts {
        & .title {
          height: 30px;
          line-height: 30px;
          text-align: center;
          width: 100%;
          background-color: #39AF78;
        }
        & .contact {
          text-overflow: ellipsis;
          overflow: hidden;
          cursor: pointer;
          display: block;
          height: 50px;
          line-height: 50px;
          width: 100%;
          text-decoration: none;
          transition: background .25s ease;
          color: grey;
          padding: 0 4px;
          border-bottom: 1px solid #efefef;
          user-select: none;
          &.active, &:hover {
            color: #fff;
            background: grey;
          }
        }
      }
    }
    & .dialogue {
      flex: 7;
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
        width: 70%;
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
}
</style>
