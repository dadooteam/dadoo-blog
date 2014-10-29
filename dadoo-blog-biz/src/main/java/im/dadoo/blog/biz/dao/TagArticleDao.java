/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.blog.biz.dao;

import im.dadoo.blog.domain.TagArticle;
import java.io.Serializable;
import javax.annotation.Resource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author codekitten
 */
@Repository
public class TagArticleDao extends BaseDao<TagArticle>{
  
  
  private static final String ADD_SQL = 
          "INSERT INTO t_tag_article(tag_id,article_id) VALUES(:tag_id,:article_id)";
  
  private static final String DELETE_BY_TAG_ID_SQL = 
          "DELETE FROM t_tag_article WHERE tag_id=:tag_id";
  
  private static final String DELETE_BY_ARTICLE_ID_SQL = 
          "DELETE FROM t_tag_article WHERE article_id=:article_id";
  
  private static final String SIZE_BY_TAG_ID_SQL = 
          "SELECT count(*) AS size FROM t_tag_article WHERE tag_id=:tag_id";
  
  @Resource
  private RowMapper<TagArticle> tagArticleRowMapper;
  
  public TagArticleDao() {
    super(TagArticle.class);
  }
  
  @Override
  public TagArticle add(TagArticle ta) {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("tag_id", ta.getTagId());
    sps.addValue("article_id", ta.getArticleId());
    this.jdbcTemplate.update(ADD_SQL, sps);
    return ta;
  }
  
  public void deleteByTagId(Integer tagId) {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("tag_id", tagId);
    this.jdbcTemplate.update(DELETE_BY_TAG_ID_SQL, sps);
  }
  
  public void deleteByArticleId(Integer articleId) {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("article_id", articleId);
    this.jdbcTemplate.update(DELETE_BY_ARTICLE_ID_SQL, sps);
  }
  
  public Serializable sizeByTagId(Integer tagId) {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("tag_id", tagId);
    return this.jdbcTemplate.queryForObject(SIZE_BY_TAG_ID_SQL, sps, this.sizeRowMapper);
  }
  
}
