/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.blog.biz.dao.mapper;

import im.dadoo.blog.domain.Tag;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

/**
 *
 * @author shuwen.zsw
 */
@Component
public class TagRowMapper implements RowMapper<Tag> {

  @Override
  public Tag mapRow(ResultSet rs, int rowNum) throws SQLException {
    Tag tag = new Tag();
    tag.setId(rs.getLong("id"));
    tag.setName(rs.getString("name"));
    return tag;
  }
  
}
