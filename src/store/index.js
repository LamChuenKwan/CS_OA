import Vue from 'vue'
import Vuex from 'vuex'

import {publicStore} from './public/publicStore'

Vue.use(Vuex)

export default new Vuex.Store({
    modules:{
        publicStore :publicStore
    }
})