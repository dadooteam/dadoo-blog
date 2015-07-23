/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package im.dadoo.blog.biz.bo;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import im.dadoo.blog.biz.dao.TagArticleDAO;
import im.dadoo.blog.biz.dao.TagDAO;
import im.dadoo.blog.domain.Tag;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author codekitten
 */
@Service
public class TagBO {

  @Resource
  private TagDAO tagDAO;

  @Resource
  private TagArticleDAO taDAO;

  public long insert(String name) {
    Tag tag = new Tag();
    tag.setName(name);
    return this.tagDAO.insert(tag);
  }

  public void updateById(long id, String name) {
    checkArgument(id > 0L);
    Tag tag = this.tagDAO.findById(id);
    if (tag != null) {
      tag.setName(name);
      this.tagDAO.updateById(tag);
    }
  }

  public void deleteById(long id) {
    checkArgument(id > 0L);
    this.tagDAO.deleteById(id);
    this.taDAO.deleteByTagId(id);
  }

  public Tag findById(long id) {
    checkArgument(id > 0L);
    return this.tagDAO.findById(id);
  }

  public Tag findByName(String name) {
    return this.tagDAO.findByName(name);
  }

  public List<Tag> list() {
    return this.tagDAO.list();
  }

  public long size() {
    return this.tagDAO.size();
  }

  public long sizeByTagId(long tagId) {
    checkArgument(tagId > 0L);
    return this.taDAO.sizeByTagId(tagId);
  }
}
