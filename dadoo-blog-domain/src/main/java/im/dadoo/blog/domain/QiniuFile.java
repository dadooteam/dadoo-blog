/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package im.dadoo.blog.domain;

/**
 *
 * @author codekitten
 */
public class QiniuFile {
  
  private String key;
  
  private String url;

  @Override
  public String toString() {
    return "QiniuFile{" + "key=" + key + ", url=" + url + '}';
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
  
}
