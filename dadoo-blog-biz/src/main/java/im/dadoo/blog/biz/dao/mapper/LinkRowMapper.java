/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package im.dadoo.blog.biz.dao.mapper;

import im.dadoo.blog.domain.Link;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

/**
 *
 * @author shuwen.zsw
 */
@Component
public class LinkRowMapper implements RowMapper<Link> {

  @Override
  public Link mapRow(ResultSet rs, int rowNum) throws SQLException {
    Link link = new Link();
    link.setId(rs.getLong("id"));
    link.setName(rs.getString("name"));
    link.setUrl(rs.getString("url"));
    link.setDescription(rs.getString("description"));
    return link;
  }

}
