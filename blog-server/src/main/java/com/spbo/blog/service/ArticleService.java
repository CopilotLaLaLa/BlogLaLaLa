package com.spbo.blog.service;

import com.spbo.blog.domain.Article;
import com.spbo.blog.domain.ArticleManage;

import java.util.List;

public interface ArticleService {

    Article getArticle(Integer id);

    List<Article> getAllArticle();

    ArticleManage getArticleManage(Integer Page);

    boolean deleteArticle(Integer id);
}
