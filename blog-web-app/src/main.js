import Vue from 'vue'
import App from './App.vue'
import router from './router';
import api from './api/index.js';
import { library } from '@fortawesome/fontawesome-svg-core';
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import { faUser } from '@fortawesome/free-solid-svg-icons';
import { fas } from '@fortawesome/free-solid-svg-icons'
Vue.component('font-awesome-icon', FontAwesomeIcon);
library.add(
    faUser,fas
);
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
