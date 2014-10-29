/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.blog.web.configuration;

import im.dadoo.blog.web.interceptor.SessionInterceptor;
import im.dadoo.blog.web.interceptor.SidebarInterceptor;
//import im.dadoo.logger.client.LoggerClient;
//import im.dadoo.logger.client.impl.DefaultLoggerClient;
import javax.annotation.Resource;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 *
 * @author codekitten
 */
@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@PropertySource("classpath:config.properties")
@ComponentScan("im.dadoo.blog")
public class WebContext extends WebMvcConfigurerAdapter {
  
  @Resource
  private Environment env;
  
//  @Bean
//  public LoggerClient loggerClient() {
//    return new DefaultLoggerClient(env.getProperty("logger-server.url"));
//  }
  
  @Bean
  public PropertiesConfiguration config() throws ConfigurationException {
    return new PropertiesConfiguration("blog.properties");
  }
  
  @Bean
  public SessionInterceptor sessionInterceptor() {
    return new SessionInterceptor();
  }
  
  @Bean
  public HandlerInterceptor sidebarInterceptor() {
    return new SidebarInterceptor();
  }
  
  @Bean
  public InternalResourceViewResolver viewResolver() {
    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    viewResolver.setPrefix("/WEB-INF/views/");
    viewResolver.setSuffix(".jsp");
    return viewResolver;
  }
  
  @Bean
  public CommonsMultipartResolver multipartResolver() {
    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
    multipartResolver.setMaxUploadSize(1024*1024*2);
    return multipartResolver;
  }
  
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/resources/");
    registry.addResourceHandler("/static/**").addResourceLocations("/WEB-INF/static/");
  }
  
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(sessionInterceptor()).addPathPatterns("/**");
    registry.addInterceptor(sidebarInterceptor()).addPathPatterns("/").addPathPatterns("/version")
            .addPathPatterns("/article/**").addPathPatterns("/tag/**");
  }
}
