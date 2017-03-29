package com.poeicgi.nikosmileweb.dao.demo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.poeicgi.nikosmileweb.models.demo.Greeting;

public interface IGreetingCrudRepository extends CrudRepository<Greeting, Long> {
	
	List<Greeting> findAll(Pageable pageable);

}
