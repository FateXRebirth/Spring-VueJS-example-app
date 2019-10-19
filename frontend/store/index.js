import Vue from 'vue'
import Vuex from 'vuex'
// import user from './modules/user'
// import car from './modules/car'

Vue.use(Vuex)

const store = () => new Vuex.Store({
  
  state: {
    authUser: null,
    message: null,
    toggle: false
  },

  mutations: {
    SET_USER: function (state, user) {
      state.authUser = user
    },
    SET_MESSAGE: function (state, message) {
      state.message = message
    },
    SET_TOGGLE: function(state) {
      state.toggle = !state.toggle;
    }
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
    getToggle: state => {
      return state.toggle;
    }
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
    toggle ({ commit }) {
      commit('SET_TOGGLE');
    }
  }

})

export default store;