package com.spbo.blog.domain;

import java.io.Serializable;

/**
 * article
 * @author 
 */
public class Article implements Serializable {
    private Integer idarticle;

    private String acname;

    private String actime;

    private String actag;

    private String actext;

    private static final long serialVersionUID = 1L;

    public Integer getIdarticle() {
        return idarticle;
    }

    public void setIdarticle(Integer idarticle) {
        this.idarticle = idarticle;
    }

    public String getAcname() {
        return acname;
    }

    public void setAcname(String acname) {
        this.acname = acname;
    }

    public String getActime() {
        return actime;
    }

    public void setActime(String actime) {
        this.actime = actime;
    }

    public String getActag() {
        return actag;
    }

    public void setActag(String actag) {
        this.actag = actag;
    }

    public String getActext() {
        return actext;
    }

    public void setActext(String actext) {
        this.actext = actext;
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
        Article other = (Article) that;
        return (this.getIdarticle() == null ? other.getIdarticle() == null : this.getIdarticle().equals(other.getIdarticle()))
            && (this.getAcname() == null ? other.getAcname() == null : this.getAcname().equals(other.getAcname()))
            && (this.getActime() == null ? other.getActime() == null : this.getActime().equals(other.getActime()))
            && (this.getActag() == null ? other.getActag() == null : this.getActag().equals(other.getActag()))
            && (this.getActext() == null ? other.getActext() == null : this.getActext().equals(other.getActext()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getIdarticle() == null) ? 0 : getIdarticle().hashCode());
        result = prime * result + ((getAcname() == null) ? 0 : getAcname().hashCode());
        result = prime * result + ((getActime() == null) ? 0 : getActime().hashCode());
        result = prime * result + ((getActag() == null) ? 0 : getActag().hashCode());
        result = prime * result + ((getActext() == null) ? 0 : getActext().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", idarticle=").append(idarticle);
        sb.append(", acname=").append(acname);
        sb.append(", actime=").append(actime);
        sb.append(", actag=").append(actag);
        sb.append(", actext=").append(actext);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}