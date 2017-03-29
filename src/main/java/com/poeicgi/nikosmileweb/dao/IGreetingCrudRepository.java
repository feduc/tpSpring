package com.poeicgi.nikosmileweb.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.poeicgi.nikosmileweb.models.demo.Greeting;

public interface IGreetingCrudRepository extends CrudRepository<Greeting, Long> {

	List<Greeting> findAll (Pageable pageable);
}
