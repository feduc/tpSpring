package com.poeicgi.nikosmileweb.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
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

	public String logIn(Model model) {

		User child = secuController.getConnectedUser();

		SecurityUser secu = secuCrud.findOne(child.getId());


		List<String> roles = securityRoleCrud.getRolesForSecurityUser(secu);



		if (roles.contains("admin")) {
			return REDIRECT+ "/user/create/";

		} else if (roles.contains("modo"))  {
			return REDIRECT + MoodController.BASE_URL + "/vote";
		} else if (roles.contains("visu"))  {
			return REDIRECT + MoodController.BASE_URL + "/vote";
		} else if (roles.contains("user"))  {
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

	@RequestMapping(path = ROUTE_INDEX, method = RequestMethod.GET)
	public String securities(Model model) {
		model.addAttribute("page", "All securities");
		model.addAttribute("fields",
				DumpFields.createContentsEmpty(super.getClazz()).fields);
		model.addAttribute("items", DumpFields.listFielder(super.getItems()));
		model.addAttribute(
				"currentItem",
				DumpFields.fielderAdvance(
						DumpFields.createContentsEmpty(super.getClazz()),
						super.getClazz()));
		return PATH_INDEX;
	}

	@RequestMapping(path = ROUTE_SECURITYROLESLINKS, method = RequestMethod.GET)
	public String setSecurityRolesForSecurityUserGet(Model model, @PathVariable Long securityId) {
		SecurityUser security = super.getItem(securityId);

		model.addAttribute("page",
				security.getLogin()
						+ " roles linker");
		model.addAttribute("fields", SecurityRole.FIELDS);
		model.addAttribute("currentItem", DumpFields.fielder(security));

		List<SecurityRole> securityRoles = (List<SecurityRole>) securityRoleCrud.findAll();
		model.addAttribute("items", DumpFields.<SecurityRole> listFielder(securityRoles));

		ArrayList<Long> securityRolesIds = new ArrayList<Long>();
		for (SecurityRole securityRole : security.getRoles()) {
			securityRolesIds.add(securityRole.getId());
		}
		model.addAttribute("linkedItems", securityRolesIds);

		return PATH_SECURITYROLESLINKS;
	}

	@RequestMapping(path = ROUTE_SECURITYROLESLINKS, method = RequestMethod.POST)
	public String setSecurityRolesForSecurityUserPost(Model model,
			@PathVariable Long securityId,
			@RequestParam(value = "ids[]") Long[] ids) {
		SecurityUser security = super.getItem(securityId);

		security.getRoles().clear();

		for (Long id : ids) {
			if (id != 0) {
				security.getRoles().add(securityRoleCrud.findOne(id));
			}
		}

		secuCrud.save(security);

		return PATH_SECURITYROLESLINKS_REDIRECT;
	}

	@RequestMapping(path = ROUTE_SECURITYROLES, method = RequestMethod.GET)
	public String getSecurityRolesForSecurityUser(Model model, @PathVariable Long securityId) {
		SecurityUser security = super.getItem(securityId);

		model.addAttribute("page",
				security.getLogin() + " security roles");
		model.addAttribute("fields", SecurityRole.FIELDS);
		model.addAttribute("currentItem", DumpFields.fielder(security));
		model.addAttribute("items", DumpFields
				.<SecurityRole> listFielder(new ArrayList<SecurityRole>(security.getRoles())));
		return PATH_SECURITYROLES;
	}
}
