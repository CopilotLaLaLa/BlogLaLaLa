package com.spbo.blog.domain;

import java.io.Serializable;

/**
 * usermsg
 * @author 
 */
public class Usermsg implements Serializable {
    private String iduser;

    private String username;

    private String useract;

    private String userpsw;

    private Boolean userroot;

    private String userhig;

    private static final long serialVersionUID = 1L;

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUseract() {
        return useract;
    }

    public void setUseract(String useract) {
        this.useract = useract;
    }

    public String getUserpsw() {
        return userpsw;
    }

    public void setUserpsw(String userpsw) {
        this.userpsw = userpsw;
    }

    public Boolean getUserroot() {
        return userroot;
    }

    public void setUserroot(Boolean userroot) {
        this.userroot = userroot;
    }

    public String getUserhig() {
        return userhig;
    }

    public void setUserhig(String userhig) {
        this.userhig = userhig;
    }
}