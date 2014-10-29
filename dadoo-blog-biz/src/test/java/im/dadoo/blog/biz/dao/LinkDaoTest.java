///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package im.dadoo.blog.biz.dao;
//
//import im.dadoo.blog.biz.configuration.BizContext;
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//
///**
// *
// * @author shuwen.zsw
// */
//public class LinkDaoTest {
//  
//  private LinkDao linkDao;
//  
//  @Before
//  public void init() {
//    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(BizContext.class);
//    this.linkDao = ctx.getBean(LinkDao.class);
//  }
//  
//  @Test
//  public void testFindById() {
//    
//    System.out.println(this.linkDao.findById(1));
//  }
//}
