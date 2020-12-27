<template>
    <div>
        <div class="headertop-article">
        </div>
        <div id="page">
            <BlogHead></BlogHead>
            <div id="content" class="site-a-content">
                <div class="main">
                    <main>
                        <div class="article">
                            <time>{{Article.time}}</time>
                            <h1>{{Article.title}}</h1>
                            <div v-html="Article.text"></div>
                            <p style="padding-bottom:15px;">
                                END
                            </p>
                        </div>
                        <Comment></Comment>
                    </main>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import Comment from "@/components/Comment";
    import BlogHead from "@/components/BlogHead";

    export default {
        name: "Article",
        components: {
            Comment,
            BlogHead,
        },
        created() {
            this.$api.getArticle({
                id: this.$route.params.id,
            }).then(res => {
                this.Article.text=res.actext;
                this.Article.title=res.acname;
                this.Article.time=res.actime;
            }).catch(e => {
                console.log(e)
            })
        },
        data() {
            return {
                Article: {
                    title: '',
                    time: '',
                    text: '',
                },
            }
        },
        methods: {

        },
    }
</script>

<style src="../assets/css/acs.css" scoped>
</style>
<style scoped>
    @import "../assets/css/font-awesome/css/font-awesome.min.css";
</style>