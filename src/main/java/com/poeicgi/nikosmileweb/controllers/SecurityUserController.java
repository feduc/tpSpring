package com.poeicgi.nikosmileweb.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.poeicgi.nikosmileweb.controllers.base.view.AntoineViewBaseController;
import com.poeicgi.nikosmileweb.controllers.base.view.ViewBaseController;
import com.poeicgi.nikosmileweb.controllers.security.SecurityController;
import com.poeicgi.nikosmileweb.dao.ISecurityUserCrudRepository;
import com.poeicgi.nikosmileweb.models.User;
import com.poeicgi.nikosmileweb.models.security.SecurityUser;
import com.poeicgi.nikosmileweb.dao.IMoodCrudRepository;
import com.poeicgi.nikosmileweb.dao.ISecurityRoleCrudRepository;
import com.poeicgi.nikosmileweb.dao.IUserCrudRepository;
import com.poeicgi.nikosmileweb.models.Mood;
import com.poeicgi.nikosmileweb.models.security.SecurityRole;
import com.poeicgi.nikosmileweb.utils.DumpFields;

@Controller
@RequestMapping(path = SecurityUserController.BASE_URL)
public class SecurityUserController extends AntoineViewBaseController<SecurityUser> {

	public final static String BASE_URL = "/security";

	@Autowired
	private ISecurityUserCrudRepository secuCrud;

	@Autowired
	private SecurityController secuController;

	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String logInView(Model model) {

		return "user/login";
	}
	
	@Secured({"ROLE_ADMIN","ROLE_MODO","ROLE_VISU","ROLE_USER"})
	@RequestMapping(path = "/login/do", method = RequestMethod.GET)

	public String logIn(Model model) {

		User child = secuController.getConnectedUser();

		SecurityUser secu = secuCrud.findOne(child.getId());

		List<String> roles = securityRoleCrud.getRolesForSecurityUser(secu);


		if (roles.contains("ROLE_ADMIN")) {
			return REDIRECT+ "/user/create/";

		} else if (roles.contains("ROLE_MODO"))  {
			return REDIRECT + MoodController.BASE_URL + "/vote";
		} else if (roles.contains("ROLE_VISU"))  {
			return REDIRECT + MoodController.BASE_URL + "/vote";
		} else if (roles.contains("ROLE_USER"))  {
			return REDIRECT + MoodController.BASE_URL + "/vote";
 		} else {
 			return "base/erreur";
 		}
	}

	@Autowired
	private ISecurityUserCrudRepository securityUserCrud;

	@Autowired
	private IUserCrudRepository userCrud;

//	public SecurityUserController() {
//		super(SecurityUser.class, BASE_URL);
//
//	}

//	public final static String BASE_URL = "/admin/user";

	public final static String ROUTE_REDIRECT = "security";
	public final static String PATH_BASE = "baseantoine";

	public final static String index = "index";

	protected final static String securityroles = "roles";
	protected final static String securityrolesLinks = "roleslink";

	protected final static String associationMultiShow = "associationMutliShow";
	protected final static String associationMultiEdit = "associationMultiEdit";

	protected final static String PATH_INDEX = PATH_BASE + PATH + index;

	protected final static String PATH_SECURITYROLES = PATH_BASE + PATH
			+ associationMultiShow;
	protected final static String PATH_SECURITYROLESLINKS = PATH_BASE + PATH
			+ associationMultiEdit;
	protected final static String PATH_SECURITYROLESLINKS_REDIRECT = REDIRECT
			+ PATH + ROUTE_REDIRECT + PATH + index;

	protected final static String SECURITY_ID = "{securityId}";
	protected final static String ROUTE_INDEX = index;

	protected final static String ROUTE_SECURITYROLES = SECURITY_ID + PATH
			+ securityroles;
	protected final static String ROUTE_SECURITYROLESLINKS = SECURITY_ID + PATH
			+ securityrolesLinks;

	public SecurityUserController() {
		super(SecurityUser.class, BASE_URL);
		this.basePage = index;
		this.createRedirect = REDIRECT + this.basePath + PATH + ROUTE_INDEX;
		this.deleteRedirect = REDIRECT + this.basePath + PATH + ROUTE_INDEX;
		this.updateRedirect = REDIRECT + this.basePath + PATH + ROUTE_INDEX;
		this.showRedirect = REDIRECT + this.basePath + PATH + ROUTE_INDEX;
		this.listRedirect = REDIRECT + this.basePath + PATH + ROUTE_INDEX;
	}

	@Autowired
	ISecurityRoleCrudRepository securityRoleCrud;

}