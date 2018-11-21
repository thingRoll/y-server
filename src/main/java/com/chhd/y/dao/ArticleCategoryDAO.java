package com.chhd.y.dao;

import com.chhd.y.pojo.ArticleCategory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ArticleCategoryDAO继承基类
 */
@Repository
public interface ArticleCategoryDAO extends MyBatisBaseDao<ArticleCategory, Long> {

    List<ArticleCategory> selectArticleCategoryByPlus(int plus);

    List<ArticleCategory> selectArticleCategoryByParentId(@Param("parentId") Long parentId, @Param("plus") int plus);
}