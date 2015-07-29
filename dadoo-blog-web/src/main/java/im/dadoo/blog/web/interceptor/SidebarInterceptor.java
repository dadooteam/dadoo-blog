/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.blog.web.interceptor;

import im.dadoo.blog.domain.Tag;
import im.dadoo.blog.biz.bo.ArticleBO;
import im.dadoo.blog.biz.bo.LinkBO;
import im.dadoo.blog.biz.bo.TagBO;
import im.dadoo.blog.web.vo.TagWellVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author codekitten
 */
public class SidebarInterceptor extends HandlerInterceptorAdapter {

  @Resource
  private ArticleBO articleBO;
  
  @Resource
  private TagBO tagBO;
  
  @Resource
  private LinkBO linkBO;

  @Override
  public void postHandle(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, ModelAndView mav) throws Exception {
    ModelMap map = mav.getModelMap();
    this.renderMostVisitArticles(map);
    this.renderTagWells(map);
    this.renderLinkWells(map);
  }
  
  private void renderMostVisitArticles(ModelMap map) {
    map.addAttribute("most-visit-articles", this.articleBO.listMostVisitedArticles(6));
  }
  
  private void renderTagWells(ModelMap map) {
    List<Tag> tags = this.tagBO.list();
    if (tags != null && !tags.isEmpty()) {
      List<TagWellVO> tagWellVOs = new ArrayList<>();
      for (Tag tag : tags) {
        TagWellVO tagWellVO = new TagWellVO();
        tagWellVO.setTag(tag);
        tagWellVO.setSize(this.tagBO.sizeByTagId(tag.getId()));
        tagWellVOs.add(tagWellVO);
      }
      map.addAttribute("tagWellVOs", tagWellVOs);
    }
  }
  
  private void renderLinkWells(ModelMap map) {
    map.addAttribute("links", this.linkBO.list());
  }
}
