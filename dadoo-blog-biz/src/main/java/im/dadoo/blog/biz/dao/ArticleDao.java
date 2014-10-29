/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.blog.biz.dao;


import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import im.dadoo.blog.biz.util.ExceptionMessageMaker;
import im.dadoo.blog.domain.Article;
import im.dadoo.spring.jdbc.support.Criteria;
import im.dadoo.spring.jdbc.support.Pair;
import im.dadoo.spring.jdbc.support.condition.Condition;
import im.dadoo.spring.jdbc.support.condition.Conditions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author codekitten
 */
@Repository
public class ArticleDao extends BaseDao<Article> {
  
  private static final String ALL_FIELDS = "id,title,html,text,publish_datetime,click";
  
  private static final String ADD_SQL = 
          "INSERT INTO t_article(title,html,text,publish_datetime,click) "
          + "VALUES(:title,:html,:text,:publish_datetime,:click)";
  
  private static final String UPDATE_CLICK_SQL = 
          "UPDATE t_article SET click=click+1 WHERE id=:id";
  
  private static final String UPDATE_SQL = 
          "UPDATE t_article SET title=:title,html=:html,text=:text,publish_datetime=:publish_datetime "
          + "WHERE id=:id";
  
  private static final String DELETE_BY_ID_SQL = 
          "DELETE FROM t_article WHERE id=:id";
  
  private static final String FIND_BY_ID_SQL = 
          "SELECT " + ALL_FIELDS +
          " FROM t_article WHERE id=:id LIMIT 1";
  
  private static final String FIND_PREV_BY_ID_SQL = 
          "SELECT " + ALL_FIELDS + 
          " FROM t_article WHERE id<:id ORDER BY id DESC LIMIT 1";
  
  private static final String FIND_NEXT_BY_ID_SQL = 
          "SELECT " + ALL_FIELDS + 
          " FROM t_article WHERE id>:id ORDER BY id ASC LIMIT 1";
  
  private static final String LIST_LIMIT_SQL = 
          "SELECT " + ALL_FIELDS + 
          " FROM t_article ORDER BY publish_datetime DESC LIMIT :limit";
  
  private static final String LIST_PAGINATION_SQL = 
          "SELECT " + ALL_FIELDS + 
          " FROM t_article ORDER BY publish_datetime DESC LIMIT :offset,:pagesize";
  
  private static final String LIST_CLICK_DESC_SQL = 
          "SELECT " + ALL_FIELDS + 
          " FROM t_article ORDER BY click DESC LIMIT :limit";
  
  private static final String LIST_BY_TAG_ID_PAGINATION_SQL = 
          "SELECT t_article.id AS id,t_article.title AS title,t_article.html AS html, "
          + "t_article.text AS text,t_article.publish_datetime AS publish_datetime, "
          + "t_article.click AS click FROM t_article "
          + "RIGHT OUTER JOIN t_tag_article ON t_article.id=t_tag_article.article_id "
          + "WHERE t_tag_article.tag_id=:tag_id "
          + "ORDER BY t_article.publish_datetime DESC LIMIT :offset,:pagesize";
  
  private static final String SIZE_SQL = 
          "SELECT count(*) AS size FROM t_article";
  
  @Resource
  private RowMapper<Article> articleRowMapper;
  
  public ArticleDao() {
    super(Article.class);
  }
  
  @Override
  public Article add(Article article) {
    checkNotNull(article, ExceptionMessageMaker.makeNullPointerMessage("article"));
    KeyHolder holder = new GeneratedKeyHolder();
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("title", article.getTitle());
    sps.addValue("html", article.getHtml());
    sps.addValue("text", article.getText());
    sps.addValue("publish_datetime", article.getPublishDatetime());
    sps.addValue("click", article.getClick());
    this.jdbcTemplate.update(ADD_SQL, sps, holder);
    article.setId(holder.getKey().intValue());
    return article;
  }
  
  @Override
  public Article update(Article article) {
    checkNotNull(article, ExceptionMessageMaker.makeNullPointerMessage("article"));
    checkNotNull(article.getId(), ExceptionMessageMaker.makeNullPointerMessage("article.id"));
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("id", article.getId());
    sps.addValue("title", article.getTitle());
    sps.addValue("html", article.getHtml());
    sps.addValue("text", article.getText());
    sps.addValue("publish_datetime", article.getPublishDatetime());
    this.jdbcTemplate.update(UPDATE_SQL, sps);
    return article;
  }
  
  public void updateClick(int id) {
    checkArgument(id > 0, ExceptionMessageMaker.makeGTMessage("id", 0, id));
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("id", id);
    this.jdbcTemplate.update(UPDATE_CLICK_SQL, sps);
  }
  
