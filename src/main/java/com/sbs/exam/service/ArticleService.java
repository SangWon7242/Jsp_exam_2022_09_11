package com.sbs.exam.service;

import com.sbs.exam.dao.ArticleDao;
import com.sbs.exam.util.DBUtil;
import com.sbs.exam.util.SecSql;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class ArticleService {
  private ArticleDao articleDao;

  public ArticleService(Connection con) {
    this.articleDao = new ArticleDao(con);
  }

  public int getItemsInAPage() {
    return 3;
  }

  public int getForPrintListTotalPage() {
    int itemsInAPage = getItemsInAPage();

    int totalCount = articleDao.getTotalCount();
    int totalPage = (int) Math.ceil((double) totalCount / itemsInAPage);

    return totalPage;
  }

  public List<Map<String, Object>> getForPrintArticleRows(int page) {
    int itemsInAPage = getItemsInAPage();
    int limitFrom = (page - 1) * itemsInAPage;

    List<Map<String, Object>> articleRows = articleDao.getArticleRows(limitFrom, itemsInAPage);

    return articleRows;
  }
}
