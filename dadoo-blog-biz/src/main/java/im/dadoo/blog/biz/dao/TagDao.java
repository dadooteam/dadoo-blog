/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.blog.biz.dao;

import static com.google.common.base.Preconditions.*;
import im.dadoo.blog.biz.util.ExceptionMessageMaker;
import im.dadoo.blog.domain.Tag;
import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author dadoosoon
 */
@Repository
public class TagDao extends BaseDao<Tag> {
  
  private static final String ADD_SQL = 
          "INSERT INTO t_tag(name) VALUES(:name)";
  
  private static final String UPDATE_SQL = 
          "UPDATE t_tag SET name=:name WHERE id=:id";
  
  private static final String DELETE_BY_ID_SQL = 
          "DELETE FROM t_tag WHERE id=:id";
  
  private static final String FIND_BY_ID_SQL = 
          "SELECT id,name FROM t_tag WHERE id=:id LIMIT 1";
  
  private static final String FIND_BY_NAME_SQL = 
          "SELECT id,name FROM t_tag WHERE name=:name LIMIT 1";
  
  private static final String LIST_SQL = 
          "SELECT id,name FROM t_tag ORDER BY id ASC";
  
  private static final String LIST_BY_ARTICLE_ID_SQL = 
          "SELECT t_tag.id AS id,t_tag.name AS name FROM t_tag "
          + "LEFT OUTER JOIN t_tag_article ON t_tag.id=t_tag_article.tag_id "
          + "WHERE t_tag_article.article_id=:article_id "
          + "ORDER BY id ASC";

  private static final String SIZE_SQL = 
          "SELECT count(*) AS size FROM t_tag";
  
  @Resource
  private RowMapper<Tag> tagRowMapper;
  
  public TagDao() {
    super(Tag.class);
  }
  
  @Override
  public Tag add(Tag tag) {
    checkNotNull(tag, ExceptionMessageMaker.makeNullPointerMessage("tag"));
    KeyHolder holder = new GeneratedKeyHolder();
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("name", tag.getName());
    this.jdbcTemplate.update(ADD_SQL, sps, holder);
    tag.setId(holder.getKey().intValue());
    return tag;
  }
  
  @Override
  public Tag update(Tag tag) {
    checkNotNull(tag, ExceptionMessageMaker.makeNullPointerMessage("tag"));
    checkNotNull(tag.getId(), ExceptionMessageMaker.makeNullPointerMessage("tag.id"));
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("id", tag.getId());
    sps.addValue("name", tag.getName());
    this.jdbcTemplate.update(UPDATE_SQL, sps);
    return tag;
  }
  
  @Override
  public void deleteById(Serializable id) {
    checkNotNull(id, ExceptionMessageMaker.makeNullPointerMessage("id"));
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("id", id);
    this.jdbcTemplate.update(DELETE_BY_ID_SQL, sps);
  }
  
  @Override
  public Tag findById(Serializable id) {
    checkNotNull(id, ExceptionMessageMaker.makeNullPointerMessage("id"));
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("id", id);
    List<Tag> tags = this.jdbcTemplate.query(FIND_BY_ID_SQL, sps, this.tagRowMapper);
    if (tags != null && !tags.isEmpty()) {
      return tags.get(0);
    } else {
      return null;
    }
  }
  
  public Tag findByName(String name) {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("name", name);
    List<Tag> tags = this.jdbcTemplate.query(FIND_BY_NAME_SQL, sps, this.tagRowMapper);
    if (tags != null && !tags.isEmpty()) {
      return tags.get(0);
    } else {
      return null;
    }
  }
  
  @Override
  public List<Tag> list() {
    return this.jdbcTemplate.query(LIST_SQL, this.tagRowMapper);
  }
  
  public List<Tag> listByArticleId(Integer articleId) {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("article_id", articleId);
    return this.jdbcTemplate.query(LIST_BY_ARTICLE_ID_SQL, sps, this.tagRowMapper);
  }
  
  @Override
  public int size() {
    return this.jdbcTemplate.queryForObject(SIZE_SQL, (SqlParameterSource)null, this.sizeRowMapper);
  }
  
}
