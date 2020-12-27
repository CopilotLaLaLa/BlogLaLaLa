package com.spbo.blog.dao;

import com.spbo.blog.domain.Article;

import java.util.List;

public interface ArticleDao {
    int deleteByPrimaryKey(Integer idarticle);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer idarticle);

    List<Article> selectAllArticle();

    List<Article> selectPageArticle(Integer Page);

    int selectArticleNum();

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);

}