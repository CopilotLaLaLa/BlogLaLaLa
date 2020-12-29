import Vue from 'vue';
import Router from 'vue-router';

const originalReplace = Router.prototype.replace;
Router.prototype.replace = function replace(location) {
    return originalReplace.call(this, location).catch(err => err);
};
const originalPush = Router.prototype.push
Router.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
};

Vue.use(Router);



export default new Router({
    routes: [
        {
            path: '/',
            redirect: '/index'
        },
        {
            path: '/index',
            name: 'index',
            component: () => import('../page/index.vue'),
            meta: { title: '副驾驶啦啦啦' }
        },
        {
            path: '/Article:id',
            name: 'Article',
            component: () => import('../page/Article.vue'),
            meta: { title: '我的文章' }
        },
        {
            path: '/Login',
            name: 'Login',
            component: () => import('../page/Login.vue'),
            meta: { title: '登陆' }
        },
        {
            path: '/Manage',
            name: 'Manage',
            component: () => import('../page/Manage.vue'),
            meta: { title: '管理' }
        },
        {
            path: '/userEdit',
            name: 'userEdit',
            component: () => import('../page/userEdit'),
            meta: { title: '个人中心' }
        },
        {
            path: '/changePassWord',
            name: 'changePassWord',
            component: () => import('../page/changePassWord'),
            meta: { title: '修改密码' }
        },
        {
            path: '*',
            redirect: '/'
        }
    ],
});
