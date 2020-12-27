<template>
    <div>
        <div class="headertop">
            <div style="color: white" class="head-down" @click="headerTop_down"><i class="fa fa-chevron-down fa-3x" aria-hidden="true"></i></div>
            <div id="banner_wave_1" ></div>
            <div id="banner_wave_2" ></div>
        </div>
        <div id="page">
            <header class="site-header sticky" id="her">
                <nav>
                    <a href="/" class="logo">副驾驶啦啦啦~</a>
                    <ul class="mna">
                        <li> <router-link class="tag" :to="{ name: 'index'}">首页</router-link></li>
                        <li> <router-link class="tag" :to="{ name: 'index'}">首页</router-link></li>
                        <li> <router-link class="tag" :to="{ name: 'index'}">首页</router-link></li>
                        <li> <router-link class="tag" :to="{ name: 'index'}">首页</router-link></li>
                    </ul>
                </nav>
                <div v-if="User.userroot" class="lous" style="margin-top: -35px" :to="{ name: 'Manage'}">
                    <img class="avatar-s" style="margin-top:0;width: 32px;height: 32px;" :src="User.userhig" alt="">
                    <div class="header-user-menu">
                        <div class="header-user-name">
                            登陆的用户：
                            <div class="header-user-name-u">
                                {{User.username}}
                            </div>
                        </div>
                        <div class="user-menu-option">
                            <router-link :to="{ name: 'Manage'}">管理文章</router-link>
                            <a @click="this.userLoginOut" href="JavaScript:;">退出登陆</a>
                        </div>
                    </div>
                </div>
                <div class="lous" style="margin-top: -35px" v-else-if="User.iduser" href="javascript:;">
                    <img class="avatar-s" style="margin-top:0;width: 32px;height: 32px;" :src="User.userhig" alt="">
                    <div class="header-user-menu">
                        <div class="header-user-name">
                            登陆的用户：
                            <div class="header-user-name-u">
                                {{User.username}}
                            </div>
                        </div>
                        <div class="user-menu-option">
                            <a @click="this.userLoginOut" href="JavaScript:;">退出登陆</a>
                        </div>
                    </div>
                </div>
                <router-link v-else class="lous" :to="{ name: 'Login'}">
                    <i class="fa fa-user-circle-o fa-2x" aria-hidden="true" ></i>
                </router-link>
            </header>
            <div id="content" class="site-content">
                <div class="main">
                    <main>
                        <ul>
                            <li v-for="(article,i) in articleList" :key="i">
                                <router-link class="link" :to="{ name: 'Article',params: { id: article.idarticle }}">
                                    {{article.acname}}
                                    <time>{{article.actime}}</time>
                                </router-link>
                            </li>
                        </ul>
                    </main>
                </div>
            </div>
        </div>
    </div>
</template>


<script>
    import "../assets/js/amia.js"
    export default {
        name: "index",
        components: {
        },
        created(){
            this.getLoginMsg();
            this.getArticle();
        },
        data(){
            return{
                User:{
                    userroot:false,
                    iduser:null,
                    username:null,
                    userhig:null,
                },
                articleList:[],
            }
        },
        mounted () {
            window.addEventListener('scroll', this.scrollHander)
        },
        methods: {
            getLoginMsg(){
                this.$api.getLogin({
                }).then(res => {
                    this.User=res;
                }).catch(e => {
                    console.log(e)
                })
            },
            userLoginOut(){
                this.$api.loginOut({
                }).then(res => {
                    if(res){
                        this.User.userroot=false;
                        this.User.iduser=null;
                        this.User.username=null;
                        this.User.userhig=null;
                        alert("登出成功")
                    }
                }).catch(e => {
                    console.log(e)
                })
            },
            getArticle() {
                this.$api.getAllArticle({
                }).then(res => {
                    this.articleList=res;
                }).catch(e => {
                    console.log(e)
                })
            },
            headerTop_down() {
                let top = this.$el.querySelector('#content');
                $('html,body').animate({
                    scrollTop: top.offsetTop
                }, 600);
            },
            scrollHander() {
                    var header=document.querySelector("header");
                    header.classList.toggle("sticky",window.scrollY > 0);
                }
            },
        destroyed () {
            window.removeEventListener('scroll', this.scrollHander)
        }
        }
</script>

<style src="../assets/css/mcs.css" scoped>
</style>
<style scoped>
    @import "../assets/css/font-awesome/css/font-awesome.min.css";
</style>
