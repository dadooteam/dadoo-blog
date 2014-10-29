/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.blog.web.controller;

import im.dadoo.blog.domain.Article;
import im.dadoo.blog.domain.Link;
import im.dadoo.blog.domain.Tag;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;
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
  
  @RequestMapping(value = "/admin", method = RequestMethod.GET)
  public String getAdminPage() {
    return "admin/admin";
  }
  
  @RequestMapping(value = "/admin/tag", method = RequestMethod.GET)
  public String getTagAdminPage(ModelMap map) {
    map.addAttribute("tags", this.tagService.list());
    return "admin/tag";
  }
  
  @RequestMapping(value = "/admin/tag/add", method = RequestMethod.GET)
  public String getTagAddAdminPage() {
    return "admin/tag-add";
  }
  
  @RequestMapping(value = "/admin/tag/{id}/update", method = RequestMethod.GET)
  public String getTagUpdateAdminPage(@PathVariable Integer id, ModelMap map) {
    Tag tag = this.tagService.findById(id);
    if (tag != null) {
      map.addAttribute("tag", tag);
      return "admin/tag-update";
    } else {
      return "redirect:/404";
    }
  }
  
  @RequestMapping(value = "/admin/article", method = RequestMethod.GET)
  public String getArticleAdminPage(ModelMap map) {
    map.addAttribute("article-tags-pairs", this.articleService.list(0, Integer.MAX_VALUE));
    return "admin/article";
  }
  
  @RequestMapping(value = "/admin/article/add", method = RequestMethod.GET)
  public String getArticleAddAdminPage(ModelMap map) {
    map.addAttribute("tags", this.tagService.list());
    return "admin/article-add";
  }
  
  @RequestMapping(value = "/admin/article/{id}/update", method = RequestMethod.GET)
  public String getArticleUpdateAdminPage(@PathVariable Integer id, ModelMap map) {
    Pair<Article, List<Tag>> pair = this.articleService.findById(id);
    if (pair != null) {
      map.addAttribute("article-tags-pair", pair);
      map.addAttribute("tags", this.tagService.list());
      return "admin/article-update";
    } else {
      return "redirect:/404";
    }
  }
  
  @RequestMapping(value = "/admin/link", method = RequestMethod.GET)
  public String getLinkAdminPage(ModelMap map) {
    List<Link> links = this.linkService.list();
    map.addAttribute("links", links);
    return "admin/link";
  }
  
  @RequestMapping(value = "/admin/link/add", method = RequestMethod.GET)
  public String getLinkAddAdminPage(ModelMap map) {
    return "admin/link-add";
  }
  
  @RequestMapping(value = "/admin/link/{id}/update", method = RequestMethod.GET)
  public String getAdminLinkUpdatePage(ModelMap map, @PathVariable Integer id) {
    Link link = this.linkService.findById(id);
    if (link != null) {
      map.addAttribute("link", link);
      return "admin/link-update";
    } else {
      return "redirect:/404";
    }
  }
  
  @RequestMapping(value = "/admin/config", method = RequestMethod.GET)
  public String getAdminConfigPage(ModelMap map) {
    map.addAttribute("title", this.configService.getTitle());
    map.addAttribute("most-visit-article-size", this.configService.getMostVisitArticleSize());
    map.addAttribute("article-pagesize", this.configService.getArticlePagesize());
    return "admin/config";
  }
}
