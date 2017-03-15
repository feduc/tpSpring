package com.poeicgi.nikosmileweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poeicgi.nikosmileweb.controllers.base.view.ViewBaseController;
import com.poeicgi.nikosmileweb.dao.IUserCrudRepository;
import com.poeicgi.nikosmileweb.models.ChangeDate;
import com.poeicgi.nikosmileweb.models.User;

@Controller
@RequestMapping(path = "/user")
public class UserController extends ViewBaseController<User>{

	// value is the address to enter in the browser to launch index(), it can be
	// more than one when writing value = {"/path1", "/path2"}
	@RequestMapping(value = "/User")
	public String index() {
		return "toto";
		// return "toto" works because toto.ftl is directly in templates, if it
		// was in templates.pages return would have to be equal to "pages/toto"
	}
	
	@Autowired
	private IUserCrudRepository userCrud;
	
	public UserController() {
		super(User.class);

	}
}
