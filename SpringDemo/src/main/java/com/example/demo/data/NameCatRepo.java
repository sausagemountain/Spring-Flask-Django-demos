package com.example.demo.data;

import org.springframework.data.jdbc.core.JdbcAggregateOperations;
import org.springframework.data.jdbc.repository.support.SimpleJdbcRepository;
import org.springframework.data.mapping.PersistentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NameCatRepo extends CrudRepository<NameCat, String> {

}
