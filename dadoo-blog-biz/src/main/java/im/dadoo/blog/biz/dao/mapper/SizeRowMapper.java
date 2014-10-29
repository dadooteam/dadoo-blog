/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.blog.biz.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

/**
 *
 * @author shuwen.zsw
 */
@Component
public class SizeRowMapper implements RowMapper<Integer> {

  @Override
  public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
    return rs.getInt("size");
  }
  
}
