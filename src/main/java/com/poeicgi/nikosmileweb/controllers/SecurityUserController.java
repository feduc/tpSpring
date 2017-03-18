package com.poeicgi.nikosmileweb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poeicgi.nikosmileweb.controllers.base.view.ViewBaseController;
import com.poeicgi.nikosmileweb.dao.ISecurityUserCrudRepository;
import com.poeicgi.nikosmileweb.models.ChangeDate;
import com.poeicgi.nikosmileweb.models.security.SecurityUser;

@Controller
@RequestMapping(path = SecurityUserController.BASE_URL)
public class SecurityUserController extends ViewBaseController<SecurityUser>{

	public final static String BASE_URL = "/security";

	// value is the address to enter in the browser to launch index(), it can be
	// more than one when writing value = {"/path1", "/path2"}
	@RequestMapping(value = "/Security")
	public String index() {
		return "toto";
		// return "toto" works because toto.ftl is directly in templates, if it
		// was in templates.pages return would have to be equal to "pages/toto"
	}

	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String logInView(Model model){


			return "user/login";
	}

	@RequestMapping(path = "/login/do", method = RequestMethod.GET)
	public String logIn(Model model, SecurityUser data){

		List<SecurityUser> items = getItems();
		SecurityUser test = new SecurityUser();

		for (SecurityUser security : items) {
			if (security.getLogin().equals(data.getLogin())){
				test = security;
			}
		}

		if(test.getPassword().equals(data.getPassword())){
			if (test.getStatus().equals("admin"))
			{
				return REDIRECT+ "/user/create/";
				}
			else {
				return REDIRECT+ "/mood/vote/";
				}
			}
		else {
			return "user/login";
		}
	}

	@Autowired
	private ISecurityUserCrudRepository securityUserCrud;

	public SecurityUserController() {
		super(SecurityUser.class,BASE_URL);

	}
}
