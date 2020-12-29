<template>
    <div>
        <div class="headertop-manage"></div>
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
                            <router-link :to="{ name: 'userEdit'}">修改信息</router-link>
                            <a @click="userLoginOut" href="JavaScript:;">退出登陆</a>
                        </div>
                    </div>
                </div>
            </header>
            <div v-if="User.userroot" class="table">
                <table cellspacing="0px">
                    <thead class="erc-table">
                    <tr>
                        <th><span class="ft">文章名</span></th>
                        <th><span class="ft">时间</span></th>
                        <th><span class="ft">文章标签</span></th>
                        <th><span class="ft">操作</span></th>
                    </tr>
                    </thead>
                    <tbody class="erc-table-body">
                    <tr v-for="(article,i) in manageArticle.articleList" :key="i">
                        <td>{{article.acname}}</td>
                        <td>{{article.actime}}</td>
                        <td>{{article.actag}}</td>
                        <td>
                            <a  class="btn btn-info"  href="javascript:;" >编辑</a>
                            <a class="btn btn-danger"  href="javascript:;" @click="deleteArticle(article.idarticle)" >删除</a>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <div v-if="allPage!=0" class="pg">
                    <a v-if="(page-1)>0" @click="pageAdd(0,1)" href="javascript:;" class="prev">上一页</a>
                    <a v-if="(page-2)>0" @click="pageAdd(0,2)" href="javascript:;" class="tcd" >{{page-2}}</a>
                    <a v-if="(page-1)>0" @click="pageAdd(0,1)" href="javascript:;" class="tcd" >{{page-1}}</a>
                    <span class="current">{{page}}</span>
                    <a v-if="(page+1)<=allPage" @click="pageAdd(1,0)" href="javascript:;" class="tcd" >{{page+1}}</a>
                    <a v-if="(page+2)<=allPage" @click="pageAdd(2,0)" href="javascript:;" class="tcd" >{{page+2}}</a>
                    <span v-if="(page+3)<allPage" class="dian">...</span>
                    <a v-if="(page+3)<allPage" @click="pageJumpMax" href="javascript:;" class="tcd">{{allPage}}</a>
                    <a v-if="(page+1)<=allPage" @click="pageAdd(1,0)" href="javascript:;" class="prev" >下一页</a>
                    <div class="page-jump">
                        共
                        <span>{{allPage}}</span>
                        页，跳至
                        <input id="jump" @keyup.enter="pageJump" class="page-input" type="text"/>
                        页
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Manage",
        created(){
            if (this.$sta.isLogin){
                this.getLoginMsg();
                this.getArticle();
            }else{
                this.$router.push({path:'/index'});
            }
        },
        watch:{
            manageArticle(){
                this.allPage= Math.ceil(this.manageArticle.articleAllNum/this.pageMax);
            },
            page(){
                this.getArticle();
            },
            User(){
                if (!this.User.userroot)this.$router.push({path:'/index'});
            }
        },
        data(){
            return {
                User:{
                    userroot: false,
                    iduser:null,
                    username:null,
                    userhig:null,
                },
                page:1,
                allPage:0,
                pageMax:6,
                manageArticle:{
                    articleList:[],
                    articleAllNum: 0,
                    articlePageNum: 0,
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
                        this.$router.push({path:'/index'});
                    }
                }).catch(e => {
                    console.log(e)
                })
            },
            getArticle() {
                this.$api.manageArticle({
                    Page:this.page,
                }).then(res => {
                    this.manageArticle=res;
                }).catch(e => {
                    console.log(e)
                })
            },
            pageJump(){
                let changePage = document.getElementById("jump").value;
                if (changePage<=this.allPage && changePage >0){
                    this.page=parseInt(changePage);
                    document.getElementById("jump").value='';
                }
                else {
                    document.getElementById("jump").value='';
                    alert("请输入正确的值");
                }
            },
            pageAdd(add,less){
                this.page = this.page+ add;
                this.page= this.page- less;
            },
            pageJumpMax(){
                this.page=this.allPage;
            },
            deleteArticle(Aid){
                if(confirm('是否删除？？！')){
                    this.$api.deleteArticle({
                        id:Aid,
                    }).then(res => {
                        if(res){
                            if(this.manageArticle.articlePageNum == 1 && this.page != 1){
                                this.page -= 1;
                                alert("删除成功");
                            }else {
                                this.getArticle();
                                alert("删除成功");
                            }
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