package com.spbo.blog.domain;

import java.io.Serializable;
import java.util.List;

public class ArticleManage implements Serializable {
    private List<Article> articleList;

    private Integer articleAllNum;

    private Integer articlePageNum;

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    public Integer getArticleAllNum() {
        return articleAllNum;
    }

    public void setArticleAllNum(Integer articleAllNum) {
        this.articleAllNum = articleAllNum;
    }

    public Integer getArticlePageNum() {
        return articlePageNum;
    }

    public void setArticlePageNum(Integer articlePageNum) {
        this.articlePageNum = articlePageNum;
    }
}
