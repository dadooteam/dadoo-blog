/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package im.dadoo.blog.web.vo;

import im.dadoo.blog.domain.Tag;

/**
 *
 * @author codekitten
 */
public class TagWellVO {
  
  private Tag tag;
  private long size;

  /**
   * @return the tag
   */
  public Tag getTag() {
    return tag;
  }

  /**
   * @param tag the tag to set
   */
  public void setTag(Tag tag) {
    this.tag = tag;
  }

  /**
   * @return the size
   */
  public long getSize() {
    return size;
  }

  /**
   * @param size the size to set
   */
  public void setSize(long size) {
    this.size = size;
  }

  @Override
  public String toString() {
    return "TagWellVO{" + "tag=" + tag + ", size=" + size + '}';
  }
  
}
