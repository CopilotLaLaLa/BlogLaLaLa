<template>
    <div class="comments">
        <div class="comments-main">
            <h3>
                Comments |
                <span class="noc">{{CommentsNumber}}条评论</span>
            </h3>
            <div v-if="!User.iduser" class="comment-send no-login">
                <img class="avatar-s" src="../assets/image/defuser.png" alt="">
                <div class="textarea-container">
                    <div class="baffle-wrap">
                        <div class="baffle">请先
                            <router-link class="log-link" :to="{ name: 'Login'}">登陆</router-link>
                            后发表评论 (・ω・)
                        </div>
                    </div>
                    <textarea name="msg" id="tcm0" cols="80" rows="50" placeholder="发条友善的评论" class="ipt-txt"></textarea>
                    <button type="submit" class="comment-submit" disabled="disabled">发表评论</button>
                </div>
            </div>
            <div v-else class="comment-send">
                <img class="avatar-s" :src="User.userhig" alt="">
                <div class="textarea-container">
                    <textarea v-model="replyText" name="msg"  cols="80" rows="50" placeholder="发条友善的评论" class="ipt-txt"></textarea>
                    <button type="submit" class="comment-submit" @click="reply" :disabled="!canClick">发表评论</button>
                </div>
            </div>
        </div>
        <li v-for="(item,i) in items" :key="i" class="comment-body comment-parent">
            <div>
                <div class="comment-view">
                    <div class="comment-header">
                        <img class="avatar" :src="item.userHig" alt="">
                        <span class="comment-author">{{item.username}}</span>
                        <a v-if="User.iduser" class="comment-reply" @click="Select=item.cmb.idcomment" href="javascript:;">REPLY</a>
                    </div>
                    <div class="comment-content">
                        <p>
                            {{item.cmb.cmtext}}
                        </p>
                        <a v-if="User.userroot || item.cmb.cmuser==User.iduser" class="comment-delete" @click="deleteComments(item.cmb.idcomment,0)" href="javascript:;">DELETE</a>
                    </div>
                    <div class="comment-meta">
                        <time>{{item.cmb.cmtime}}</time>
                    </div>
                    <div v-if="Select == item.cmb.idcomment" class="comment-urp">
                        <img class="avatar-s" :src="User.userhig" alt="">
                        <div class="textarea-container">
                                    <textarea name="msg" v-model="childReplyText" cols="80" rows="50"  placeholder="发条友善的评论" class="ipt-txt">
                                    </textarea>
                            <button type="submit" class="comment-submit" :disabled="!canClick" @click="childReply(item.cmb.idcomment,null)">回复</button>
                        </div>
                    </div>
                </div>
                <ul v-if="item.childCm" class="comment-child">
                    <li v-for="(crp,i) in item.childCm" :key="i" >
                        <div class="comment-view">
                            <div class="comment-header">
                                <img class="avatar" :src="crp.userHig" alt="">
                                <span class="comment-author">{{crp.username}}</span>
                                <a v-if="User.iduser && crp.cmb.cmuser!=User.iduser" class="comment-reply" @click="Select=crp.cmb.idcomment" href="javascript:;">REPLY</a>
                            </div>
                            <div class="comment-content">
                                <span v-if="crp.cmb.cmrp" class="comment-author-at">
                                                    @{{crp.cmb.cmrp}}
                                                </span>
                                <p>
                                    {{crp.cmb.cmtext}}
                                </p>
                                <a v-if="User.userroot || crp.cmb.cmuser==User.iduser" class="comment-delete" @click="deleteComments(crp.cmb.idcomment,item.cmb.idcomment)" href="javascript:;">DELETE</a>
                            </div>
                            <div class="comment-meta">
                                <time>{{crp.cmb.cmtime}}</time>
                            </div>
                            <div v-if="Select==crp.cmb.idcomment" class="comment-urp">
                                <img class="avatar-s" :src="User.userhig" alt="">
                                <div class="textarea-container">
                                    <textarea name="msg" v-model="childReplyText" cols="80" rows="50" placeholder="发条友善的评论" class="ipt-txt">
                                    </textarea>
                                    <button type="submit" class="comment-submit" :disabled="!canClick" @click="childReply(item.cmb.idcomment,crp.username)">回复</button>
                                </div>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </li>
        <div v-if="!User.iduser && count>4" class="comment-send no-login">
            <img class="avatar-s" src="../assets/image/defuser.png" alt="">
            <div class="textarea-container">
                <div class="baffle-wrap">
                    <div class="baffle">请先
                        <router-link class="log-link" :to="{ name: 'Login'}">登陆</router-link>
                        后发表评论 (・ω・)
                    </div>
                </div>
                <textarea  name="msg" id="tcm1" cols="80" rows="50" placeholder="发条友善的评论" class="ipt-txt"></textarea>
                <button type="submit" class="comment-submit" disabled="disabled">发表评论</button>
            </div>
        </div>
        <div v-if="User.iduser && count>4" class="comment-send">
            <img class="avatar-s" :src="User.userhig" alt="">
            <div class="textarea-container">
                <textarea v-model="replyText" name="msg" cols="80" rows="50" placeholder="发条友善的评论" class="ipt-txt"></textarea>
                <button type="submit" class="comment-submit" @click="reply" :disabled="!canClick">发表评论</button>
            </div>
        </div>
        <div v-if="allPage!=0">
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
</template>

