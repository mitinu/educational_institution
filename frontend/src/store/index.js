import { createStore } from 'vuex'

export default createStore({
  state: {
    zIndexMax: 10
  },
  getters: {
    getZIndexMax: (state) => state.zIndexMax
  },
  mutations: {
    INCREMENT_Z_INDEX: (state) => {
      state.zIndexMax++
    }
  }
})
