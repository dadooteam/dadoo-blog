/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.blog.web.controller;

import im.dadoo.blog.biz.bo.ArticleBO;
import im.dadoo.blog.web.ao.ConfigService;
import im.dadoo.blog.biz.bo.LinkBO;
import im.dadoo.blog.biz.bo.TagBO;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 *
 * @author codekitten
 */
public class BaseController {
  
  @Resource
  protected ArticleBO articleBO;
  
  @Resource
  protected TagBO tagBO;
  
  @Resource
  protected LinkBO linkBO;
  
  @Resource
  protected ConfigService configService;
  
  protected void renderSession(HttpSession session) {
    this.renderConfig(session);
  }
  
  protected void renderConfig(HttpSession session) {
    session.setAttribute("title", this.configService.getTitle());
  }
}
