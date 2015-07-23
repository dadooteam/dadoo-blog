/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package im.dadoo.blog.biz.dao;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import im.dadoo.blog.domain.Article;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author codekitten
 */
@Repository
public class ArticleDAO {

  private static final String INSERT_SQL
          = "INSERT INTO t_article(gmt_create,title,html,text,gmt_create,click) "
          + "VALUES(:gmt_create,:title,:html,:text,:click)";

  private static final String UPDATE_CLICK_SQL
          = "UPDATE t_article SET click=click+1 WHERE id=:id";

  private static final String UPDATE_SQL
          = "UPDATE t_article SET title=:title,html=:html,text=:text "
          + "WHERE id=:id";

  private static final String DELETE_BY_ID_SQL
          = "DELETE FROM t_article WHERE id=:id";

  private static final String FIND_BY_ID_SQL
          = "SELECT * FROM t_article WHERE id=:id LIMIT 1";

  private static final String FIND_PREV_BY_ID_SQL
          = "SELECT * FROM t_article WHERE id<:id ORDER BY id DESC LIMIT 1";

  private static final String FIND_NEXT_BY_ID_SQL
          = "SELECT * FROM t_article WHERE id>:id ORDER BY id ASC LIMIT 1";

  private static final String PAGE_SQL
          = "SELECT * FROM t_article ORDER BY id DESC LIMIT :offset,:pagesize";

  private static final String LIST_CLICK_DESC_SQL
          = "SELECT * FROM t_article ORDER BY click DESC LIMIT :limit";

  private static final String PAGE_BY_TAG_ID_PAGINATION_SQL
          = "SELECT t_article.id AS id,t_article.title AS title,t_article.html AS html, "
          + "t_article.text AS text,t_article.publish_datetime AS publish_datetime, "
          + "t_article.click AS click FROM t_article "
          + "RIGHT OUTER JOIN t_tag_article ON t_article.id=t_tag_article.article_id "
          + "WHERE t_tag_article.tag_id=:tag_id "
          + "ORDER BY t_article.publish_datetime DESC LIMIT :offset,:pagesize";

  private static final String SIZE_SQL
          = "SELECT count(*) AS size FROM t_article";

  @Resource
  private NamedParameterJdbcTemplate jdbcTemplate;

  @Resource
  private RowMapper<Article> articleRowMapper;

  @Resource
  private RowMapper<Long> sizeRowMapper;

  public long insert(Article article) {
    checkNotNull(article);
    KeyHolder holder = new GeneratedKeyHolder();
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("gmt_create", article.getGmtCreate());
    sps.addValue("title", article.getTitle());
    sps.addValue("html", article.getHtml());
    sps.addValue("text", article.getText());
    sps.addValue("click", article.getClick());
    this.jdbcTemplate.update(INSERT_SQL, sps, holder);
    return holder.getKey().longValue();
  }

  public void updateById(Article article) {
    checkNotNull(article);
    checkArgument(article.getId() > 0L);
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("id", article.getId());
    sps.addValue("title", article.getTitle());
    sps.addValue("html", article.getHtml());
    sps.addValue("text", article.getText());
    this.jdbcTemplate.update(UPDATE_SQL, sps);
  }

  public void updateClick(long id) {
    checkArgument(id > 0L);
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("id", id);
    this.jdbcTemplate.update(UPDATE_CLICK_SQL, sps);
  }

  public void deleteById(long id) {
    checkArgument(id > 0L);
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("id", id);
    this.jdbcTemplate.update(DELETE_BY_ID_SQL, sps);
  }

  public Article findById(long id) {
    Article result = null;
    checkArgument(id > 0L);
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("id", id);
    List<Article> articles = this.jdbcTemplate.query(FIND_BY_ID_SQL, sps, this.articleRowMapper);
    if (articles != null && !articles.isEmpty()) {
      result = articles.get(0);
    }
    return result;
  }

  public Article findPrevById(long id) {
    Article result = null;
    checkArgument(id > 0L);
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("id", id);
    List<Article> articles = this.jdbcTemplate.query(FIND_PREV_BY_ID_SQL, sps, this.articleRowMapper);
    if (articles != null && !articles.isEmpty()) {
      result = articles.get(0);
    }
    return result;
  }

  public Article findNextById(long id) {
    Article result = null;
    checkArgument(id > 0L);
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("id", id);
    List<Article> articles = this.jdbcTemplate.query(FIND_NEXT_BY_ID_SQL, sps, this.articleRowMapper);
    if (articles != null && !articles.isEmpty()) {
      return articles.get(0);
    } else {
      return null;
    }
  }

  public List<Article> page(int pagecount, int pagesize) {
    checkArgument(pagecount >= 1);
    checkArgument(pagesize > 0);
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("offset", (pagecount - 1) * pagesize);
    sps.addValue("pagesize", pagesize);
    List<Article> articles = this.jdbcTemplate.query(PAGE_SQL, sps, this.articleRowMapper);
    return articles;
  }

  public List<Article> listOrderByClickDesc(long limit) {
    checkArgument(limit > 0L);
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("limit", limit);
    List<Article> articles = this.jdbcTemplate.query(LIST_CLICK_DESC_SQL, sps, this.articleRowMapper);
    return articles;
  }

  public List<Article> pageByTagId(long tagId, int pagecount, int pagesize) {
    checkArgument(tagId > 0L);
    checkArgument(pagecount >= 1);
    checkArgument(pagesize > 0);
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("tag_id", tagId);
    sps.addValue("offset", pagecount * pagesize);
    sps.addValue("pagesize", pagesize);
    List<Article> articles = this.jdbcTemplate.query(PAGE_BY_TAG_ID_PAGINATION_SQL, sps, this.articleRowMapper);
    return articles;
  }

  public long size() {
    return this.jdbcTemplate.queryForObject(SIZE_SQL, (SqlParameterSource) null, this.sizeRowMapper);
  }

}