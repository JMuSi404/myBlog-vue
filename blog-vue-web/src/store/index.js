import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    keyed:''
  },
  getters: {
    getKeyed(state){
       return state.keyed
    }
  },
  mutations: {
    setKeyed (state, str) {
      state.keyed = str
    }
  },
  actions: {
  },
  modules: {
  }
})