  @Override
  public void deleteById(Serializable id) {
    checkArgument((int)id > 0, ExceptionMessageMaker.makeGTMessage("id", 0, id));
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("id", id);
    this.jdbcTemplate.update(DELETE_BY_ID_SQL, sps);
  }
  
  @Override
  public Article findById(Serializable id) {
    checkNotNull(id, ExceptionMessageMaker.makeNullPointerMessage("id"));
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("id", id);
    List<Article> articles = this.jdbcTemplate.query(FIND_BY_ID_SQL, sps, this.articleRowMapper);
    if (articles != null && !articles.isEmpty()) {
      return articles.get(0);
    } else {
      return null;
    }
  }
  
  public Article findPrevById(Serializable id) {
    checkNotNull(id, ExceptionMessageMaker.makeNullPointerMessage("id"));
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("id", id);
    List<Article> articles = this.jdbcTemplate.query(FIND_PREV_BY_ID_SQL, sps, this.articleRowMapper);
    if (articles != null && !articles.isEmpty()) {
      return articles.get(0);
    } else {
      return null;
    }
  }
  
  public Article findNextById(Serializable id) {
    checkNotNull(id, ExceptionMessageMaker.makeNullPointerMessage("id"));
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("id", id);
    List<Article> articles = this.jdbcTemplate.query(FIND_NEXT_BY_ID_SQL, sps, this.articleRowMapper);
    if (articles != null && !articles.isEmpty()) {
      return articles.get(0);
    } else {
      return null;
    }
  }
  
  public List<Article> list(Integer limit) {
    checkArgument(limit > 0, ExceptionMessageMaker.makeGTMessage("limit", 0, limit));
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("limit", limit);
    List<Article> articles = this.jdbcTemplate.query(LIST_LIMIT_SQL, sps, this.articleRowMapper);
    return articles;
  }
  
  @Override
  public List<Article> list(Integer pagecount, Integer pagesize) {
    checkArgument(pagesize > 0, ExceptionMessageMaker.makeGTMessage("pagesize", 0, pagesize));
    checkArgument(pagecount >= 0, ExceptionMessageMaker.makeGEMessage("pagecount", 0, pagecount));
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("offset", pagecount * pagesize);
    sps.addValue("pagesize", pagesize);
    List<Article> articles = this.jdbcTemplate.query(LIST_PAGINATION_SQL, sps, this.articleRowMapper);
    return articles;
  }
  
  public List<Article> listOrderByClickDesc(Integer limit) {
    checkArgument(limit > 0, ExceptionMessageMaker.makeGTMessage("limit", 0, limit));
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("limit", limit);
    List<Article> articles = this.jdbcTemplate.query(LIST_CLICK_DESC_SQL, sps, this.articleRowMapper);
    return articles;
  }
  
  public List<Article> listByTagId(Integer tagId, Integer pagecount, Integer pagesize) {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("tag_id", tagId);
    sps.addValue("offset", pagecount * pagesize);
    sps.addValue("pagesize", pagesize);
    List<Article> articles = this.jdbcTemplate.query(
            LIST_BY_TAG_ID_PAGINATION_SQL, sps, this.articleRowMapper);
    return articles;
  }
  
  public List<Article> query(Map<String, Object> params, int pagecount, int pagesize) {
    List<Condition> conds = new ArrayList<>();
    MapSqlParameterSource sps = new MapSqlParameterSource();
    if (params.containsKey("publish_datetime")) {
      conds.add(Conditions.between("publish_datetime"));
      @SuppressWarnings("unchecked")
      Pair<Long, Long> pair = (Pair<Long, Long>)params.get("publish_datetime");
      sps.addValue("publish_datetime_1", pair.getV1());
      sps.addValue("publish_datetime_2", pair.getV2());
    }
    if (params.containsKey("text")) {
      conds.add(Conditions.like("text"));
      sps.addValue("text", (String)params.get("text"));
    }
    sps.addValue("offset", pagecount * pagesize);
    sps.addValue("pagesize", pagesize);
    String sql = "SELECT " + ALL_FIELDS + " FROM t_article " + Criteria.where(conds)
            + " ORDER BY publish_datetime DESC LIMIT :offset, :pagesize";
    return this.jdbcTemplate.query(sql, sps, this.articleRowMapper);
  }
  
  @Override
  public int size() {
    return this.jdbcTemplate.queryForObject(SIZE_SQL, (SqlParameterSource)null, this.sizeRowMapper);
  }
  
}
