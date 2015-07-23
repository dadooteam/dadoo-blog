/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package im.dadoo.blog.biz.dto;

import im.dadoo.blog.domain.Article;
import im.dadoo.blog.domain.Tag;
import java.util.List;

/**
 *
 * @author shuwen.zsw
 */
public class ArticleDTO {
  
  private Article article;
  
  private List<Tag> tags;

  /**
   * @return the article
   */
  public Article getArticle() {
    return article;
  }

  /**
   * @param article the article to set
   */
  public void setArticle(Article article) {
    this.article = article;
  }

  /**
   * @return the tags
   */
  public List<Tag> getTags() {
    return tags;
  }

  /**
   * @param tags the tags to set
   */
  public void setTags(List<Tag> tags) {
    this.tags = tags;
  }

  @Override
  public String toString() {
    return "ArticleDTO{" + "article=" + article + ", tags=" + tags + '}';
  }
}
