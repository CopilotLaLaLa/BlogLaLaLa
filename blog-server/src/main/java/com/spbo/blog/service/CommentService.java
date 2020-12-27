package com.spbo.blog.service;

import com.spbo.blog.domain.CommentMain;
import com.spbo.blog.domain.CommentWithBLOBs;
import com.spbo.blog.domain.UpdataComment;

import java.util.List;

public interface CommentService {
    List<CommentWithBLOBs> getComment(Integer Aid);
    CommentMain getCommentJson(Integer Aid,Integer Page);
    Integer getCommentNum(Integer Aid);
    boolean insertSelective(UpdataComment record);
    boolean deleteSelective(Integer id,Integer isChild);
}
