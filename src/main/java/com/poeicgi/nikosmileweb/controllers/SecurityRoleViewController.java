package com.poeicgi.nikosmileweb.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.poeicgi.nikosmileweb.controllers.base.view.AntoineViewBaseController;
import com.poeicgi.nikosmileweb.dao.ISecurityRoleCrudRepository;
import com.poeicgi.nikosmileweb.dao.ISecurityUserCrudRepository;
import com.poeicgi.nikosmileweb.models.security.SecurityUser;
import com.poeicgi.nikosmileweb.models.security.SecurityRole;
import com.poeicgi.nikosmileweb.utils.DumpFields;

@Controller
@RequestMapping(SecurityRoleViewController.BASE_URL)
public class SecurityRoleViewController extends AntoineViewBaseController<SecurityRole>{
	public final static String BASE_URL = "/admin/securityrole";

	public final static String ROUTE_BASE = "securityrole";
	public final static String ROUTE_REDIRECT = "admin/securityrole";

	public final static String PATH_BASE = "baseantoine";

	public final static String index = "index";

	protected final static String securities = "securities";
	protected final static String securitiesLinks = "securitieslink";

	protected final static String associationMultiShow = "associationMutliShow";
	protected final static String associationMultiEdit = "associationMultiEdit";


	protected final static String PATH_INDEX = PATH_BASE + PATH + index;

	protected final static String PATH_USERS = PATH_BASE + PATH + associationMultiShow;
	protected final static String PATH_USERSLINKS = PATH_BASE + PATH
			+ associationMultiEdit;
	protected final static String PATH_USERSLINKS_REDIRECT = REDIRECT + PATH
			+ ROUTE_REDIRECT + PATH + index;


	protected final static String PROJECT_ID = "{securityRoleId}";
	protected final static String ROUTE_INDEX = index;


	protected final static String ROUTE_USERS = PROJECT_ID + PATH + securities;
	protected final static String ROUTE_USERSLINKS = PROJECT_ID + PATH
			+ securitiesLinks;

	public SecurityRoleViewController() {
		super(SecurityRole.class, BASE_URL);
		this.basePage = index;

		this.createRedirect = REDIRECT + this.basePath + PATH + ROUTE_INDEX;
		this.deleteRedirect = REDIRECT + this.basePath + PATH + ROUTE_INDEX;
		this.updateRedirect = REDIRECT + this.basePath + PATH + ROUTE_INDEX;
		this.showRedirect = REDIRECT + this.basePath + PATH + ROUTE_INDEX;
		this.listRedirect = REDIRECT + this.basePath + PATH + ROUTE_INDEX;
	}

	@Autowired
	ISecurityRoleCrudRepository securityRoleCrud;

	@Autowired
	ISecurityUserCrudRepository securityCrud;

	@Secured({"ROLE_ADMIN","ROLE_PROJECTLEADER"})
	@RequestMapping(ROUTE_INDEX)
	public String projects(Model model) {
		model.addAttribute("page", "All roles");
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

	@RequestMapping(path = ROUTE_USERSLINKS, method = RequestMethod.GET)
	public String setTeamsForProjectGet(Model model,
			@PathVariable Long securityRoleId) {
		SecurityRole securityRole = super.getItem(securityRoleId);

		model.addAttribute("page", securityRole.getRole() + " securities linker");
		model.addAttribute("fields", SecurityUser.FIELDS);
		model.addAttribute("currentItem", DumpFields.fielder(securityRole));

		List<SecurityUser> securities = (List<SecurityUser>) securityCrud.findAll();
		model.addAttribute("items", DumpFields.<SecurityUser> listFielder(securities));

		ArrayList<Long> securitiesIds = new ArrayList<Long>();
		for (SecurityUser security : securityRole.getSecurities()) {
			securitiesIds.add(security.getId());
		}
		model.addAttribute("linkedItems", securitiesIds);

		return PATH_USERSLINKS;
	}

	@RequestMapping(path = ROUTE_USERSLINKS, method = RequestMethod.POST)
	public String setTeamsForProjectPost(Model model,
			@PathVariable Long securityRoleId,
			@RequestParam(value = "ids[]") Long[] ids) {
		SecurityRole securityRole = super.getItem(securityRoleId);

		securityRole.getSecurities().clear();

		for (Long id : ids) {
			if (id != 0) {
				securityRole.getSecurities().add(securityCrud.findOne(id));
			}
		}

		securityRoleCrud.save(securityRole);

		return PATH_USERSLINKS_REDIRECT;
	}

	@RequestMapping(path = ROUTE_USERS, method = RequestMethod.GET)
	public String getTeamsForProject(Model model, @PathVariable Long securityRoleId) {
		SecurityRole securityRole = super.getItem(securityRoleId);

		model.addAttribute("page", securityRole.getRole() + " roles");
		model.addAttribute("fields", SecurityUser.FIELDS);
		model.addAttribute("currentItem", DumpFields.fielder(securityRole));
		model.addAttribute("items", DumpFields
				.<SecurityUser> listFielder(new ArrayList<SecurityUser>(securityRole.getSecurities())));
		return PATH_USERS;
	}
}
