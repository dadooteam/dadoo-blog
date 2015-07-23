/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.blog.web.controller;

import im.dadoo.blog.domain.Article;
import im.dadoo.blog.domain.Tag;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author codekitten
 */
@Controller
public class ArticleController extends BaseController {
  
  @RequestMapping(value = "/article/{id}", method = RequestMethod.GET)
  public String item(@PathVariable Integer id, ModelMap map) {
    this.articleService.click(id);
    Pair<Article, List<Tag>> pair = this.articleService.findById(id);
    if (pair != null) {
      map.addAttribute("pair", pair);
      map.addAttribute("prev-next", this.articleService.findPrevAndNextById(id));
      return "item";
    } else {
      return "redirect:/404";
    }
  }
  
  @RequestMapping(value = "/tag/{id}", method = RequestMethod.GET)
  public String list(ModelMap map, @PathVariable Integer id,
          @RequestParam(required = false) Integer pagecount) {
    if (pagecount == null) {
      pagecount = 1;
    }
    Integer pagesize = this.configService.getArticlePagesize();
    List<Pair<Article, List<Tag>>> pairs = 
            this.articleService.listByTagId(id, pagecount - 1, pagesize);
    map.addAttribute("current-tag", this.tagService.findById(id));
    map.addAttribute("article-tags-pairs", pairs);
    map.addAttribute("cur-pagecount", pagecount);
    map.addAttribute("max-pagecount", 1 + this.articleService.sizeByTagId(id)/ pagesize);
    return "list";
  }
  
  @RequestMapping(value = "/admin/article/add", method = RequestMethod.POST)
  public String save(@RequestParam String title, @RequestParam String html,
          @RequestParam(required = false) List<Integer> tagIds) {
    if (title != null && html != null) {
      this.articleService.insert(title, html, tagIds);
      return "redirect:/admin/article";
    } else {
      return "redirect:/404";
    }
  }
  
  @RequestMapping(value = "/admin/article/{id}/update", method = RequestMethod.POST)
  public String update(@PathVariable Integer id, @RequestParam String title, 
          @RequestParam String html, @RequestParam(required = false) List<Integer> tagIds) {
    if (title != null && html != null) {
      this.articleService.updateById(id, title, html, tagIds);
      return "redirect:/admin/article";
    } else {
      return "redirect:/404";
    }
  }
  
  @RequestMapping(value = "/admin/article/{id}/delete", method = RequestMethod.GET)
  public String delete(@PathVariable Integer id) {
    this.articleService.deleteById(id);
    return "redirect:/admin/article";
  }
}
