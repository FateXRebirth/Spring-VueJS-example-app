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
    login ({ commit }, { authUser }) {
      commit('SET_USER', authUser);
    },
    logout ({ commit }) {
      commit('SET_USER', null);
    },
  }

})

export default store