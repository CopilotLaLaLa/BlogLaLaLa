package com.spbo.blog.domain;

import java.io.Serializable;

public class UpdataComment extends CommentWithBLOBs implements Serializable {
    private CommentWithBLOBs childMsg;
    private Integer idcomment;

    public CommentWithBLOBs getChildMsg() {
        return childMsg;
    }

    public void setChildMsg(CommentWithBLOBs childMsg) {
        this.childMsg = childMsg;
    }

    @Override
    public Integer getIdcomment() {
        return idcomment;
    }

    @Override
    public void setIdcomment(Integer idcomment) {
        this.idcomment = idcomment;
    }
}
