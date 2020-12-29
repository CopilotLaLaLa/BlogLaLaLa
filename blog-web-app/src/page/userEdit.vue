<template>
    <div class="fullPage">
        <header class="site-header sticky" id="her">
            <nav>
                <a href="/" class="logo">副驾驶啦啦啦~</a>
                <ul class="mna" style="margin-right: 40%;">
                    <li> <router-link class="tag" :to="{ name: 'index'}">首页</router-link></li>
                    <li> <router-link class="tag" :to="{ name: 'index'}">首页</router-link></li>
                    <li> <router-link class="tag" :to="{ name: 'index'}">首页</router-link></li>
                    <li> <router-link class="tag" :to="{ name: 'changePassWord'}">修改密码</router-link></li>
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
                        <a @click="userLoginOut" href="JavaScript:;">退出登陆</a>
                    </div>
                </div>
            </div>
        </header>
        <div class="security_content">
            <div class="security-title">
                <span class="security-title-icon"></span>
                <span class="security-title-text">我的信息</span>
            </div>
            <div class="user-setting-warp">
                <div class="security-right">
                    <div class="el-form-item__content">
                        <div class="el-input">
                            <span class="spTag">账号</span>
                            <span class="userinfo-username">{{User.iduser}}</span>
                        </div>
                    </div>
                    <div class="el-form-item__content">
                        <div class="el-input">
                            <span class="spTag">昵称</span>
                            <input v-model="User.username" autocomplete="off" placeholder="你的昵称" type="text" maxlength="10" class="el-input__inner" required>
                        </div>
                    </div>
                    <div class="el-form-item__content">
                        <div class="el-input">
                            <span class="spTag">邮箱</span>
                            <input v-model="User.useract" autocomplete="off" placeholder="你的邮箱" type="text" maxlength="10" class="el-input__inner" required>
                        </div>
                    </div>
                    <div class="padding-dom"></div>
                    <button @click="UserMsgUpData" type="button" class="el-button">
                        <span>保存</span>
                    </button>
                </div>
                <div class="security-left">
                    <div class="el-avatar">
                        <div class="avatar-div">
                            <img class="avatar-s avatar-m" :src="User.userhig" alt="">
                        </div>
                        <div class="padding-dom padding-avatar"></div>
                        <span  class="spanButton"  style="margin-left: 8px;">
                            <input type="file" accept="image/*" @change="update" class="fileUpLoad" title="">
                            <span class="titleButton">更换头像</span>
                        </span>
                    </div>
                </div>
            </div>
        </div>

    </div>
</template>

<script>
    import axios from "axios"
    export default {
        name: "userEdit",
        created(){
            if (this.$sta.isLogin){
                this.getLoginMsg();
            }else{
                this.$router.push({path:'/index'});
            }
        },
        data(){
            return{
                User:{
                    userroot: false,
                    iduser:null,
                    username:null,
                    userhig:null,
                    useract:null,
                },
            }
        },
        methods:{
            update (e) {
                var self = this;
                let file = e.target.files[0];
                let param = new FormData() ;
                if(file){
                    param.append('file', file, file.name);
                    param.append('id', this.User.iduser); // 添加form表单中其他数据
                    console.log(param.get('file')) // FormData私有类对象，访问不到，可以通过get判断值是否传进去
                    let config = {
                        headers: {'Content-Type': 'multipart/form-data'}
                    }
                    axios.post('http://localhost:8081/file', param, config)
                        .then(response => {
                            this.getLoginMsg();
                        })
                }
            },
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
                        this.$sta.isLogin=false;
                        this.$router.push({path:'/index'});
                    }
                }).catch(e => {
                    console.log(e)
                })
            },
            UserMsgUpData(){
                if (!this.User.username.trim()) {
                    this.User.username=null;
                    alert("不能使用空的昵称");
                }else if(!this.User.useract.trim()){
                    this.User.useract=null;
                    alert("不能使用空的邮箱");
                }else {
                    this.$api.upDataUser({
                        username:this.User.username,
                        useract:this.User.useract,
                    }).then(res => {
                        if(res){
                            this.getLoginMsg();
                            alert("修改成功");
                        }
                    }).catch(e => {
                        console.log(e)
                    })
                }
            },
            toggleShow() {
                var show = this.show;
                this.show = !show;
            }
        },
    }
</script>

