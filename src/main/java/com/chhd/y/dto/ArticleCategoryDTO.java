package com.chhd.y.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.Lists;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class ArticleCategoryDTO {

    /**
     * 类别id
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    /**
     * 如果父类别id=-1，是根节点，一级类别
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long parentId;

    /**
     * 名称
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;

    /**
     * 排序，根节点优先级最高，越低越前，其次sort，越低越前，其次create_uptime，越新越后
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer sort;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer plus;

    /**
     * 创建时间
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date createTime;

    /**
     * base64编码的图标
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String icon;

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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}