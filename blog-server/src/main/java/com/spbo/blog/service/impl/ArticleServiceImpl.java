package com.spbo.blog.service.impl;

import com.spbo.blog.dao.CommentDao;
import com.spbo.blog.dao.ArticleDao;
import com.spbo.blog.domain.Article;
import com.spbo.blog.domain.ArticleManage;
import com.spbo.blog.service.ArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    ArticleDao articleDao;

    @Resource
    CommentDao commentDao;

    @Override
    public Article getArticle(Integer id) {
        return articleDao.selectByPrimaryKey(id);
    }

    @Override
    public List<Article> getAllArticle(){
        return articleDao.selectAllArticle();
    }

    @Override
    public ArticleManage getArticleManage(Integer Page){
        ArticleManage ArResult = new ArticleManage();
        ArResult.setArticleAllNum(articleDao.selectArticleNum());
        List<Article> ArticleList = articleDao.selectPageArticle((Page-1)*6);
        ArResult.setArticleList(ArticleList);
        ArResult.setArticlePageNum(ArticleList.size());
        return ArResult;
    }

    @Override
    public boolean deleteArticle(Integer id){
        try {
            articleDao.deleteByPrimaryKey(id);
            commentDao.deleteByCmatc(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
