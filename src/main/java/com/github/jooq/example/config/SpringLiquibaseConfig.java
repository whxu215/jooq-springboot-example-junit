package com.github.jooq.example.config;

import javax.sql.DataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringLiquibaseConfig {

  @Value("${spring.liquibase.enable}")
  private boolean shouldRun;

  @Bean
  public SpringLiquibase liquibase(DataSource dataSource) {
    SpringLiquibase liquibase = new SpringLiquibase();
    liquibase.setShouldRun(shouldRun);
    liquibase.setChangeLog("classpath:master.xml");
    liquibase.setDataSource(dataSource);
    return liquibase;
  }
}