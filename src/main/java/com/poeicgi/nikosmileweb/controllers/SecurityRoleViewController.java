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




}
