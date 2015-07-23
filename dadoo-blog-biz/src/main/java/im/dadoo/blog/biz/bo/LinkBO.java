/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package im.dadoo.blog.biz.bo;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import im.dadoo.blog.biz.dao.LinkDAO;
import im.dadoo.blog.domain.Link;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author codekitten
 */
@Service
public class LinkBO {

  @Resource
  private LinkDAO linkDAO;

  public long insert(String name, String url, String description) {
    Link link = new Link();
    link.setName(name);
    link.setUrl(url);
    link.setDescription(description);
    return this.linkDAO.insert(link);
  }

  public void updateById(long id, String name, String url, String description) {
    checkArgument(id > 0L);
    Link link = this.linkDAO.findById(id);
    if (link != null) {
      link.setName(name);
      link.setUrl(url);
      link.setDescription(description);
      this.linkDAO.updateById(link);
    }
  }

  public void deleteById(long id) {
    checkArgument(id > 0L);
    this.linkDAO.deleteById(id);
  }

  public Link findById(long id) {
    checkArgument(id > 0L);
    return this.linkDAO.findById(id);
  }

  public List<Link> list() {
    return this.linkDAO.list();
  }
}