<script>
    export default {
        name: "Comment",
        created() {
            this.getLoginMsg();
            this.getCom();
        },
        watch:{
            CommentsMain(){
                this.allPage= Math.ceil(this.CommentsMain/this.pageMax);
            },
            page(){
                this.getCom();
                this.counter();
            }
        },
        data(){
            return{
                CommentsNumber: 0,
                CommentsMain: 0,
                Select:0,
                replyText:null,
                childReplyText:null,
                page:1,
                pageMax:6,
                pageNow:0,
                allPage:0,
                count:0,
                canClick:true,
                User:{
                    userroot: false,
                    iduser:null,
                    username:null,
                    userhig:null,
                },
                items: []
            }
        },
        methods: {
            counter(){
                this.count = 0;
                for (let i=0; i<this.items.length; i++) {
                    this.count +=1;
                    if(this.items[i].childCm && this.items[i].childCm.length > 0){
                        this.count += this.items[i].childCm.length;
                    }
                }
            },
            getCom(){
                this.$api.getComments({
                    id: this.$route.params.id,
                    Page: this.page,
                }).then(res => {
                    this.items=res.commentText;
                    this.CommentsNumber=res.commentsNumber;
                    this.pageNow=res.commentsMain;
                    this.CommentsMain=res.commentsAllMain;
                    this.counter();
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
            nowTime(){
                var now = new Date();
                var year = now.getFullYear();
                var month = now.getMonth();
                var date = now.getDate();
                var hour = now.getHours();
                var min = now.getMinutes();
                var sec = now.getSeconds();
                let time = "";
                month = month + 1;
                if (month < 10) month = "0" + month;
                if (date < 10) date = "0" + date;
                if (hour < 10) hour = "0" + hour;
                if (min < 10) min = "0" + min;
                if (sec < 10) sec = "0" + sec;
                time = year + "/" + month + "/" + date + "/" + " " + hour + ":" + min + ":" + sec;
                return time;
            },
            reply(){
                if(this.replyText){
                    if (!this.replyText.trim()) {
                        alert("请勿输入空信息")
                        this.replyText=null;
                    }
                    else {
                        this.canClick=false;
                        this.$api.updateComment({
                            childMsg: {
                                cmuser: this.User.iduser,
                                cmatc: this.$route.params.id,
                                cmtext: this.replyText,
                                cmtime: this.nowTime(),
                                cmischild: false,
                                cmrp: null
                            },
                            idcomment:0,
                        }).then(res => {
                            if (res){
                                this.replyText=null;
                                if(this.pageNow==this.pageMax) {
                                    if (this.page==this.allPage){
                                        this.page += 1;
                                    }else {
                                        this.page=this.allPage;
                                    }
                                }
                                else {
                                    this.getCom();
                                    alert("评论成功");
                                    this.Select=0;
                                }
                                this.canClick=true;
                            }
                        }).catch(e => {
                            alert("追加评论失败");
                            this.canClick=true;
                            console.log(e)
                        })

                    }
                }else {
                    alert("请输入评论");
                }
            },
            childReply(parentId,rename){
                if(this.childReplyText){
                    if (!this.childReplyText.trim()) {
                        this.childReplyText=null;
                        alert("请勿输入空信息");
                    }
                    else {
                        this.canClick=false;
                        this.$api.updateComment({
                            childMsg:{
                                cmuser: this.User.iduser,
                                cmatc: this.$route.params.id,
                                cmtext: this.childReplyText,
                                cmtime: this.nowTime(),
                                cmischild: true,
                                cmrp: rename
                            },
                            idcomment:parentId
                        }).then(res => {
                            if (res){
                                this.childReplyText=null;
                                this.getCom();
                                alert("追加评论成功");
                                this.Select=0;
                            }
                            this.canClick=true;
                        }).catch(e => {
                            alert("追加评论失败");
                            this.canClick=true;
                            console.log(e)
                        })
                    }
                }else {
                    alert("请输入信息");
                }
            },
            deleteComments(deleteId,isChild) {
                if(confirm('是否删除？？！')){
                    this.$api.deleteComment({
                        id:deleteId,
                        isChild:isChild
                    }).then(res => {
                        if (res){
                            if(isChild==0){
                                if(this.pageNow == 1 && this.page != 1){
                                    this.page -= 1;
                                    alert("删除成功");
                                }else {
                                    this.getCom();
                                    alert("删除成功");
                                }
                            }else {
                                this.getCom();
                                alert("删除成功");
                            }
                        }
                    }).catch(e => {
                        alert("删除失败");
                        console.log(e)
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
        },
    }
</script>

<style scoped>

</style>