/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package im.dadoo.blog.web.controller;

import im.dadoo.blog.biz.dto.ArticleDTO;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author codekitten
 */
@Controller
public class IndexController extends BaseController {

  private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
  
  private static final Logger elogger = LoggerFactory.getLogger(Exception.class);
  
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String index(ModelMap map, @RequestParam(required = false) int pagecount,
          @RequestParam(required = false) int pagesize) {
    String result = "list";
    try {
      if (pagecount <= 0) {
        pagecount = 1;
      }
      if (pagesize <= 0) {
        pagesize = 10;
      }
      List<ArticleDTO> articleDTOs = this.articleBO.page(pagecount, pagesize);
      map.addAttribute("articleDTOs", articleDTOs);
      map.addAttribute("curPagecount", pagecount);
      map.addAttribute("maxPagecount", this.articleBO.maxPagecount(pagesize));
    } catch (Exception e) {
      logger.error(e.getLocalizedMessage());
      elogger.error("ERROR", e);
      result = "redirect:/404";
    }
    return result;
  }

  @RequestMapping(value = "/version", method = RequestMethod.GET)
  public String version(ModelMap map) {
    return "version";
  }
}
