<template>
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
                    <router-link :to="{ name: 'userEdit'}">修改信息</router-link>
                    <router-link :to="{ name: 'Manage'}">管理文章</router-link>
                    <a @click="userLoginOut" href="JavaScript:;">退出登陆</a>
                </div>
            </div>
        </div>
        <div class="lous" style="margin-top: -35px" v-else-if="User.username" href="javascript:;">
            <img class="avatar-s" style="margin-top:0;width: 32px;height: 32px;" :src="User.userhig" alt="">
            <div class="header-user-menu">
                <div class="header-user-name">
                    登陆的用户：
                    <div class="header-user-name-u">
                        {{User.username}}
                    </div>
                </div>
                <div class="user-menu-option">
                    <router-link :to="{ name: 'userEdit'}">修改信息</router-link>
                    <a @click="userLoginOut" href="JavaScript:;">退出登陆</a>
                </div>
            </div>
        </div>
        <router-link v-else class="lous" :to="{ name: 'Login'}">
            <font-awesome-icon :icon="['fas','user-circle']" size="2x"></font-awesome-icon>
<!--            <i class="fa fa-user-circle-o fa-2x" aria-hidden="true" ></i>-->
        </router-link>
    </header>
</template>

<script>
    export default {
        name: "BlogHead",
        created(){
            if (this.$sta.isLogin){
                this.getLoginMsg();
            }
        },
        data(){
            return{
                User: {
                    userroot:false,
                    iduser:null,
                    username:null,
                    userhig:null,
                },
            }
        },
        methods:{
            getLoginMsg(){
                this.$api.getLogin({
                }).then(res => {
                    this.User=res;
                }).catch(e => {
                    this.User.userroot=false;
                    this.User.iduser=null;
                    this.User.username=null;
                    this.User.userhig=null;
                    console.log(e)
                })
            },
            userLoginOut(){
                this.$api.loginOut({
                }).then(res => {
                    if(res){
                        this.$sta.isLogin=false;
                        alert("登出成功")
                        this.$router.go(0);
                    }
                }).catch(e => {
                    console.log(e)
                })
            },
        },
    }
</script>

<style scoped>
    .header-user-menu {
        position: absolute;
        width: 110px;
        background-color:#fff;
        top: 70px;
        display: none;
        overflow: hidden;
        animation: header-user-menu .4s;
        box-shadow: 0 1px 40px -8px rgba(0,0,0,.5);
        border-radius: 5px;
        text-align: center;
    }
    .header-user-menu:before {
        content: "";
        position: fixed;
        top: 50px;
        right: 105px;
        margin-left: -10px;
        border-width: 10px;
        border-style: solid;
        border-color: transparent transparent #fff;
    }
    .lous:hover .header-user-menu {
        display: block;
    }
    .header-user-name {
        font-size: 12px;
        background: #fff;
        padding: 10px;
        border-radius: 4px 4px 0 0;
    }
    .header-user-name-u {
        font-weight: 700;
        font-size: 14px;
        overflow: hidden;
        text-overflow: ellipsis;
        -o-text-overflow: ellipsis;
        white-space: nowrap;
    }
    .header-user-menu .user-menu-option {
        padding: 5px 0;
        background: #fff;
        border-radius: 0 0 4px 4px;
        width: 130px;
    }
    .header-user-menu a {
        display: block;
        padding: 6px 10px;
        margin: 2px 0;
        width: 110px;
        color: #333;
        font-size: 13px;
        text-align: center;
        outline: none;
        transition: color .2s ease-out,border .2s ease-out,opacity .2s ease-out;
    }

</style>