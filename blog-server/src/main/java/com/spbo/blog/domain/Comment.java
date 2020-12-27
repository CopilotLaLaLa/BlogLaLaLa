package com.spbo.blog.domain;

import java.io.Serializable;

/**
 * comment
 * @author 
 */
public class Comment implements Serializable {
    private Integer idcomment;

    private String cmuser;

    private Integer cmatc;

    private String cmtime;

    private Boolean cmischild;

    private static final long serialVersionUID = 1L;

    public Integer getIdcomment() {
        return idcomment;
    }

    public void setIdcomment(Integer idcomment) {
        this.idcomment = idcomment;
    }

    public String getCmuser() {
        return cmuser;
    }

    public void setCmuser(String cmuser) {
        this.cmuser = cmuser;
    }

    public Integer getCmatc() {
        return cmatc;
    }

    public void setCmatc(Integer cmatc) {
        this.cmatc = cmatc;
    }

    public String getCmtime() {
        return cmtime;
    }

    public void setCmtime(String cmtime) {
        this.cmtime = cmtime;
    }

    public Boolean getCmischild() {
        return cmischild;
    }

    public void setCmischild(Boolean cmischild) {
        this.cmischild = cmischild;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Comment other = (Comment) that;
        return (this.getIdcomment() == null ? other.getIdcomment() == null : this.getIdcomment().equals(other.getIdcomment()))
            && (this.getCmuser() == null ? other.getCmuser() == null : this.getCmuser().equals(other.getCmuser()))
            && (this.getCmatc() == null ? other.getCmatc() == null : this.getCmatc().equals(other.getCmatc()))
            && (this.getCmtime() == null ? other.getCmtime() == null : this.getCmtime().equals(other.getCmtime()))
            && (this.getCmischild() == null ? other.getCmischild() == null : this.getCmischild().equals(other.getCmischild()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getIdcomment() == null) ? 0 : getIdcomment().hashCode());
        result = prime * result + ((getCmuser() == null) ? 0 : getCmuser().hashCode());
        result = prime * result + ((getCmatc() == null) ? 0 : getCmatc().hashCode());
        result = prime * result + ((getCmtime() == null) ? 0 : getCmtime().hashCode());
        result = prime * result + ((getCmischild() == null) ? 0 : getCmischild().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", idcomment=").append(idcomment);
        sb.append(", cmuser=").append(cmuser);
        sb.append(", cmatc=").append(cmatc);
        sb.append(", cmtime=").append(cmtime);
        sb.append(", cmischild=").append(cmischild);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}