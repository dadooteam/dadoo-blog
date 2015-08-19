/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package im.dadoo.blog.web.controller;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import im.dadoo.blog.biz.bo.ArticleBO;
import im.dadoo.blog.biz.bo.TagBO;
import im.dadoo.blog.biz.dto.ArticleDTO;
import im.dadoo.blog.cons.DadooConstant;
import im.dadoo.blog.domain.Tag;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class ArticleController {

  private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

  private static final Logger elogger = LoggerFactory.getLogger(Exception.class);
  
  @Resource
  private ArticleBO articleBO;
  
  @Resource
  private TagBO tagBO;

  @RequestMapping(value = "/article/{id}", method = RequestMethod.GET)
  public String item(ModelMap map, @PathVariable Long id) {
    String result = "item";
    try {
      checkArgument(id > 0L);
      ArticleDTO articleDTO = this.articleBO.findById(id);
      checkNotNull(articleDTO, String.format("id{%d} is not existed", id));
      this.articleBO.click(id);
      map.addAttribute("articleDTO", articleDTO);
      map.addAttribute("prev-next", this.articleBO.findPrevAndNextById(id));
    } catch (Exception e) {
      logger.error(e.getLocalizedMessage());
      elogger.error("ERROR", e);
      result = "redirect:/404";
    }
    return result;
  }

  @RequestMapping(value = "/tag/{id}", method = RequestMethod.GET)
  public String list(ModelMap map, @PathVariable Long id,
          @RequestParam(required = false) Integer pagecount,
          @RequestParam(required = false) Integer pagesize) {
    String result = "list";
    try {
      checkArgument(id > 0L);
      if (pagecount == null || pagecount <= 0) {
        pagecount = 1;
      }
      if (pagesize == null || pagesize <= 0) {
        pagesize = 10;
      }
      Tag tag = this.tagBO.findById(id);
      checkNotNull(tag, String.format("tag id{%d} is not existed", id));
      List<ArticleDTO> articleDTOs = this.articleBO.pageByTagId(id, pagecount, pagesize);
      map.addAttribute("currentTag", tag);
      map.addAttribute("articleDTOs", articleDTOs);
      map.addAttribute("curPagecount", pagecount);
      map.addAttribute("maxPagecount", this.articleBO.maxPagecountByTagId(id, pagesize));
    } catch (Exception e) {
      logger.error(e.getLocalizedMessage());
      elogger.error("ERROR", e);
      result = "redirect:/404";
    }
    return result;
  }

  @RequestMapping(value = "/admin/article/add", method = RequestMethod.POST)
  public String insert(@RequestParam String title, 
          @RequestParam String html,
          @RequestParam String markdown,
          @RequestParam(required = false) Integer top,
          @RequestParam(required = false) Integer hidden,
          @RequestParam(required = false) List<Long> tagIds) {
    String result = "redirect:/admin/article";
    try {
      checkNotNull(title);
      checkNotNull(markdown);
      checkNotNull(html);
      if (top == null) {
        top = DadooConstant.TOP_N;
      } else {
        checkArgument(top == DadooConstant.TOP_N || top == DadooConstant.TOP_Y);
      }
      if (hidden == null) {
        hidden = DadooConstant.HIDDEN_N;
      } else {
        checkArgument(hidden == DadooConstant.HIDDEN_N || hidden == DadooConstant.HIDDEN_Y);
      }
      this.articleBO.insert(title, markdown, html, top, hidden, tagIds);
    } catch (Exception e) {
      logger.error(e.getLocalizedMessage());
      elogger.error("ERROR", e);
      result = "redirect:/404";
    }
    return result;
  }

  @RequestMapping(value = "/admin/article/{id}/update", method = RequestMethod.POST)
  public String update(@PathVariable Long id, @RequestParam String title,
          @RequestParam String html, @RequestParam String markdown,
          @RequestParam(required = false) Integer top, 
          @RequestParam(required = false) Integer hidden,
          @RequestParam(required = false) List<Long> tagIds) {
    String result = "redirect:/admin/article"; 
    try {
      checkArgument(id > 0L);
      checkNotNull(title);
      checkNotNull(markdown);
      checkNotNull(html);
      if (top == null) {
        top = DadooConstant.TOP_N;
      } else {
        checkArgument(top == DadooConstant.TOP_N || top == DadooConstant.TOP_Y);
      } 
      if (hidden == null) {
        hidden = DadooConstant.HIDDEN_N;
      } else {
        checkArgument(hidden == DadooConstant.HIDDEN_N || hidden == DadooConstant.HIDDEN_Y);
      }
      this.articleBO.updateById(id, title, markdown, html, top, hidden, tagIds);
    } catch (Exception e) {
      logger.error(e.getLocalizedMessage());
      elogger.error("ERROR", e);
      result = "redirect:/404";
    }
    return result;
  }

  @RequestMapping(value = "/admin/article/{id}/delete", method = RequestMethod.GET)
  public String delete(@PathVariable Long id) {
    String result = "redirect:/admin/article"; 
    try {
      checkArgument(id > 0L);
      this.articleBO.deleteById(id);
    } catch (Exception e) {
      logger.error(e.getLocalizedMessage());
      elogger.error("ERROR", e);
      result = "redirect:/404";
    }
    return result;
  }
  
}
