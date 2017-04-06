package com.poeicgi.nikosmileweb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poeicgi.nikosmileweb.controllers.base.view.ViewBaseController;
import com.poeicgi.nikosmileweb.controllers.security.SecurityController;
import com.poeicgi.nikosmileweb.dao.ISecurityUserCrudRepository;
import com.poeicgi.nikosmileweb.models.User;
import com.poeicgi.nikosmileweb.models.security.SecurityUser;
import com.poeicgi.nikosmileweb.dao.ISecurityRoleCrudRepository;

@Controller
@RequestMapping(path = SecurityUserController.BASE_URL)
public class SecurityUserController extends ViewBaseController<SecurityUser> {

	public final static String BASE_URL = "/security";

	@Autowired
	private ISecurityUserCrudRepository secuCrud;

	@Autowired
	private SecurityController secuController;

	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String logInView(Model model) {

		return "user/login";
	}

	@RequestMapping(path = "/login/do", method = RequestMethod.GET)
	public String logIn(Model model){

		User child = secuController.getConnectedUser();

		SecurityUser secu = secuCrud.findOne(child.getId());

		List<String> roles = securityRoleCrud.getRolesForSecurityUser(secu);

		if (roles.contains("ROLE_ADMIN")) {
			return REDIRECT+ "/user/create/";

		} else if (roles.contains("ROLE_MODO"))  {
			return REDIRECT + ProjectController.BASE_URL + "/create/";
		} else if (roles.contains("ROLE_VISU"))  {
			return REDIRECT + UserController.BASE_URL + "/resume";
		} else if (roles.contains("ROLE_USER"))  {
			return REDIRECT + MoodController.BASE_URL + "/vote";
		} else {
			return "base/erreur";
		}
	}

	 public SecurityUserController() {
	 super(SecurityUser.class, BASE_URL);

	 }

	@Autowired
	private ISecurityRoleCrudRepository securityRoleCrud;

}