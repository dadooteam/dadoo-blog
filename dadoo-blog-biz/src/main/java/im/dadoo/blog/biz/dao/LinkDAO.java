/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package im.dadoo.blog.biz.dao;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import im.dadoo.blog.domain.Link;
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
public class LinkDAO {

  private static final String INSERT_SQL
          = "INSERT INTO t_link(name,url,description) VALUES(:name,:url,:description)";

  private static final String UPDATE_BY_ID_SQL
          = "UPDATE t_link SET name=:name,url=:url,description=:description WHERE id=:id";

  private static final String DELETE_BY_ID_SQL
          = "DELETE FROM t_link WHERE id=:id";

  private static final String FIND_BY_ID_SQL
          = "SELECT * FROM t_link WHERE id=:id LIMIT 1";

  private static final String FIND_BY_NAME_SQL
          = "SELECT *n FROM t_link WHERE name=:name LIMIT 1";

  private static final String LIST_SQL
          = "SELECT * FROM t_link ORDER BY name ASC";

  private static final String SIZE_SQL
          = "SELECT count(*) AS size FROM t_link";

  @Resource
  private NamedParameterJdbcTemplate jdbcTemplate;

  @Resource
  private RowMapper<Link> linkRowMapper;

  @Resource
  private RowMapper<Long> sizeRowMapper;

  public long insert(Link link) {
    checkNotNull(link);
    KeyHolder holder = new GeneratedKeyHolder();
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("name", link.getName());
    sps.addValue("url", link.getUrl());
    sps.addValue("description", link.getDescription());
    this.jdbcTemplate.update(INSERT_SQL, sps, holder);
    return holder.getKey().longValue();
  }

  public void updateById(Link link) {
    checkNotNull(link);
    checkArgument(link.getId() > 0L);
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("id", link.getId());
    sps.addValue("name", link.getName());
    sps.addValue("url", link.getUrl());
    sps.addValue("description", link.getDescription());
    this.jdbcTemplate.update(UPDATE_BY_ID_SQL, sps);
  }

  public void deleteById(long id) {
    checkArgument(id > 0L);
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("id", id);
    this.jdbcTemplate.update(DELETE_BY_ID_SQL, sps);
  }

  public Link findById(long id) {
    Link result = null;
    checkArgument(id > 0L);
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("id", id);
    List<Link> links = this.jdbcTemplate.query(FIND_BY_ID_SQL, sps, this.linkRowMapper);
    if (links != null && !links.isEmpty()) {
      result = links.get(0);
    }
    return result;
  }

  public Link findByName(String name) {
    Link result = null;
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("name", name);
    List<Link> links = this.jdbcTemplate.query(FIND_BY_NAME_SQL, sps, this.linkRowMapper);
    if (!links.isEmpty()) {
      result = links.get(0);
    }
    return result;
  }

  public List<Link> list() {
    List<Link> links = this.jdbcTemplate.query(LIST_SQL, this.linkRowMapper);
    return links;
  }

  public long size() {
    return this.jdbcTemplate.queryForObject(SIZE_SQL, (SqlParameterSource) null, this.sizeRowMapper);
  }

}
