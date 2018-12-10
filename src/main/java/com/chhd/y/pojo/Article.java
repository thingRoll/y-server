package com.chhd.y.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * article
 * @author 
 */
public class Article implements Serializable {
    /**
     * 文章id
     */
    private Long id;

    /**
     * 封面
     */
    private String cover;

    /**
     * 标题
     */
    private String title;

    /**
     * 关键字，英文逗号“,”分割
     */
    private String keyword;

    /**
     * 分类id
     */
    private Long categoryId;

    /**
     * 浏览量
     */
    private Integer visit;

    /**
     * 点赞量
     */
    private Integer like;

    private Integer plus;

    /**
     * 网盘下载地址
     */
    private String pan;

    /**
     * 网盘提取码
     */
    private String panCode;

    /**
     * 状态，0=开启，1=禁用
     */
    private Integer disable;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getVisit() {
        return visit;
    }

    public void setVisit(Integer visit) {
        this.visit = visit;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public Integer getPlus() {
        return plus;
    }

    public void setPlus(Integer plus) {
        this.plus = plus;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getPanCode() {
        return panCode;
    }

    public void setPanCode(String panCode) {
        this.panCode = panCode;
    }

    public Integer getDisable() {
        return disable;
    }

    public void setDisable(Integer disable) {
        this.disable = disable;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
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
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", cover=").append(cover);
        sb.append(", title=").append(title);
        sb.append(", keyword=").append(keyword);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", visit=").append(visit);
        sb.append(", like=").append(like);
        sb.append(", plus=").append(plus);
        sb.append(", pan=").append(pan);
        sb.append(", panCode=").append(panCode);
        sb.append(", disable=").append(disable);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}