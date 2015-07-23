/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.blog.web.interceptor;

import im.dadoo.blog.domain.Tag;
import im.dadoo.blog.biz.bo.ArticleBO;
import im.dadoo.blog.web.service.ConfigService;
import im.dadoo.blog.biz.bo.LinkBO;
import im.dadoo.blog.biz.bo.TagBO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author shuwen.zsw
 */
public class SidebarInterceptor extends HandlerInterceptorAdapter {

  @Resource
  private ArticleBO articleService;
  
  @Resource
  private TagBO tagService;
  
  @Resource
  private ConfigService configService;
  
  @Resource
  private LinkBO linkService;

  @Override
  public void postHandle(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, ModelAndView mav) throws Exception {
    this.renderSidebar(mav.getModelMap());
  }
  
  private void renderSidebar(ModelMap map) {
    this.renderMostVisitArticles(map);
    this.renderTagWell(map);
    this.renderLinks(map);
  }
  
  private void renderMostVisitArticles(ModelMap map) {
    map.addAttribute("most-visit-articles", 
            this.articleService.listMostVisitedArticles(this.configService.getMostVisitArticleSize()));
  }
  
  private void renderTagWell(ModelMap map) {
    List<Tag> tags = this.tagService.list();
    if (tags != null && !tags.isEmpty()) {
      List<Pair<Tag, Integer>> pairs = new ArrayList<>();
      for (Tag tag : tags) {
        pairs.add(ImmutablePair.of(tag, this.tagService.sizeByTagId(tag.getId())));
      }
      map.addAttribute("tag-size-pairs", pairs);
    }
  }
  
  private void renderLinks(ModelMap map) {
    map.addAttribute("links", this.linkService.list());
  }
}