<style src="../assets/css/acs.css" scoped>
</style>
<style scoped>
    .fullPage{
        position:absolute;
        top:0;
        bottom:0;
        left:0;
        right:0;
        display: flex;
        align-items: center;
        justify-content: center;
        background: url("../assets/image/msgbg.jpg");
        background-size: cover;
        background-repeat: no-repeat;
        background-position:center center;
    }
    .security_content{
        overflow: hidden;
        width: 600px;
        margin: 92px auto 100px;
        border: 1px solid #e1e2e5;
        box-shadow: 0 2px 4px rgba(0,0,0,.14);
        background: rgba(255,255,255,.7);
        border-radius: 7px;
    }
    .security-right {
        float: right;
        width: 400px;
        height: 231px;
        border-left: 1px solid #ddd;
        background: rgba(255,255,255,.9);
        padding-top: 15px;
        min-height: 185px!important;
    }
    .security-title {
        height: 50px;
        padding-left: 30px;
        border-bottom: 1px solid #ddd;
        background-color: #fff;
    }
    .security-title-icon{
        float: left;
        width: 4px;
        height: 16px;
        margin-top: 18px;
        background-color: #fb7299!important;
        border-radius: 4px;
    }
    .security-title-text {
        float: left;
        margin: 15px 0 0 5px;
        color: #fb7299!important;
        font-size: 14px;
        cursor: default;
    }
    .user-setting-warp{
        padding: 0px 20px 0;
        position: relative;
    }
    .el-form-item__content {
        position: relative;
        height: 30px;
        line-height: 30px;
        margin-bottom: 10px;
        font-size: 14px;
    }
    .el-input__inner{
        float: left;
        width: 225px;
        height: 30px;
        margin-right: 40px;
        -webkit-appearance: none;
        -moz-appearance: none;
        appearance: none;
        background-color: rgba(255,255,255,.8);
        background-image: none;
        border-radius: 4px;
        border: 1px solid #bfcbd9;
        box-sizing: border-box;
        color: #1f2d3d;
        font-size: inherit;
        line-height: 1;
        outline: 0;
        padding: 3px 10px;
        transition: border-color .2s cubic-bezier(.645,.045,.355,1);
    }
    .el-input__inner:hover {
        border-color: #8391a5;
    }
    .el-input__inner:focus {
        outline: 0;
        border-color: #20a0ff;
    }
    .spTag{
        float: left;
        padding-left: 30px;
        padding-right: 30px;
    }
    .userinfo-username {
        font-size: 14px;
        color: #898989;
    }
    .padding-dom {
        width: 300px;
        height: 20px;
        border-bottom: 1px solid #e5e9ef;
        margin-bottom: 40px;
        margin-left: 30px;
    }
    .el-button  {
        width: 110px;
        position: relative;
        top: 50%;
        left: 45%;
        -webkit-transform: translate(-50%, -50%);
        transform: translate(-50%, -50%);
        background: #fb7299!important;
        display: inline-block;
        line-height: 1;
        white-space: nowrap;
        cursor: pointer;
        border: 1px solid #c4c4c4;
        color: #fff;
        margin: 0;
        padding: 10px 15px;
        border-radius: 4px;
    }
    .el-button:hover {
        background-color: #fb7299!important;
        border-color: #fb7299!important;
        color: #fff;
    }
    .security-left {
        float: left;
        width: 158px;
        height: 100%;
        overflow: hidden;
        background: rgba(255,255,255,.9);
        padding-top: 15px;
        min-height: 231px!important;
    }
    .avatar-div{
        height: 72px;
        margin-top: 20px;
    }
    .avatar-m {
        margin-left: 47px;
        width: 64px;
        height: 64px;
    }
    .padding-avatar{
        position: relative;
        margin-top: 27px;
        margin-left: 20px;
        width: 120px;
    }
    .fileUpLoad{
        width: 110px;
        height: 35px;
        opacity: 0;
        cursor: pointer;
        z-index: 10;
    }
    .spanButton {
        position: relative;
        top: 50%;
        left: 45%;
        transform: translate(-50%, -50%);
        width: 110px;
        height: 35px;
        border: 1px solid #c4c4c4;
        color: #fff;
        background: #fb7299!important;
        display: inline-block;
        line-height: 1;
        border-radius: 4px;
    }
    .titleButton{
        position: absolute;
        top: 9px;
        left: 23px;
        font-size: 15px;
        pointer-events:none;
        cursor: pointer;
    }
</style>