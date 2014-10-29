/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */

package im.dadoo.blog.domain;

import java.io.Serializable;

/**
 *
 * @author codekitten
 */
public class TagArticle implements Serializable {
  
  private static final long serialVersionUID = 4275456959395318081L;
  
  private Integer           tagId;
  
  private Integer           articleId;
  
  public static TagArticle of(Integer tagId, Integer articleId) {
    TagArticle ta = new TagArticle();
    ta.setTagId(tagId);
    ta.setArticleId(articleId);
    return ta;
  }
  
  public Integer getTagId() {
    return tagId;
  }
  
  public void setTagId(Integer tagId) {
    this.tagId = tagId;
  }
  
  public Integer getArticleId() {
    return articleId;
  }
  
  public void setArticleId(Integer articleId) {
    this.articleId = articleId;
  }
  
}
