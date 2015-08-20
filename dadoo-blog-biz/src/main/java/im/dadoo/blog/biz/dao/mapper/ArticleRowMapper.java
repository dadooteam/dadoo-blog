/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.blog.biz.dao.mapper;

import im.dadoo.blog.domain.Article;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

/**
 *
 * @author codekitten
 */
@Component
public class ArticleRowMapper implements RowMapper<Article> {

  @Override
  public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
    Article article = new Article();
      article.setId(rs.getLong("id"));
      article.setTitle(rs.getString("title"));
      article.setHtml(rs.getString("html"));
      article.setContent(rs.getString("content"));
      article.setGmtCreate(rs.getLong("gmt_create"));
      article.setClick(rs.getInt("click"));
      article.setTop(rs.getInt("top"));
      article.setHidden(rs.getInt("hidden"));
      return article;
  }
  
}
