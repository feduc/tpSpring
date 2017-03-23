package com.poeicgi.nikosmileweb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.poeicgi.nikosmileweb.controllers.base.view.ViewBaseController;
import com.poeicgi.nikosmileweb.dao.ISecurityUserCrudRepository;
import com.poeicgi.nikosmileweb.models.ChangeDate;
import com.poeicgi.nikosmileweb.models.User;
import com.poeicgi.nikosmileweb.models.security.SecurityUser;

@Controller
@RequestMapping(path = SecurityUserController.BASE_URL)
public class SecurityUserController extends ViewBaseController<SecurityUser> {

	public final static String BASE_URL = "/security";
	
	@Autowired
	private ISecurityUserCrudRepository secuCrud;

	// value is the address to enter in the browser to launch index(), it can be
	// more than one when writing value = {"/path1", "/path2"}
	@RequestMapping(value = "/Security")
	public String index() {
		return "toto";
		// return "toto" works because toto.ftl is directly in templates, if it
		// was in templates.pages return would have to be equal to "pages/toto"
	}

	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String logInView(Model model) {

		return "user/login";
	}

	@RequestMapping(path = "/login/do", method = RequestMethod.GET)

	public String logIn(Model model, SecurityUser data,
		// fabrication d'un user et renvoyer dans redirect
		@ModelAttribute("child") User child, final BindingResult childBindingResult, final Model model2,
		final RedirectAttributes redirectAttributes) {

		//creation d'un securityuser pour comparaison
		//rempli par une requete sur le login
		SecurityUser test = secuCrud.getSecurityTest(data.getLogin());

		//on recupere l'id pour trouver l'user
		child = userCont.getItem(test.getId());
		// permet de l'envoyer vers le redirect
		redirectAttributes.addAttribute("child", child);

		if ((test.getPassword().equals(data.getPassword())) && (test.getStatus().equals("admin"))) {
			return REDIRECT+ "/user/create/";

		} else if (test.getPassword().equals(data.getPassword()))  {
			return REDIRECT + MoodController.BASE_URL + "/vote";
		}
 		else {
 			return "user/login";
 		}
	}

	@Autowired
	private ISecurityUserCrudRepository securityUserCrud;

	@Autowired
	private UserController userCont;

	public SecurityUserController() {
		super(SecurityUser.class, BASE_URL);

	}
}
