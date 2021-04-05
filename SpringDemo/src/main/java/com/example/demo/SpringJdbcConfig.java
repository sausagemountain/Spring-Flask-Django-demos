package com.example.demo;

import com.example.demo.data.SQLiteJdbcDialectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.DialectResolver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.example.demo")
public class SpringJdbcConfig {

    @Bean
    public DialectResolver.JdbcDialectProvider SQLiteDialectProvider() {
        return new SQLiteJdbcDialectProvider();
    }

    @Bean
    public DataSource sqliteDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.sqlite.JDBC");
        dataSource.setUrl("jdbc:sqlite:SpringDemo.db");

        return dataSource;
    }

}
