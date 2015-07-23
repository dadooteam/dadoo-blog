/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package im.dadoo.blog.biz.dao;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import im.dadoo.blog.domain.Tag;
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
public class TagDAO {

  private static final String INSERT_SQL
          = "INSERT INTO t_tag(name) VALUES(:name)";

  private static final String UPDATE_BY_ID_SQL
          = "UPDATE t_tag SET name=:name WHERE id=:id";

  private static final String DELETE_BY_ID_SQL
          = "DELETE FROM t_tag WHERE id=:id";

  private static final String FIND_BY_ID_SQL
          = "SELECT * FROM t_tag WHERE id=:id LIMIT 1";

  private static final String FIND_BY_NAME_SQL
          = "SELECT * FROM t_tag WHERE name=:name LIMIT 1";

  private static final String LIST_SQL
          = "SELECT * FROM t_tag ORDER BY id ASC";

  private static final String LIST_BY_ARTICLE_ID_SQL
          = "SELECT t_tag.id AS id,t_tag.name AS name FROM t_tag "
          + "LEFT OUTER JOIN t_tag_article ON t_tag.id=t_tag_article.tag_id "
          + "WHERE t_tag_article.article_id=:article_id "
          + "ORDER BY id ASC";

  private static final String SIZE_SQL
          = "SELECT count(*) AS size FROM t_tag";

  @Resource
  private NamedParameterJdbcTemplate jdbcTemplate;
  
  @Resource
  private RowMapper<Tag> tagRowMapper;

  @Resource
  private RowMapper<Long> sizeRowMapper;

  public long insert(Tag tag) {
    checkNotNull(tag);
    KeyHolder holder = new GeneratedKeyHolder();
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("name", tag.getName());
    this.jdbcTemplate.update(INSERT_SQL, sps, holder);
    return holder.getKey().longValue();
  }

  public void updateById(Tag tag) {
    checkNotNull(tag);
    checkArgument(tag.getId() > 0L);
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("id", tag.getId());
    sps.addValue("name", tag.getName());
    this.jdbcTemplate.update(UPDATE_BY_ID_SQL, sps);
  }

  public void deleteById(long id) {
    checkArgument(id > 0L);
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("id", id);
    this.jdbcTemplate.update(DELETE_BY_ID_SQL, sps);
  }

  public Tag findById(long id) {
    Tag result = null;
    checkArgument(id > 0L);
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("id", id);
    List<Tag> tags = this.jdbcTemplate.query(FIND_BY_ID_SQL, sps, this.tagRowMapper);
    if (tags != null && !tags.isEmpty()) {
      result = tags.get(0);
    }
    return result;
  }

  public Tag findByName(String name) {
    Tag result = null;
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("name", name);
    List<Tag> tags = this.jdbcTemplate.query(FIND_BY_NAME_SQL, sps, this.tagRowMapper);
    if (tags != null && !tags.isEmpty()) {
      result = tags.get(0);
    }
    return result;
  }

  public List<Tag> list() {
    return this.jdbcTemplate.query(LIST_SQL, this.tagRowMapper);
  }

  public List<Tag> listByArticleId(long articleId) {
    checkArgument(articleId > 0L);
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("article_id", articleId);
    return this.jdbcTemplate.query(LIST_BY_ARTICLE_ID_SQL, sps, this.tagRowMapper);
  }

  public long size() {
    return this.jdbcTemplate.queryForObject(SIZE_SQL, (SqlParameterSource) null, this.sizeRowMapper);
  }

}