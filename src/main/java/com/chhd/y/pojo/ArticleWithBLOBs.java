package com.chhd.y.pojo;

import java.io.Serializable;

/**
 * article
 * @author 
 */
public class ArticleWithBLOBs extends Article implements Serializable {
    /**
     * 概述
     */
    private String summary;

    /**
     * 内容，html标签
     */
    private String content;

    /**
     * 隐藏信息
     */
    private String hideInfo;

    /**
     * 磁力链接
     */
    private String magnet;

    private static final long serialVersionUID = 1L;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHideInfo() {
        return hideInfo;
    }

    public void setHideInfo(String hideInfo) {
        this.hideInfo = hideInfo;
    }

    public String getMagnet() {
        return magnet;
    }

    public void setMagnet(String magnet) {
        this.magnet = magnet;
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
        ArticleWithBLOBs other = (ArticleWithBLOBs) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCover() == null ? other.getCover() == null : this.getCover().equals(other.getCover()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getKeyword() == null ? other.getKeyword() == null : this.getKeyword().equals(other.getKeyword()))
            && (this.getCategoryId() == null ? other.getCategoryId() == null : this.getCategoryId().equals(other.getCategoryId()))
            && (this.getVisit() == null ? other.getVisit() == null : this.getVisit().equals(other.getVisit()))
            && (this.getLike() == null ? other.getLike() == null : this.getLike().equals(other.getLike()))
            && (this.getPlus() == null ? other.getPlus() == null : this.getPlus().equals(other.getPlus()))
            && (this.getPan() == null ? other.getPan() == null : this.getPan().equals(other.getPan()))
            && (this.getPanCode() == null ? other.getPanCode() == null : this.getPanCode().equals(other.getPanCode()))
            && (this.getDisable() == null ? other.getDisable() == null : this.getDisable().equals(other.getDisable()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getSummary() == null ? other.getSummary() == null : this.getSummary().equals(other.getSummary()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getHideInfo() == null ? other.getHideInfo() == null : this.getHideInfo().equals(other.getHideInfo()))
            && (this.getMagnet() == null ? other.getMagnet() == null : this.getMagnet().equals(other.getMagnet()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCover() == null) ? 0 : getCover().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getKeyword() == null) ? 0 : getKeyword().hashCode());
        result = prime * result + ((getCategoryId() == null) ? 0 : getCategoryId().hashCode());
        result = prime * result + ((getVisit() == null) ? 0 : getVisit().hashCode());
        result = prime * result + ((getLike() == null) ? 0 : getLike().hashCode());
        result = prime * result + ((getPlus() == null) ? 0 : getPlus().hashCode());
        result = prime * result + ((getPan() == null) ? 0 : getPan().hashCode());
        result = prime * result + ((getPanCode() == null) ? 0 : getPanCode().hashCode());
        result = prime * result + ((getDisable() == null) ? 0 : getDisable().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getSummary() == null) ? 0 : getSummary().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getHideInfo() == null) ? 0 : getHideInfo().hashCode());
        result = prime * result + ((getMagnet() == null) ? 0 : getMagnet().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", summary=").append(summary);
        sb.append(", content=").append(content);
        sb.append(", hideInfo=").append(hideInfo);
        sb.append(", magnet=").append(magnet);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}