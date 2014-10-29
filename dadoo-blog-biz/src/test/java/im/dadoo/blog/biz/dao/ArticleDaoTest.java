///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package im.dadoo.blog.biz.dao;
//
//import im.dadoo.blog.biz.configuration.BizContext;
//import im.dadoo.blog.domain.Article;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//
///**
// *
// * @author shuwen.zsw
// */
//public class ArticleDaoTest {
//  
//  private ArticleDao articleDao;
//  
//  @Before
//  public void init() {
//    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(BizContext.class);
//    this.articleDao = ctx.getBean(ArticleDao.class);
//  }
//  
//  @Test
//  public void testList() {
//    int size = (int)this.articleDao.size();
//    System.out.println(size);
//  }
//  
//  public void testQuery() {
//    Map<String, Object> params = new HashMap<>();
//    params.put("text", "%爱%");
//    List<Article> list = this.articleDao.query(params, 0, 10);
//    System.out.println(list);
//  }
// 
//}
