package com.spbo.blog.dao;

import com.spbo.blog.domain.Comment;
import com.spbo.blog.domain.CommentWithBLOBs;

import java.util.List;

public interface CommentDao {
    int deleteByPrimaryKey(Integer idcomment);

    int insert(CommentWithBLOBs record);

    int insertSelective(CommentWithBLOBs record);

    CommentWithBLOBs selectByPrimaryKey(Integer idcomment);

    List<CommentWithBLOBs> selectByArticleKey(Integer Cmatc);

    List<CommentWithBLOBs> selectByArticlePageKey(Integer Cmatc,Integer Page);

    int updateByPrimaryKeySelective(CommentWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(CommentWithBLOBs record);

    int updateByPrimaryKey(Comment record);

    int updateByCmrp(String cmrpadd,Integer idcomment);

    int removeByCmrp(String cmrpremove,Integer idcomment);

    int selectCommentAll(Integer Cmatc);

    int selectCommentMain(Integer Cmatc);

    int deleteByCmatc(Integer id);
}