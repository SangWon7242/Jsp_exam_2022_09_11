package com.sbs.exam.service;

import com.sbs.exam.util.DBUtil;
import com.sbs.exam.util.SecSql;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class ArticleService {
  private Connection con;

  public ArticleService(Connection con) {
    this.con = con;
  }

  public int getItemsInAPage() {
    return 3;
  }

  public int getForPrintListTotalPage() {
    int itemsInAPage = getItemsInAPage();

    SecSql sql = SecSql.from("SELECT COUNT(*) AS cnt");
    sql.append("FROM article");
    int totalCount = DBUtil.selectRowIntValue(con, sql);
    int totalPage = (int) Math.ceil((double) totalCount / itemsInAPage);
    return totalPage;
  }

  public List<Map<String, Object>> getForPrintArticleRows(int page) {
    int itemsInAPage = getItemsInAPage();
    int limitFrom = (page - 1) * itemsInAPage;

    SecSql sql = SecSql.from("SELECT *");
    sql.append("FROM article");
    sql.append("ORDER BY id DESC");
    sql.append("LIMIT ?, ?", limitFrom, itemsInAPage);
    List<Map<String, Object>> articleRows = DBUtil.selectRows(con, sql);

    return articleRows;
  }
}
