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

  private long tagId;

  private long articleId;

  public long getTagId() {
    return tagId;
  }

  public void setTagId(long tagId) {
    this.tagId = tagId;
  }

  public long getArticleId() {
    return articleId;
  }

  public void setArticleId(long articleId) {
    this.articleId = articleId;
  }

  @Override
  public String toString() {
    return "TagArticle{" + "tagId=" + tagId + ", articleId=" + articleId + '}';
  }

}
