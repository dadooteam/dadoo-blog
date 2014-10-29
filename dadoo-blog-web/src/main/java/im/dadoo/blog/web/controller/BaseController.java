/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.blog.web.controller;

import im.dadoo.blog.biz.service.ArticleService;
import im.dadoo.blog.web.service.ConfigService;
import im.dadoo.blog.biz.service.LinkService;
import im.dadoo.blog.biz.service.TagService;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 *
 * @author codekitten
 */
public class BaseController {
  
  @Resource
  protected ArticleService articleService;
  
  @Resource
  protected TagService tagService;
  
  @Resource
  protected LinkService linkService;
  
  @Resource
  protected ConfigService configService;
  
  protected void renderSession(HttpSession session) {
    this.renderConfig(session);
  }
  
  protected void renderConfig(HttpSession session) {
    session.setAttribute("title", this.configService.getTitle());
  }
}
