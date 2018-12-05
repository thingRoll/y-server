package com.chhd.y.dao;

import com.chhd.y.pojo.ArticleWithBLOBs;

import java.util.List;

/**
 * ArticleDAO继承基类
 */
public interface ArticleDAO extends MyBatisBaseDao<ArticleWithBLOBs, Long> {

    int selectCount(Long categoryId);

    List<ArticleWithBLOBs> selectAll();

}