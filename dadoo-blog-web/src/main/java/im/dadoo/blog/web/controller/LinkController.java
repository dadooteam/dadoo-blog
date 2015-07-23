/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.blog.web.controller;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
  
  private static final Logger logger = LoggerFactory.getLogger(LinkController.class);
  
  private static final Logger elogger = LoggerFactory.getLogger(Exception.class);
  
  @RequestMapping(value = "/admin/link/add", method = RequestMethod.POST)
  public String save(@RequestParam String name, @RequestParam String url, 
          @RequestParam(required = false) String description) {
    String result = "redirect:/admin/link";
    try {
      checkNotNull(name);
      checkNotNull(url);
      this.linkBO.insert(name, url, description);
    } catch (Exception e) {
      logger.error(e.getLocalizedMessage());
      elogger.error("ERROR", e);
      result = "redirect:/404";
    }
    return result;
  }
  
  @RequestMapping(value = "/admin/link/{id}/update", method = RequestMethod.POST)
  public String update(@PathVariable long id, 
          @RequestParam String name, @RequestParam String url, @RequestParam String description) {
    String result = "redirect:/admin/link";
    try {
      checkArgument(id > 0L);
      this.linkBO.updateById(id, name, url, description);
    } catch (Exception e) {
      logger.error(e.getLocalizedMessage());
      elogger.error("ERROR", e);
      result = "redirect:/404";
    }
    return result;
  }
  
  @RequestMapping(value = "/admin/link/{id}/delete", method = RequestMethod.GET)
  public String deleteById(@PathVariable long id) {
    String result = "redirect:/admin/link";
    try {
      checkArgument(id > 0L);
      this.linkBO.deleteById(id);
    } catch (Exception e) {
      logger.error(e.getLocalizedMessage());
      elogger.error("ERROR", e);
      result = "redirect:/404";
    }
    return result;
  }
}
