package com.javaguru.shoppinglist;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@ComponentScan
@PropertySource({"classpath:/validation.properties",
        "classpath:/application.properties"})
public class ApplicationConfiguration {

    @Bean
    public DataSource dataSource(@Value("${jdbc.url}") String jdbcUrl,
                                 @Value("${driverClass}") String driverClass,
                                 @Value("${database.user.name}") String userName,
                                 @Value("${database.user.password}") String password) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(jdbcUrl);
        dataSource.setDriverClassName(driverClass);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
