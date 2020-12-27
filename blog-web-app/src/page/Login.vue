<template>
    <div class="bgd">
        <div class="form-box">
            <div class="button-box">
                <div id="btn"></div>
                <button type="button" class="toggle-btn" style="padding-left: 40px" @click="changeLogin">登 陆</button>
                <button type="button" class="toggle-btn" style="padding-left: 45px" @click="changeRegister">注 册</button>
            </div>
            <form id="login" class="input-group">
                <div class="item">
                    <input v-model="Usermsg.iduser" maxlength="10" oninput="value=value.replace(/[^\d]/g,'')" type="text" required>
                    <label>账号</label>
                </div>
                <div class="item">
                    <input v-model="Usermsg.userpsw" maxlength="10" oninput="value=value.replace(/[^\d]/g,'')" type="password" autocomplete="off" required>
                    <label>密码</label>
                </div>
                <input type="checkbox" @click = "checkbox($event)" class="check-box"><span>滴，我不是机器人</span>
                <button type="submit" @click="sendLogin" class="submit-btn" :disabled="!dis">登陆</button>
            </form>
            <form id="register" style="top: 90px;">
                <div class="item">
                    <input v-model="Usermsg.iduser" type="text" maxlength="10" class="input-field" oninput="value=value.replace(/[^\d]/g,'')" required>
                    <label>设置账户</label>
                </div>
                <div class="item">
                    <input v-model="Usermsg.userpsw" type="password" maxlength="10" autocomplete="off" oninput="value=value.replace(/[^\d]/g,'')" required>
                    <label>设置密码</label>
                </div>
                <div class="item">
                    <input v-model="Usermsg.repetuserpsw" type="password" maxlength="10" autocomplete="off" oninput = "value=value.replace(/[^\d]/g,'')" required>
                    <label>确认密码</label>
                </div>
                <div class="item" style="margin-bottom: 10px;">
                    <input v-model="Usermsg.useract" type="email" class="input-field" required>
                    <label>绑定邮箱</label>
                </div>
                <input type="checkbox" @click = "checkbox($event)" class="check-box" style="margin-bottom: 10px;"><span style="bottom: 46px;">我同意这些协议。</span>
                <button type="submit" @click="sendRegister" class="submit-btn" :disabled="!dis">注册</button>
            </form>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Login",
        data(){
            return {
                Usermsg:{
                    iduser:'',
                    useract:'',
                    userpsw:'',
                    repetuserpsw:'',
                },
                dis:false,
            }
        },
        methods: {
            checkbox(e){
                    this.dis = e.target.checked;
            },
            changeRegister() {
                var x =document.getElementById("login");
                var y =document.getElementById("register");
                var z =document.getElementById("btn");
                this.Usermsg.iduser=null;
                this.Usermsg.userpsw=null;
                this.dis=false;
                x.style.left = "-400px";
                y.style.left = "50px";
                z.style.left = "110px";
            },
            changeLogin() {
                var x =document.getElementById("login");
                var y =document.getElementById("register");
                var z =document.getElementById("btn");
                this.Usermsg.iduser=null;
                this.Usermsg.userpsw=null;
                this.Usermsg.repetuserpsw=null;
                this.Usermsg.useract=null;
                this.dis=false;
                x.style.left = "50px";
                y.style.left = "450px";
                z.style.left = "0";
            },
            sendRegister(){
                if(this.Usermsg.iduser != null)
                    this.Usermsg.iduser = this.Usermsg.iduser.trim();
                if(this.Usermsg.userpsw != null)
                    this.Usermsg.userpsw = this.Usermsg.userpsw.trim();
                if(this.Usermsg.repetuserpsw != null)
                    this.Usermsg.repetuserpsw = this.Usermsg.userpsw.trim();
                if(this.Usermsg.useract != null)
                    this.Usermsg.useract = this.Usermsg.useract.trim();
                if( this.Usermsg.iduser == "") {
                    alert("请填写账户信息");
                }else if(this.Usermsg.userpsw == "" ){
                    alert("请填写密码");
                }else if(this.Usermsg.useract == "" ){
                    alert("请填写邮箱");
                } else if(this.Usermsg.userpsw != this.Usermsg.repetuserpsw ){
                    alert("两次输入的密码不一致");
                    this.Usermsg.userpsw=null;
                    this.Usermsg.repetuserpsw=null;
                }else{
                    this.dis=false;
                    this.$api.Register({
                        iduser:this.Usermsg.iduser,
                        useract:this.Usermsg.useract,
                        userpsw:this.Usermsg.userpsw,
                    }).then(res => {
                        if (res){
                            alert("注册成功");
                            location.reload();
                        }else {
                            this.Usermsg.iduser=null;
                            this.Usermsg.userpsw=null;
                            this.Usermsg.repetuserpsw=null;
                            this.Usermsg.useract=null;
                            this.dis=true;
                            alert("账号重复");
                        }
                    }).catch(e => {
                        console.log(e)
                    })
                }
            },
            sendLogin(){
                this.dis=false;
                if(this.Usermsg.iduser != null)
                    this.Usermsg.iduser = this.Usermsg.iduser.trim();
                if(this.Usermsg.userpsw != null)
                    this.Usermsg.userpsw = this.Usermsg.userpsw.trim();
                if( this.Usermsg.iduser == "") {
                    alert("账号不能为空");
                }else if(this.Usermsg.userpsw == "" ){
                    alert("密码不能为空");
                }else {
                    console.log(this.Usermsg.iduser);
                    console.log(this.Usermsg.userpsw);
                    this.$api.Login({
                        ciduser:this.Usermsg.iduser,
                        cuserpsw:this.Usermsg.userpsw,
                    }).then(res => {
                        if (res){
                            this.$router.go(-1);
                        }else {
                            this.Usermsg.iduser=null;
                            this.Usermsg.userpsw=null;
                            this.dis=true;
                            alert("账号不存在或密码错误");
                        }
                    }).catch(e => {
                        console.log(e)
                    })
                }
            },
        }
    }
</script>

<style src="../assets/css/lss.css" scoped>
</style>