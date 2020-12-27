package com.spbo.blog.controller;

import com.spbo.blog.domain.*;
import com.spbo.blog.service.impl.ArticleServiceImpl;
import com.spbo.blog.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
public class ArticleController {

    @Autowired
    private ArticleServiceImpl articleService;

    @Autowired
    private CommentServiceImpl CommentService;

    @GetMapping("/article")
    public Article getArticle(@RequestParam("id") Integer id){
        return articleService.getArticle(id);
    }

    @GetMapping("/comment")
    public CommentMain getCommentJson(@RequestParam("id") Integer id, @RequestParam("Page") Integer Page){
        return CommentService.getCommentJson(id,Page);
    }

    @GetMapping("/commentTest")
    public List<CommentWithBLOBs> getComment(@RequestParam("id") Integer id){
        return CommentService.getComment(id);
    }

    @GetMapping("/commentNum")
    public Integer getCommentNum(@RequestParam("id") Integer id){
        return CommentService.getCommentNum(id);
    }

    @PostMapping("/updateComment")
    public boolean insert(@RequestBody UpdataComment record){
        return CommentService.insertSelective(record);
    }

    @GetMapping("/deleteComment")
    public boolean deleteSelective(@RequestParam("id") Integer id,@RequestParam("isChild") Integer isChild){
        return CommentService.deleteSelective(id,isChild);
    }

    @GetMapping("/allArticle")
    public List<Article> getAllArticle(){ return articleService.getAllArticle();}

    @GetMapping("/manageArticle")
    public ArticleManage manageArticle(@RequestParam("Page") Integer Page){
        return articleService.getArticleManage(Page);
    }

    @GetMapping("/deleteArticle")
    public boolean deleteArticle(@RequestParam("id") Integer id){
        return articleService.deleteArticle(id);
    }
}
