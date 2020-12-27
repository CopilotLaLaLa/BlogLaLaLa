package com.spbo.blog.domain;

import java.io.Serializable;

/**
 * comment
 * @author 
 */
public class CommentWithBLOBs extends Comment implements Serializable {
    private String cmrp;

    private String cmtext;

    private static final long serialVersionUID = 1L;

    public String getCmrp() {
        return cmrp;
    }

    public void setCmrp(String cmrp) {
        this.cmrp = cmrp;
    }

    public String getCmtext() {
        return cmtext;
    }

    public void setCmtext(String cmtext) {
        this.cmtext = cmtext;
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
        CommentWithBLOBs other = (CommentWithBLOBs) that;
        return (this.getIdcomment() == null ? other.getIdcomment() == null : this.getIdcomment().equals(other.getIdcomment()))
            && (this.getCmuser() == null ? other.getCmuser() == null : this.getCmuser().equals(other.getCmuser()))
            && (this.getCmatc() == null ? other.getCmatc() == null : this.getCmatc().equals(other.getCmatc()))
            && (this.getCmtime() == null ? other.getCmtime() == null : this.getCmtime().equals(other.getCmtime()))
            && (this.getCmischild() == null ? other.getCmischild() == null : this.getCmischild().equals(other.getCmischild()))
            && (this.getCmrp() == null ? other.getCmrp() == null : this.getCmrp().equals(other.getCmrp()))
            && (this.getCmtext() == null ? other.getCmtext() == null : this.getCmtext().equals(other.getCmtext()));
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
        result = prime * result + ((getCmrp() == null) ? 0 : getCmrp().hashCode());
        result = prime * result + ((getCmtext() == null) ? 0 : getCmtext().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", cmrp=").append(cmrp);
        sb.append(", cmtext=").append(cmtext);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}