/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.blog.biz.dao.mapper;

import im.dadoo.blog.domain.TagArticle;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

/**
 *
 * @author shuwen.zsw
 */
@Component
public class TagArticleRowMapper implements RowMapper<TagArticle> {

  @Override
  public TagArticle mapRow(ResultSet rs, int rowNum) throws SQLException {
    TagArticle ta = new TagArticle();
      ta.setTagId(rs.getLong("tag_id"));
      ta.setArticleId(rs.getLong("article_id"));
      return ta;
  }
  
}
