import Vue from 'vue'
import App from './App.vue'
import Axios from 'axios'
import router from './router';
import api from './api/index.js';
Vue.prototype.$api = api;
Vue.config.productionTip = false
router.beforeEach((to, from, next) => {
  // chrome
  document.body.scrollTop = 0
  // firefox
  document.documentElement.scrollTop = 0
  // safari
  window.pageYOffset = 0
  next();
})
new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
