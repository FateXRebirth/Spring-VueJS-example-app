import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const store = () => new Vuex.Store({
  
  state: {
    authUser: null
  },

  mutations: {
    SET_USER: function (state, user) {
      state.authUser = user
    }
  },

  getters: {
    getAuthenticatedUser: state => {
      return state.authUser;
    }
  },

  actions: {
    nuxtServerInit ({ commit }, { req }) {
      if (req.session && req.session.authUser) {
        commit('SET_USER', req.session.authUser)
      }
    },
    login ({ commit }, { username, password }) {
      return this.$axios.post('/api/login', {
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
          throw new Error(res.returnMessage)
        } else {
          return res.data.username;
        }
      })
      .then((authUser) => {
        commit('SET_USER', authUser);
      })
    },
    logout ({ commit }) {
      return this.$axios.get('/api/logout', {
        // Send the client cookies to the server
        credentials: 'same-origin',
        method: 'POST'
      })
      .then((res) => {
        if (res.data.returnCode === 0) {
          commit('SET_USER', null)
        }
      })
    }
  }

})

export default store