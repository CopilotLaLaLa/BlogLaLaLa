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
let sta={
  firstLogin:false,
  isLogin:false,
}
Vue.prototype.$sta = sta;
Vue.prototype.$api = api;

Vue.config.productionTip = false
router.beforeEach((to, from, next) => {
  if (!Vue.prototype.$sta.firstLogin){
    Vue.prototype.$api.getLogin({
    }).then(res => {
      Vue.prototype.$sta.firstLogin=true;
      Vue.prototype.$sta.isLogin=true;
    }).catch(e => {
      Vue.prototype.$sta.firstLogin = true;
      Vue.prototype.$sta.isLogin=false;
    })
  }

  if(Vue.prototype.$sta.isLogin && to.path === '/Login' || to.path === '/login'){
    next('/');
  }

  document.body.scrollTop = 0
  document.documentElement.scrollTop = 0
  window.pageYOffset = 0
  next();
})


new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
