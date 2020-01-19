<template>
  <section class="talking">
    <div class="button" :class="{ 'hide': show }" @click="show = true; HandleLoad();">
       <i class="el-icon-chat-dot-square"></i> &nbsp; 聊聊
      <div class="notice" v-show="unread"></div>
    </div>
    <div class="model" :class="{ 'show': show }">
      <section class="list">
        <ul class="contacts">
          <li class="title">對話清單</li>
          <li class="contact" :class="{ 'active': selected == null ? false : dialogue.uuid == selected.uuid ? true : false, 'pending': dialogue.pending }" v-for="dialogue in dialogues" :key="dialogue.title" @click="HandleFetch(dialogue)">{{ dialogue.title }}<i class="el-icon-message"></i></li>
        </ul>
      </section>
      <section class="dialogue">
        <div class="header">對話內容
          <div class="close" @click="show = false"><i class="el-icon-minus"></i></div>
        </div>
        <div class="messages" id="messages" :class="{ 'messages--empty': dialogues.length == 0 }">
          <div class="empty">目前還沒有任何對話訊息喔！</div>
          <div class="notice" :class="{ 'show': hasPrompt }"><span>對面現在不在線上，您可以留言給他</span><i class="el-icon-close" @click="hasPrompt = !hasPrompt"></i></div>
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
        <div class="input" v-show="dialogues.length != 0">
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
      const source = JSON.parse(payload.data.dialogue);
      const message = JSON.parse(payload.data.message);
      if(!this.show) {
        this.unread = true;
        return;
      } else {
        this.unread = false;
        if(this.selected != null && this.selected.uuid == source.uuid) {
          this.messages.push(message);
          const scrollHeight = document.getElementById('messages').scrollHeight;
          document.getElementById('messages').scrollTo({ top: scrollHeight })
        } else {
          const isExist = this.dialogues.filter((dialogue) => dialogue.uuid == source.uuid).length == 0 ? false : true;
          if(isExist) {
            this.dialogues.map(dialogue => {
              if(dialogue.uuid == source.uuid) {
                dialogue.pending = true;
              }
            })
            this.$forceUpdate();
          } else {
            this.HandleLoad();
          }
        }
      }
    })
    this.User = this.$store.getters.getAuthenticatedUser;
  },
  data() {
    return {
      hasPrompt: false,
      show: false,
      unread: false,
      selected: null,
      isOnline: false,
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
  watch: {
    'dialogue': function(newValue, oldValue) {
      this.messages = [];
    }
  },
  methods: {
    HandleLoad: async function() {
      const Result = await this.$axios({
        method: 'get',
        url: '/users/dialogues/' + this.User.ID,
        headers: {
          'User': this.User.Username,
          'ID': this.User.ID,
          'Authorization': this.User.Token
        },
      });
      if(Result.data.returnCode == 0) {
        this.dialogues = Result.data.returnData.dialogue;
        if(this.dialogues.length) this.HandleFetch(this.dialogues[0]);
        this.show = true;
      } else {
        throw new Error(Result.data.returnMessage);
      }
    },
    HandleFetch: function(dialogue) {
      this.selected = dialogue;
      this.selected.pending = false;
      this.uuid = dialogue.uuid;
      this.$axios({
        method: 'get',
        url: `/users/isOnline/${this.User.ID == dialogue.sender ? dialogue.receiver : dialogue.sender}`,
        headers: {
          'User': this.User.Username,
          'ID': this.User.ID,
          'Authorization': this.User.Token
        },
      }).then((res => {
        if(res.data.returnCode == 0) {
          this.hasPrompt = !res.data.returnData.isOnline;
          this.isOnline = res.data.returnData.isOnline;
        } else {
          throw new Error(res.data.returnMessage)
        }
      }))
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
      const message = {
        uuid: this.uuid,
        speaker: this.User.Username,
        content: document.getElementById('content').value,
        time: new Date().toISOString()
      }
      this.messages.push(message);
      if(this.isOnline && this.token !== null && this.token !== undefined) {
        const data = {
          data: {
            dialogue: this.selected,
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
      }
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
            color: #39AF78;
          }
          &.pending {
            position: relative;
            & .el-icon-message {
              position: absolute;
              top: 1px;
              right: 1px
            }
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
        opacity: 0;
        transition: opacity 1s;
        pointer-events: none;
        &.show {
          opacity: 1;
          pointer-events: initial;
        }
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
        & .empty {
          display: none;
        }
        &--empty {
          height: 100%;
          & .empty {
            display: block;
            width: 100%;
            height: 100%;
            text-align: center;
            line-height: 500px;
          }
        }
        & .message {
          margin: 10px;
          display: flex;
          &--sender {
            flex-direction: row-reverse;
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
