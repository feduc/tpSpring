package com.poeicgi.nikosmileweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.poeicgi.nikosmileweb.controllers.base.view.ViewBaseController;
import com.poeicgi.nikosmileweb.models.security.SecurityRole;


@Controller
@RequestMapping(SecurityRoleViewController.BASE_URL)
public class SecurityRoleViewController extends ViewBaseController<SecurityRole>{

	public final static String BASE_URL = "/admin/securityrole";

	public SecurityRoleViewController() {
		super(SecurityRole.class, BASE_URL);
	}

}
