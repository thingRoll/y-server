package com.chhd.y.common;

import com.chhd.y.pojo.ArticleCategory;

import java.util.Comparator;

public class ArticleCategorySort implements Comparator<ArticleCategory> {

    @Override
    public int compare(ArticleCategory o1, ArticleCategory o2) {
        int sortDiff = o1.getSort() - o2.getSort();
        int timeDiff = (int) (o1.getCreateTime().getTime() - o2.getCreateTime().getTime());
        if (sortDiff != 0) {
            return sortDiff;
        } else {
            return timeDiff;
        }
    }
}
