/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.blog.web.controller;

import im.dadoo.blog.domain.Link;
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
public class LinkController extends BaseController {
  
  @RequestMapping(value = "/admin/link/add", method = RequestMethod.POST)
  public String save(@RequestParam String name, @RequestParam String url, 
          @RequestParam(required = false) String description) {
    if (!url.startsWith("http://")) {
      return "redirect:/404";
    }
    Link link = this.linkService.insert(name, url, description);
    if (link != null) {
      return "redirect:/admin/link";
    } else {
      return "redirect:/404";
    }
  }
  
  @RequestMapping(value = "/admin/link/{id}/update", method = RequestMethod.POST)
  public String update(@PathVariable Integer id, 
          @RequestParam(required = false) String name,
          @RequestParam(required = false) String url,
          @RequestParam(required = false) String description) {
    Link link = this.linkService.findById(id);
    if (link != null) {
      if (name != null) {
        link.setName(name);
      }
      if (url != null) {
        link.setUrl(url);
      }
      if (description != null) {
        link.setDescription(description);
      }
      this.linkService.updateById(id, link.getName(), link.getUrl(), link.getDescription());
      return "redirect:/admin/link";
    } else {
      return "redirect:/404";
    }
  }
  
  @RequestMapping(value = "/admin/link/{id}/delete", method = RequestMethod.GET)
  public String deleteById(@PathVariable Integer id) {
    this.linkService.deleteById(id);
    return "redirect:/admin/link";
  }
}
