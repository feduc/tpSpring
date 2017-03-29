package com.poeicgi.nikosmileweb.controllers.demo;

import java.io.IOException;

import javassist.tools.web.BadHttpRequest;

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

import com.mysql.fabric.Response;
import com.poeicgi.nikosmileweb.dao.IGreetingCrudRepository;
import com.poeicgi.nikosmileweb.models.demo.Greeting;

@RestController
public class RestGreetingController {

	@Autowired
	private IGreetingCrudRepository greetingcrud;

	//Rest actions.
	@RequestMapping(path="/demo/greeting", method=RequestMethod.GET)
    public Iterable<Greeting> listAction(
    		@RequestParam int offset,
    		@RequestParam int limit, HttpServletResponse response) throws IOException{

		if (limit<=0 || offset <=0){
			response.sendError(
					HttpStatus.BAD_REQUEST.value(),
					"Invalid limit and/or offset");
			return null;
		}
		Pageable pageable = new PageRequest(offset/limit, limit);

		return this.greetingcrud.findAll(pageable);
    }

//	@RequestMapping(path ="/demo/greeting", method=RequestMethod.POST)
//	public Greeting createAction(HttpServletResponse response,
//			@RequestParam (required = true) String content) throws IOException{
//		return this.newOrEdit(response, content, new Greeting());
//	}
//
//	@RequestMapping(path ="/demo/greeting/{id}/edit", method=RequestMethod.PUT)
//	public Greeting editAction(
//			HttpServletResponse response,
//			@PathVariable ("id") Long id,
//
//			@RequestParam(value = "content", required = true)
//			String content) throws IOException{
//
//		return this.newOrEdit(response, content, this.greetingcrud.findOne(id));
//	}
//
//	private Greeting newOrEdit(HttpServletResponse response, String content, Greeting greeting) throws IOException{
//
//		if (content.equals("")){
//			response.sendError(HttpStatus.BAD_REQUEST.value(), "Required name");
//			return null;
//		}
//
//		if(!content.toLowerCase().contains("hello")){
//			content = "Hello" + content;
//		}
//
//		greeting.setContent(content);
//
//		if (greeting.getId()==null){
//		response.setStatus(HttpStatus.CREATED.value());
//		}
//		greeting = new Greeting (content) ;
//
//		this.greetingcrud.save(greeting);
//
//		return greeting;
//	}

	@RequestMapping(path ="/demo/greeting/{id}/show", method = RequestMethod.GET)
	public Greeting showAction(
			HttpServletResponse response,
			@PathVariable ("id") Long id){
		Greeting greeting = this.greetingcrud.findOne(id);

		if(greeting == null)
		{
			response.setStatus(HttpStatus.NOT_FOUND.value());
		}
		return this.greetingcrud.findOne(id);
	}

	@RequestMapping(path ="/demo/greeting/{id}/delete", method = RequestMethod.DELETE)
	public void deleteAction(
			HttpServletResponse response,
			@PathVariable ("id") Long id){
		if (this.greetingcrud.exists(id)){
			this.greetingcrud.delete(id);
			response.setStatus(HttpStatus.NO_CONTENT.value());
		}
		else {
			response.setStatus(HttpStatus.NOT_FOUND.value());
		}

	}

	@RequestMapping(path ="/demo/greeting", method=RequestMethod.DELETE)
	public Greeting deleteAction(@RequestParam String id, HttpServletResponse response) throws BadHttpRequest, IOException{

		if (id.equals(null) || id.equals("")){
			response.sendError(HttpStatus.BAD_REQUEST.value(), "Pas content");
			return null;
		}

		this.greetingcrud.delete(Long.parseLong(id));
		return null;

	}
	//Delete, create, edit, show

	//Tools
	@RequestMapping(path ="/demo/greeting/addbourrin", method=RequestMethod.GET)
	public Greeting addgreeting(
			@RequestParam(value="name", defaultValue= "world")
			String name){
			Greeting greeting = new Greeting (
					String.format("Hello %s!", name)) ;
			this.greetingcrud.save(greeting);
			return greeting;

			// appel du resultat avec http://127.0.0.1:1215/demo/greeting/addbourrin?name=felix
			// resultat avec id variable {"id":5,"mysuper":"Hello felix!"}
	}
}
