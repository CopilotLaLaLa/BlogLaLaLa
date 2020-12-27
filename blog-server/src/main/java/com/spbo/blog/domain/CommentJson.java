package com.spbo.blog.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * comment
 * @author 
 */
public class CommentJson implements Serializable {
    private CommentWithBLOBs cmb;

    private String username;

    private String userHig;

    private List<CommentJson> childCm;

    public CommentWithBLOBs getCmb() {
        return cmb;
    }

    public void setCmb(CommentWithBLOBs cmb) {
        this.cmb = cmb;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserHig() {
        return userHig;
    }

    public void setUserHig(String userHig) {
        this.userHig = userHig;
    }

    public List<CommentJson> getChildCm() {
        return childCm;
    }

    public void setChildCm(List<CommentJson> childCm) {
        this.childCm = childCm;
    }
}