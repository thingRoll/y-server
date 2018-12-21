package com.chhd.y.dao;

import com.chhd.y.dto.ArticleVisitDTO;
import com.chhd.y.pojo.Article;
import com.chhd.y.pojo.ArticleVisit;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ArticleVisitDAO继承基类
 */
@Repository
public interface ArticleVisitDAO extends MyBatisBaseDao<ArticleVisit, Long> {

    List<ArticleVisit> selectByLast7Days(int plus);
}