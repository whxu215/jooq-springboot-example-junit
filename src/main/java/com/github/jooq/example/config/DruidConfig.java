package com.github.jooq.example.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DruidConfig {

  private static final Logger LOGGER = LoggerFactory.getLogger(DruidConfig.class);

  @Bean
  @Primary
  public DataSource druidDataSource() {
    return DruidDataSourceBuilder.create().build();
  }

  @Bean
  @ConfigurationProperties("spring.datasource.druid.filter.wall")
  public WallConfig wallConfig() {
    return new WallConfig();
  }

  @Bean
  public WallFilter wallFilter(WallConfig wallConfig) {
    WallFilter filter = new WallFilter();
    filter.setConfig(wallConfig);
    filter.setDbType("mysql");
    return filter;
  }
} 