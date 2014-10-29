/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.blog.web.controller;

import im.dadoo.blog.domain.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author codekitten
 */
@Controller
public class TagController extends BaseController {
  
  @RequestMapping(value = "/admin/tag/add", method = RequestMethod.POST)
  public String save(@RequestParam String name) {
    if (this.tagService.findByName(name) == null) {
      this.tagService.add(name);
      return "redirect:/admin/tag";
    } else {
      return "redirect:/404";
    }
  }
  
  @RequestMapping(value = "/admin/tag/{id}/update", method = RequestMethod.POST)
  public String update(@PathVariable Integer id, @RequestParam String name) {
    Tag t = this.tagService.findByName(name);
    if (t == null || t.getId().equals(id)) {
      this.tagService.update(id, name);
      return "redirect:/admin/tag";
    } else {
      return "redirect:/404";
    }
  }
  
  @RequestMapping(value = "/admin/tag/{id}/delete", method = RequestMethod.GET)
  public String delete(@PathVariable Integer id) {
    this.tagService.deleteById(id);
    return "redirect:/admin/tag";
  }
}
