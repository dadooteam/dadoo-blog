/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.blog.biz.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 *
 * @author shuwen.zsw
 */
@Configuration
@ComponentScan("im.dadoo.blog.biz")
public class BizContext {
  
  @Bean(initMethod="init", destroyMethod = "close")
  public DataSource dataSource() {
    DruidDataSource dataSource = new DruidDataSource();
    dataSource.setUrl("jdbc:mysql://202.114.18.242:33066/dadooblog");
    dataSource.setUsername("root");
    dataSource.setPassword("dadoo2012dadoo");
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
}
