/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.blog.web.controller;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import im.dadoo.blog.biz.dto.ArticleDTO;
import im.dadoo.blog.domain.Article;
import im.dadoo.blog.domain.Link;
import im.dadoo.blog.domain.Tag;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author codekitten
 */
@Controller
public class AdminController extends BaseController {
  
  private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
  
  private static final Logger elogger = LoggerFactory.getLogger(AdminController.class);
  
  @RequestMapping(value = "/admin", method = RequestMethod.GET)
  public String getAdminPage() {
    return "admin/admin";
  }
  
  @RequestMapping(value = "/admin/tag", method = RequestMethod.GET)
  public String getTagAdminPage(ModelMap map) {
    map.addAttribute("tags", this.tagBO.list());
    return "admin/tag";
  }
  
  @RequestMapping(value = "/admin/tag/add", method = RequestMethod.GET)
  public String getTagAddAdminPage() {
    return "admin/tag-add";
  }
  
  @RequestMapping(value = "/admin/tag/{id}/update", method = RequestMethod.GET)
  public String getTagUpdateAdminPage(ModelMap map, @PathVariable long id) {
    String result = "admin/tag-update";
    try {
      checkArgument(id > 0L);
      Tag tag = this.tagBO.findById(id);
      checkNotNull(tag);
      map.addAttribute("tag", tag);
    } catch (Exception e) {
      logger.error(e.getLocalizedMessage());
      elogger.error("ERROR", e);
      result = "redirect:/404";
    }
    return result;
  }
  
  @RequestMapping(value = "/admin/article", method = RequestMethod.GET)
  public String getArticleAdminPage(ModelMap map) {
    map.addAttribute("articleDTOs", this.articleBO.page(1, Integer.MAX_VALUE));
    return "admin/article";
  }
  
  @RequestMapping(value = "/admin/article/add", method = RequestMethod.GET)
  public String getArticleAddAdminPage(ModelMap map) {
    map.addAttribute("tags", this.tagBO.list());
    return "admin/article-add";
  }
  
  @RequestMapping(value = "/admin/article/{id}/update", method = RequestMethod.GET)
  public String getArticleUpdateAdminPage(ModelMap map, @PathVariable long id) {
    String result = "admin/article-update";
    try {
      checkArgument(id > 0L);
      ArticleDTO articleDTO = this.articleBO.findById(id);
      checkNotNull(articleDTO);
      map.addAttribute("articleDTO", articleDTO);
      map.addAttribute("tags", this.tagBO.list());
    } catch (Exception e) {
      logger.error(e.getLocalizedMessage());
      elogger.error("ERROR", e);
      result = "redirect:/404";
    }
    return result;
  }
  
  @RequestMapping(value = "/admin/link", method = RequestMethod.GET)
  public String getLinkAdminPage(ModelMap map) {
    List<Link> links = this.linkBO.list();
    map.addAttribute("links", links);
    return "admin/link";
  }
  
  @RequestMapping(value = "/admin/link/add", method = RequestMethod.GET)
  public String getLinkAddAdminPage(ModelMap map) {
    return "admin/link-add";
  }
  
  @RequestMapping(value = "/admin/link/{id}/update", method = RequestMethod.GET)
  public String getAdminLinkUpdatePage(ModelMap map, @PathVariable long id) {
    String result = "admin/link-update";
    try {
      checkArgument(id > 0L);
      Link link = this.linkBO.findById(id);
      checkNotNull(link);
      map.addAttribute("link", link);
    } catch (Exception e) {
      logger.error(e.getLocalizedMessage());
      elogger.error("ERROR", e);
      result = "redirect:/404";
    }
    return result;
  }
  
  @RequestMapping(value = "/admin/config", method = RequestMethod.GET)
  public String getAdminConfigPage(ModelMap map) {
    map.addAttribute("title", this.configService.getTitle());
    map.addAttribute("most-visit-article-size", this.configService.getMostVisitArticleSize());
    map.addAttribute("article-pagesize", this.configService.getArticlePagesize());
    return "admin/config";
  }
}
