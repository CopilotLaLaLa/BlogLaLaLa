<template>
    <div class="fullPage">
        <header class="site-header sticky" id="her">
            <nav>
                <a href="/" class="logo">副驾驶啦啦啦~</a>
                <ul class="mna" style="margin-right: 40%;">
                    <li> <router-link class="tag" :to="{ name: 'index'}">首页</router-link></li>
                    <li> <router-link class="tag" :to="{ name: 'index'}">首页</router-link></li>
                    <li> <router-link class="tag" :to="{ name: 'index'}">首页</router-link></li>
                    <li> <router-link class="tag" :to="{ name: 'userEdit'}">修改资料</router-link></li>
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
                <span class="security-title-text">修改密码</span>
            </div>
            <div class="user-setting-warp">
                <div class="security-right">
                    <div class="el-form-item__content">
                        <div class="el-input">
                            <span class="spTag">旧的密码</span>
                            <input v-model="User.userpsw" autocomplete="off"  type="password" maxlength="10" class="el-input__inner" required>
                        </div>
                    </div>
                    <div class="el-form-item__content">
                        <div class="el-input">
                            <span class="spTag">新的密码</span>
                            <input v-model="userpswnew" autocomplete="off"  type="password" maxlength="10" class="el-input__inner" required>
                        </div>
                    </div>
                    <div class="el-form-item__content">
                        <div class="el-input">
                            <span class="spTag">确认密码</span>
                            <input v-model="userpswcheck" autocomplete="off"  type="password" maxlength="10" class="el-input__inner" required>
                        </div>
                    </div>
                    <div class="padding-dom"></div>
                    <button @click="changePsw" type="button" class="el-button">
                        <span>修改密码</span>
                    </button>
                </div>
                <div class="security-left">
                    <div class="el-avatar">
                        <div class="avatar-div">
                            <img class="avatar-s avatar-m" :src="User.userhig" alt="">
                        </div>
                        <div class="padding-dom padding-avatar"></div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</template>

<script>
    export default {
        name: "changePassWord",
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
                    userpsw:null,
                    userhig:null,
                },
                userpswnew:null,
                userpswcheck:null,
            }
        },
        methods:{
            getLoginMsg(){
                this.$api.getLogin({
                }).then(res => {
                    this.User=res;
                }).catch(e => {
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
            changePsw(){
                if ( this.User.userpsw == null || !this.User.userpsw.trim()) {
                    this.User.userpsw=null;
                    alert("请输入旧密码");
                }else if ( this.userpswnew == null || !this.userpswnew.trim()) {
                    this.userpswnew=null;
                    alert("请输入新密码");
                }else if ( this.userpswcheck == null || !this.userpswcheck.trim()) {
                    this.userpswcheck=null;
                    alert("请输入确认密码");
                } else if(this.userpswcheck.trim() != this.userpswnew.trim()){
                    this.userpswnew=null;
                    this.userpswcheck=null;
                    alert("两次输入的密码不一致");
                }else {
                    this.$api.changePsd({
                        oldPassword:this.User.userpsw,
                        newPassword:this.userpswnew,
                    }).then(res => {
                        if(res){
                            alert("修改成功");
                            this.$sta.isLogin=false;
                            this.$router.push({path:'/Login'});
                        }
                        else {
                            alert("旧密码不正确")
                        }
                    }).catch(e => {
                        console.log(e)
                    })
                }
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
        background: url("../assets/image/pswbg.jpg");
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
        width: 350px;
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
</style>