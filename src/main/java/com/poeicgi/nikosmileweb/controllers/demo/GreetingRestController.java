package com.poeicgi.nikosmileweb.controllers.demo;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poeicgi.nikosmileweb.dao.demo.IGreetingCrudRepository;
import com.poeicgi.nikosmileweb.models.demo.Greeting;

import javassist.tools.web.BadHttpRequest;

@RestController
public class GreetingRestController {

	@Autowired
	private IGreetingCrudRepository repository;

	@RequestMapping(path = "/demo/greeting/addBourrin", method = RequestMethod.GET)
	public Greeting addGreeting(@RequestParam(value = "name", defaultValue = "World") String user) {

		Greeting greeting = new Greeting(String.format("Hello, %s !", user));
		repository.save(greeting);

		return greeting;
	}
	


	@RequestMapping(path = "demo/greeting", method = RequestMethod.GET)
	public ArrayList<Greeting> listAction(@RequestParam int offset, @RequestParam int limit,
			HttpServletResponse response) throws IOException {
		if (limit<=0 || offset<=0){
			response.sendError(HttpStatus.BAD_REQUEST.value(), "invalid limit and/or offset");
			return null;
		}
		Pageable pageable = new PageRequest(offset/limit, limit);
		
		return (ArrayList<Greeting>) repository.findAll(pageable);
	}
	
	@RequestMapping(path = "demo/greeting/list", method = RequestMethod.GET)
	public ArrayList<Greeting> listSomeAction(@RequestParam(value = "page") String page,
			@RequestParam(value = "limit") String limit) {
		ArrayList<Greeting> greetings = (ArrayList<Greeting>) repository.findAll();
		ArrayList<Greeting> greets = new ArrayList<Greeting>();
		Integer offset = Integer.parseInt(limit)*(Integer.parseInt(page)-1);
		Integer end = offset + Integer.parseInt(limit);
		for (int i = offset; i < end ; i++) {
			if (greetings.size()>i){
				greets.add(greetings.get(i));
			}
		}
		return greets;
	}

	@RequestMapping(path = "/demo/greeting/addMany", method = RequestMethod.GET)
	public Greeting addManyGreeting(@RequestParam(value = "name", defaultValue = "World") String user,
			@RequestParam(value = "number", defaultValue = "1") String number) {
		int n = Integer.parseInt(number);

		Greeting greeting = new Greeting(String.format("Hello, %s !", user));
		for (int i = 0; i < n; i++) {
			Greeting greet = new Greeting(String.format("Hello, %s !", user));
			repository.save(greet);
		}

		return greeting;
	}

	@RequestMapping(path = "/demo/greeting", method = RequestMethod.POST)
	public Greeting createAction(@RequestParam String content, HttpServletResponse response)
			throws IOException {
		if (content == null || content.equals("")) {
			response.sendError(400, "Pas content!!");
			return null;
		}
		if (!content.toLowerCase().contains("hello")) {
			content = "Hello, " + content;
		}

		response.setStatus(201);
		Greeting greeting = new Greeting(content);

		repository.save(greeting);

		return greeting;
	}

	@RequestMapping(path = "/demo/greeting/{id}/delete", method = RequestMethod.DELETE)
	public void deleteAction(HttpServletResponse response, @PathVariable Long id){
		
		if (repository.exists(id)){
			response.setStatus(204);
			repository.delete(id);
		}
		else {
			response.setStatus(HttpStatus.NOT_FOUND.value());
		}

		

	}

	@RequestMapping(path = "/demo/greeting/{id}/edit", method = RequestMethod.PUT)
	public Greeting editAction(@RequestParam String content, HttpServletResponse response, @PathVariable Long id)
			throws IOException {
		if (content == null || content.equals("")) {
			response.sendError(400, "Pas content!");
			return null;
		}
		if (!content.toLowerCase().contains("hello")) {
			content = "Hello, " + content;
		}

		response.setStatus(204);
		Greeting greeting = new Greeting(id, content);

		repository.save(greeting);

		return greeting;
	}
	
	@RequestMapping(path = "/demo/greeting/{id}/show", method = RequestMethod.GET)
	public Greeting showAction(@PathVariable Long id, HttpServletResponse response) {
		Greeting greeting = repository.findOne(id);
		
		if (greeting == null){
			response.setStatus(HttpStatus.NOT_FOUND.value());
		}
		return greeting;
	}
}
