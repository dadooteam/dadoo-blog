/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.blog.biz.service;

import im.dadoo.blog.biz.dao.TagArticleDao;
import im.dadoo.blog.biz.dao.TagDao;
import im.dadoo.blog.domain.Tag;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author codekitten
 */
@Service
@Transactional
public class TagService {
  
  @Resource
  private TagDao tagDao;
  
  @Resource
  private TagArticleDao taDao;
  
  public Tag add(String name) {
    Tag tag = Tag.of(name);
    this.tagDao.add(tag);
    return tag;
  }
  
  public Tag update(Integer id, String name) {
    Tag tag = this.tagDao.findById(id);
    tag.setName(name);
    this.tagDao.update(tag);
    return tag;
  }
  
  public void deleteById(Integer id) {
    this.tagDao.deleteById(id);
    this.taDao.deleteByTagId(id);
  }
  
  public Tag findById(Integer id) {
    return this.tagDao.findById(id);
  }
  
  public Tag findByName(String name) {
    return this.tagDao.findByName(name);
  }
  
  public List<Tag> list() {
    return this.tagDao.list();
  }
  
  public Integer size() {
    return (Integer)this.tagDao.size();
  }
  
  public Integer sizeByTagId(Integer tagId) {
    return (Integer)this.taDao.sizeByTagId(tagId);
  }
}
