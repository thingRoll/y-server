package com.chhd.y.dao;

import com.chhd.y.pojo.Article;
import com.chhd.y.pojo.ArticleWithBLOBs;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * ArticleDAO继承基类
 */
@Repository
public interface ArticleDAO extends MyBatisBaseDao<ArticleWithBLOBs, Long> {

    int selectCount(Long categoryId);

    List<ArticleWithBLOBs> selectAll();

    List<ArticleWithBLOBs> selectAllByPlus(int plus);

    List<ArticleWithBLOBs> selectAllByParams(Map<String, String> map);

    List<ArticleWithBLOBs> selectAllByPlusCategoryId(@Param("plus") int plus,@Param("categoryId")  Long categoryId);
}