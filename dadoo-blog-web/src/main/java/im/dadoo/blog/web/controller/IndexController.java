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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author dadoosoon
 */
@Controller
public class IndexController extends BaseController {
  
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String index(ModelMap map, @RequestParam(required = false) Integer pagecount) {
    if (pagecount == null) {
      pagecount = 1;
    }
    Integer pagesize = this.configService.getArticlePagesize();
    List<Pair<Article, List<Tag>>> pairs = this.articleService.list(pagecount - 1, pagesize);
    map.addAttribute("article-tags-pairs", pairs);
    map.addAttribute("cur-pagecount", pagecount);
    map.addAttribute("max-pagecount", 1 + this.articleService.size() / pagesize);
    return "list";
  }
  
  @RequestMapping(value = "/version", method = RequestMethod.GET)
  public String version(ModelMap map) {
    return "version";
  }
}
