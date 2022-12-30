import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import api from "@/api";
// 导入element-UI
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
// 引入axios
import axios from 'axios'
import VueAxios from 'vue-axios'

// 使用axios
Vue.use(VueAxios, axios)
// 使用element-UI
Vue.use(ElementUI)
// 使用api
Vue.prototype.$api = api
// 关闭生产提示
Vue.config.productionTip = false
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
