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
public class Link implements Serializable {
  
  private static final long serialVersionUID = -6090338071771675276L;
  
  
  private Integer           id;
  
  private String            name;
  
  private String            url;
  
  private String            description;
  
  public static Link of(String name, String url, String description) {
    Link link = new Link();
    link.setName(name);
    link.setUrl(url);
    link.setDescription(description);
    return link;
  }
  
  public Integer getId() {
    return id;
  }
  
  public void setId(Integer id) {
    this.id = id;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getUrl() {
    return url;
  }
  
  public void setUrl(String url) {
    this.url = url;
  }
  
  public String getDescription() {
    return description;
  }
  
  public void setDescription(String description) {
    this.description = description;
  }
  
}
