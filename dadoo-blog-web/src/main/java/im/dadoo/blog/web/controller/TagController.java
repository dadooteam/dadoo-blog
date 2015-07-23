/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.blog.web.controller;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import im.dadoo.blog.domain.Tag;
import org.apache.commons.lang3.StringUtils;
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
public class TagController extends BaseController {
  
  private static final Logger logger = LoggerFactory.getLogger(TagController.class);
  
  private static final Logger elogger = LoggerFactory.getLogger(Exception.class);
  
  @RequestMapping(value = "/admin/tag/add", method = RequestMethod.POST)
  public String insert(@RequestParam String name) {
    String result = "redirect:/admin/tag";
    try {
      checkArgument(StringUtils.isNotBlank(name));
      checkArgument(this.tagBO.findByName(name) != null, String.format("name{%s} is existed", name));
      this.tagBO.insert(name);
    } catch (Exception e) {
      logger.error(e.getLocalizedMessage());
      elogger.error("ERROR", e);
      result = "redirect:/404";
    }
    return result;
  }
  
  @RequestMapping(value = "/admin/tag/{id}/update", method = RequestMethod.POST)
  public String update(@PathVariable long id, @RequestParam String name) {
    String result = "redirect:/admin/tag";
    try {
      checkArgument(id > 0L);
      this.tagBO.updateById(id, name);
    } catch (Exception e) {
      logger.error(e.getLocalizedMessage());
      elogger.error("ERROR", e);
      result = "redirect:/404";
    }
    return result;
  }
  
  @RequestMapping(value = "/admin/tag/{id}/delete", method = RequestMethod.GET)
  public String delete(@PathVariable long id) {
    String result = "redirect:/admin/tag";
    try {
      checkArgument(id > 0L);
      this.tagBO.deleteById(id);
    } catch (Exception e) {
      logger.error(e.getLocalizedMessage());
      elogger.error("ERROR", e);
      result = "redirect:/404";
    }
    return result;
  }
}
