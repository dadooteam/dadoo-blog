/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.blog.biz.dao;

import static com.google.common.base.Preconditions.*;
import im.dadoo.blog.biz.util.ExceptionMessageMaker;
import im.dadoo.blog.domain.Link;
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
 * @author codekitten
 */
@Repository
public class LinkDao extends BaseDao<Link>{
  
  private static final String ADD_SQL = 
          "INSERT INTO t_link(name,url,description) VALUES(:name,:url,:description)";
  
  private static final String UPDATE_SQL = 
          "UPDATE t_link SET name=:name,url=:url,description=:description WHERE id=:id";
  
  private static final String DELETE_BY_ID_SQL = 
          "DELETE FROM t_link WHERE id=:id";
  
  private static final String FIND_BY_ID_SQL = 
          "SELECT id,name,url,description FROM t_link WHERE id=:id LIMIT 1";
  
  private static final String FIND_BY_NAME_SQL = 
          "SELECT id,name,url,description FROM t_link WHERE name=:name LIMIT 1";
  
  private static final String LIST_SQL = 
          "SELECT id,name,url,description FROM t_link ORDER BY name ASC";
  
  private static final String SIZE_SQL = 
          "SELECT count(*) AS size FROM t_link";
  
  @Resource
  private RowMapper<Link> linkRowMapper;
  
  public LinkDao() {
    super(Link.class);
  }
  
  @Override
  public Link add(Link link) {
    checkNotNull(link, ExceptionMessageMaker.makeNullPointerMessage("link"));
    KeyHolder holder = new GeneratedKeyHolder();
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("name", link.getName());
    sps.addValue("url", link.getUrl());
    sps.addValue("description", link.getDescription());
    this.jdbcTemplate.update(ADD_SQL, sps, holder);
    link.setId(holder.getKey().intValue());
    return link;
  }
  
  @Override
  public Link update(Link link) {
    checkNotNull(link, ExceptionMessageMaker.makeNullPointerMessage("link"));
    checkNotNull(link.getId(), ExceptionMessageMaker.makeNullPointerMessage("link.id"));
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("id", link.getId());
    sps.addValue("name", link.getName());
    sps.addValue("url", link.getUrl());
    sps.addValue("description", link.getDescription());
    this.jdbcTemplate.update(UPDATE_SQL, sps);
    return link;
  }
  
  @Override
  public void deleteById(Serializable id) {
    checkNotNull(id, ExceptionMessageMaker.makeNullPointerMessage("id"));
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("id", id);
    this.jdbcTemplate.update(DELETE_BY_ID_SQL, sps);
  }
  
  @Override
  public Link findById(Serializable id) {
    checkNotNull(id, ExceptionMessageMaker.makeNullPointerMessage("id"));
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("id", id);
    List<Link> links = this.jdbcTemplate.query(FIND_BY_ID_SQL, sps, this.linkRowMapper);
    if (links != null && !links.isEmpty()) {
      return links.get(0);
    } else {
      return null;
    }
  }
  
  public Link findByName(String name) {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("name", name);
    List<Link> links = this.jdbcTemplate.query(FIND_BY_NAME_SQL, sps, this.linkRowMapper);
    if (!links.isEmpty()) {
      return links.get(0);
    } else {
      return null;
    }
  }
  
  @Override
  public List<Link> list() {
    List<Link> links = this.jdbcTemplate.query(LIST_SQL, this.linkRowMapper);
    return links;
  }
  
  @Override
  public int size() {
    return this.jdbcTemplate.queryForObject(SIZE_SQL, (SqlParameterSource)null, this.sizeRowMapper);
  }
  
}
