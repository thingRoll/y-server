package com.chhd.y.service.impl;

import com.chhd.y.common.Response;
import com.chhd.y.dao.ArticleDAO;
import com.chhd.y.dto.ArticleDTO;
import com.chhd.y.pojo.Article;
import com.chhd.y.pojo.ArticleWithBLOBs;
import com.chhd.y.service.ArticleService;
import com.chhd.y.util.PropertiesUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ArticleService")
public class ArticleServiceImpl implements ArticleService {

    private static String imgBaseUrlFlag = PropertiesUtils.getProperty("img.flag");
    private static String imgBaseUrl = PropertiesUtils.getProperty("ftp.http.prefix");

    @Autowired
    private ArticleDAO articleDAO;

    @Override
    public Response add(ArticleWithBLOBs article) {
        Response response = checkAddData(article);
        if (response != null) {
            return response;
        }
        article.setCover(article.getCover().replace(imgBaseUrl, imgBaseUrlFlag));
        article.setContent(article.getContent().replace(imgBaseUrl, imgBaseUrlFlag));
        int row = articleDAO.insert(article);
        if (row > 0) {
            return Response.createBySuccess();
        } else {
            return Response.createByError();
        }
    }

    private Response checkAddData(ArticleWithBLOBs article) {
        if (StringUtils.isBlank(article.getCover())) {
            return Response.createByError("cover is null");
        }
        if (StringUtils.isBlank(article.getTitle())) {
            return Response.createByError("title is null");
        }
        if (article.getCategoryId() == null) {
            return Response.createByError("categoryId is null");
        }
        if (StringUtils.isBlank(article.getContent())) {
            return Response.createByError("content is null");
        }
        return null;
    }

    @Override
    public Response detail(Long id) {
        if (id == null) {
            return Response.createByError("id is null");
        }
        ArticleWithBLOBs article = articleDAO.selectByPrimaryKey(id);
        if (article != null) {
            article.setCover(article.getCover().replace(imgBaseUrlFlag, imgBaseUrl));
            article.setContent(article.getContent().replace(imgBaseUrlFlag, imgBaseUrl));
            ArticleDTO articleDTO = new ArticleDTO();
            BeanUtils.copyProperties(article, articleDTO);
            return Response.createBySuccess(articleDTO);
        } else {
            return Response.createByError();
        }
    }
}
