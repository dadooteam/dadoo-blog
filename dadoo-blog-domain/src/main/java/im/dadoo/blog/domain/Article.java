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
public class Article implements Serializable {

  private static final long serialVersionUID = -1312159335915767619L;

  private Integer id;

  private String title;

  private String html;

  private String text;

  private Long publishDatetime;

  private Integer click;

  public Article() {
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getHtml() {
    return html;
  }

  public void setHtml(String html) {
    this.html = html;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Long getPublishDatetime() {
    return publishDatetime;
  }

  public void setPublishDatetime(Long publishDatetime) {
    this.publishDatetime = publishDatetime;
  }

  public Integer getClick() {
    return click;
  }

  public void setClick(Integer click) {
    this.click = click;
  }

  @Override
  public String toString() {
    return "Article{" + "id=" + id + ", title=" + title + ", html=" + html + ", text=" + text + ", publishDatetime=" + publishDatetime + ", click=" + click + '}';
  }

}
