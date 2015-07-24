/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.blog.biz.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.zaxxer.hikari.HikariDataSource;
import im.dadoo.blog.biz.bo.FileBO;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 *
 * @author shuwen.zsw
 */
@Configuration
@PropertySource("file:C:/config/dadooblog/config.properties")
@ComponentScan("im.dadoo.blog.biz")
public class BizContext {
  
  @Resource
  private Environment env;
  
  @Bean(initMethod="init", destroyMethod = "close")
  public DataSource dataSource() {
    DruidDataSource dataSource = new DruidDataSource();
    dataSource.setUrl(this.env.getProperty("db.url"));
    dataSource.setUsername(this.env.getProperty("db.username"));
    dataSource.setPassword(this.env.getProperty("db.password"));
    return dataSource;
  }
  
//  @Bean(destroyMethod = "close")
//  public DataSource dataSource() {
//    HikariDataSource dataSource = new HikariDataSource();
//    dataSource.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
//    dataSource.setJdbcUrl("jdbc:mysql://202.114.18.242:33066/dadooblog");
//    dataSource.setUsername("root");
//    dataSource.setPassword("dadoo2012dadoo");
//    dataSource.setConnectionTimeout(Long.MAX_VALUE);
//    return dataSource;
//  }
  
  @Bean
  public NamedParameterJdbcTemplate jdbcTemplate() {
    NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(this.dataSource());
    return jdbcTemplate;
  }
  
  @Bean
  public FileBO fileBO() {
    String accessKey = this.env.getProperty("qiniu.access_key");
    String secretKey = this.env.getProperty("qiniu.secret_key");
    String bucket = this.env.getProperty("qiniu.bucket");
    String project = this.env.getProperty("qiniu.project");
    String root = this.env.getProperty("qiniu.root");
    return new FileBO(accessKey, secretKey, bucket, project, root);
  }
}
