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

  private long id;

  private String title;

  private String html;

  private String text;

  private long gmtCreate;
  
  private int top;

  private int click;

  public Article() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
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

  public long getGmtCreate() {
    return gmtCreate;
  }

  public void setGmtCreate(long gmtCreate) {
    this.gmtCreate = gmtCreate;
  }

  public int getClick() {
    return click;
  }

  public void setClick(int click) {
    this.click = click;
  }

    /**
   * @return the top
   */
  public int getTop() {
    return top;
  }

  /**
   * @param top the top to set
   */
  public void setTop(int top) {
    this.top = top;
  }

  @Override
  public String toString() {
    return "Article{" + "id=" + id + ", title=" + title + ", html=" + html + ", text=" + text + ", gmtCreate=" + gmtCreate + ", top=" + top + ", click=" + click + '}';
  }

}
