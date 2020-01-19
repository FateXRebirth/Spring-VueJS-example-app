import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const store = () => new Vuex.Store({

  state: {
    authUser: null
  },

  mutations: {
    SET_USER: function (state, user) {
      state.authUser = user;
    }
  },

  getters: {
    getAuthenticatedUser: state => {
      return state.authUser;
    },
    isAuthenticated: state => {
      return state.authUser === null ? false : true;
    }
  },

  actions: {
    nuxtServerInit ({ commit }, { req }) {
      if (req.session && req.session.authUser) {
        commit('SET_USER', req.session.authUser)
      } else {
        commit('SET_USER', null)
      }
    }
  }

})

export default store;
