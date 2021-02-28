import Vue from 'vue'
import Vuex from 'vuex'
import users from './modules/users'
import jusers from './modules/jusers'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    users,jusers
  }
})
