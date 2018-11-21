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
     * 内容
     */
    private String content;

    /**
     * 隐藏信息
     */
    private String hideInfo;

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
}