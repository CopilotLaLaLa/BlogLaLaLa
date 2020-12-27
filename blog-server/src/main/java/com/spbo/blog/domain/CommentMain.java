package com.spbo.blog.domain;

import java.io.Serializable;
import java.util.List;

public class CommentMain implements Serializable {
    private List<CommentJson> CommentText;

    private Integer CommentsNumber;

    private Integer CommentsAllMain;

    private Integer CommentsMain;

    public List<CommentJson> getCommentText() {
        return CommentText;
    }

    public void setCommentText(List<CommentJson> commentText) {
        CommentText = commentText;
    }

    public Integer getCommentsNumber() {
        return CommentsNumber;
    }

    public void setCommentsNumber(Integer commentsNumber) {
        CommentsNumber = commentsNumber;
    }

    public Integer getCommentsMain() {
        return CommentsMain;
    }

    public void setCommentsMain(Integer commentsMain) {
        CommentsMain = commentsMain;
    }

    public Integer getCommentsAllMain() {
        return CommentsAllMain;
    }

    public void setCommentsAllMain(Integer commentsAllMain) {
        CommentsAllMain = commentsAllMain;
    }
}
