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
public class Tag implements Serializable {
  
  private static final long serialVersionUID = 3412256218213400971L;
  
  private Integer           id;
  
  private String            name;
  
  public static Tag of(String name) {
    Tag tag = new Tag();
    tag.setName(name);
    return tag;
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
  
}
