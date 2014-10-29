/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.blog.web.controller;

import javax.servlet.http.HttpSession;
import org.apache.commons.configuration.ConfigurationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author codekitten
 */
@Controller
public class ConfigController extends BaseController {
  
  @RequestMapping(value = "/admin/config", method = RequestMethod.POST)
  public String config(HttpSession session, @RequestParam String title,
          @RequestParam("most-visit-article-size") Integer mvSize,
          @RequestParam("article-pagesize") Integer pagesize) {
    if (title != null) {
      this.configService.setTitle(title);
    }
    if (mvSize != null) {
      this.configService.setMostVisitArticleSize(mvSize);
    }
    if (pagesize != null) {
      this.configService.setArticlePagesize(pagesize);
    }
    try {
      this.configService.save();
      if (title != null) {
        session.setAttribute("title", title);
      }
    } catch (ConfigurationException ex) {
      
    }
    return "redirect:/admin/config";
  }
  
}
