package com.example.demo.data;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.DialectResolver;
import org.springframework.data.relational.core.dialect.Dialect;
import org.springframework.jdbc.core.JdbcOperations;

import java.util.Optional;

@Configuration
public class SQLiteJdbcDialectProvider implements DialectResolver.JdbcDialectProvider {
    @Override
    public Optional<Dialect> getDialect(JdbcOperations jdbcOperations) {
        return Optional.of(new SQLiteDialect());
    }
}
