package com.chhd.y.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * article_category
 * @author 
 */
public class ArticleCategory implements Serializable {
    /**
     * 类别id
     */
    private Long id;

    /**
     * 如果父类别id=-1，是根节点，一级类别
     */
    private Long parentId;

    /**
     * 名称
     */
    private String name;

    /**
     * 排序，根节点优先级最高，越低越前，其次sort，越低越前，其次create_uptime，越新越后
     */
    private Integer sort;

    private Integer plus;

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

    /**
     * base64编码的图标
     */
    private String icon;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getPlus() {
        return plus;
    }

    public void setPlus(Integer plus) {
        this.plus = plus;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}