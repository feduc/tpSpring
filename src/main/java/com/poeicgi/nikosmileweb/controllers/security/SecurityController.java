package com.poeicgi.nikosmileweb.controllers.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poeicgi.nikosmileweb.dao.ISecurityUserCrudRepository;
import com.poeicgi.nikosmileweb.dao.IUserCrudRepository;
import com.poeicgi.nikosmileweb.models.User;
import com.poeicgi.nikosmileweb.models.security.SecurityUser;

@Controller
public class SecurityController {
	
	@Autowired
	private ISecurityUserCrudRepository secuCrud;

	@Autowired
	private IUserCrudRepository userCrud;
	
	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String login() {
		return "security/login";
	}
	
	@RequestMapping(path = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}
	
	public User getConnectedUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		org.springframework.security.core.userdetails.User userDetail = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
		String login = userDetail.getUsername();
		SecurityUser security = secuCrud.findByLogin(login);
		User child = userCrud.findOne(security.getId());
		return child;
	}

}
