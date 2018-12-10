package com.chhd.y.dao;

import com.chhd.y.pojo.Article;
import com.chhd.y.pojo.ArticleWithBLOBs;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ArticleDAO继承基类
 */
@Repository
public interface ArticleDAO extends MyBatisBaseDao<ArticleWithBLOBs, Long> {

    int selectCount(Long categoryId);

    List<ArticleWithBLOBs> selectAll();
}