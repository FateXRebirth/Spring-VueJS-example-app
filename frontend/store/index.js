import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const store = () => new Vuex.Store({
  
  state: {
    authUser: null,
    message: null,
  },

  mutations: {
    SET_USER: function (state, user) {
      state.authUser = user;
    },
    SET_MESSAGE: function (state, message) {
      state.message = message;
    },
  },

  getters: {
    getAuthenticatedUser: state => {
      return state.authUser;
    },
    isAuthenticated: state => {
      return state.authUser === null ? false : true;
    },
    getMessage: state => {
      return state.message;
    },
  },

  actions: {
    nuxtServerInit ({ commit }, { req }) {
      if (req.session && req.session.authUser) {
        commit('SET_USER', req.session.authUser)
      } else {
        commit('SET_USER', null)
      }
    },
    message ({ commit }, message ) {
      commit('SET_MESSAGE', message);
    },
  }

})

export default store;