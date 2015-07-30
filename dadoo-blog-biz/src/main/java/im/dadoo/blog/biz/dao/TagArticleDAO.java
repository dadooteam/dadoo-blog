/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.blog.biz.dao;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import im.dadoo.blog.biz.dao.mapper.SizeRowMapper;
import im.dadoo.blog.domain.TagArticle;
import java.io.Serializable;
import javax.annotation.Resource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author codekitten
 */
@Repository("taDAO")
public class TagArticleDAO {
  
  private static final String INSERT_SQL = 
          "INSERT INTO t_tag_article(tag_id,article_id) VALUES(:tag_id,:article_id)";
  
  private static final String DELETE_BY_TAG_ID_SQL = 
          "DELETE FROM t_tag_article WHERE tag_id=:tag_id";
  
  private static final String DELETE_BY_ARTICLE_ID_SQL = 
          "DELETE FROM t_tag_article WHERE article_id=:article_id";
  
  private static final String SIZE_BY_TAG_ID_SQL = 
          "SELECT count(*) AS size FROM t_tag_article "
          + "INNER JOIN t_article ON t_tag_article.article_id=t_article.id "
          + "WHERE t_tag_article.tag_id=:tag_id AND t_article.hidden=1";
  
  @Resource
  private NamedParameterJdbcTemplate jdbcTemplate;
  
  @Resource
  private RowMapper<TagArticle> tagArticleRowMapper;

  @Resource
  private RowMapper<Long> sizeRowMapper;
  
  public void insert(TagArticle ta) {
    checkNotNull(ta);
    checkArgument(ta.getArticleId() > 0L);
    checkArgument(ta.getTagId() > 0L);
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("tag_id", ta.getTagId());
    sps.addValue("article_id", ta.getArticleId());
    this.jdbcTemplate.update(INSERT_SQL, sps);
  }
  
  public void deleteByTagId(long tagId) {
    checkArgument(tagId > 0L);
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("tag_id", tagId);
    this.jdbcTemplate.update(DELETE_BY_TAG_ID_SQL, sps);
  }
  
  public void deleteByArticleId(long articleId) {
    checkArgument(articleId > 0L);
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("article_id", articleId);
    this.jdbcTemplate.update(DELETE_BY_ARTICLE_ID_SQL, sps);
  }
  
  public long sizeByTagId(long tagId) {
    checkArgument(tagId > 0L);
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("tag_id", tagId);
    return this.jdbcTemplate.queryForObject(SIZE_BY_TAG_ID_SQL, sps, this.sizeRowMapper);
  }
  
}
