import request from '../utils/request';

const api = {
    getArticle(query) {
        return request({
            url: '/article',
            method: 'get',
            params: query
        });
    },
    getComments(query) {
        return request({
            url: '/comment',
            method: 'get',
            params: query
        });
    },
    updateComment(data) {
        return request({
            url: '/updateComment',
            method: 'post',
            data: data
        });
    },
    deleteComment(query) {
        return request({
            url: '/deleteComment',
            method: 'get',
            params: query
        });
    },
    Register(data) {
        return request({
            url: '/sign',
            method: 'post',
            data: data
        });
    },
    Login(query) {
        return request({
            url: '/sLogin',
            method: 'get',
            params: query
        });
    },
    getLogin(query) {
        return request({
            url: '/info',
            method: 'get',
            params: query
        });
    },
    loginOut(query) {
        return request({
            url: '/logout',
            method: 'get',
            params: query
        });
    },
    getAllArticle(query) {
        return request({
            url: '/allArticle',
            method: 'get',
            params: query
        });
    },
    manageArticle(query) {
        return request({
            url: '/manageArticle',
            method: 'get',
            params: query
        });
    },
    deleteArticle(query) {
        return request({
            url: '/deleteArticle',
            method: 'get',
            params: query
        });
    },
    upDataUser(data) {
        return request({
            url: '/upInfo',
            method: 'post',
            data: data
        });
    },
    changePsd(query) {
        return request({
            url: '/password',
            method: 'get',
            params: query
        });
    },
    filePost(data){
        return request({
            url: '/file',
            method: 'post',
            data: data
        });
    }
};


export default api;