package com.chhd.y.dto;

import com.google.common.collect.Lists;

import java.util.Date;
import java.util.List;

public class ArticleCategoryDTO {

    /**
     * 类别id
     */
    private Long id;

    /**
     * 如果父类别id=0，是根节点，一级类别
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
     * 创建时间
     */
    private Date createTime;

    private List<ArticleCategoryDTO> childList = Lists.newArrayList();

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

    public List<ArticleCategoryDTO> getChildList() {
        return childList;
    }

    public void setChildList(List<ArticleCategoryDTO> childList) {
        this.childList = childList;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
