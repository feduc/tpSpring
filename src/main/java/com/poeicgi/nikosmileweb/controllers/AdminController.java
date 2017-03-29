package com.poeicgi.nikosmileweb.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.poeicgi.nikosmileweb.controllers.base.view.ViewBaseController;
import com.poeicgi.nikosmileweb.dao.IMoodCrudRepository;
import com.poeicgi.nikosmileweb.dao.IProjectCrudRepository;
import com.poeicgi.nikosmileweb.dao.IUserCrudRepository;
import com.poeicgi.nikosmileweb.models.Project;
import com.poeicgi.nikosmileweb.models.User;

@Controller
@RequestMapping(path = AdminController.BASE_URL)
public class AdminController extends ViewBaseController<User> {

	public final static String BASE_URL = "/admin";

	@Autowired
	private IUserCrudRepository userCrud;

	@Autowired
	private IProjectCrudRepository projectCrud;

	@Autowired
	private IMoodCrudRepository moodCrud;

	public AdminController() {
		super(User.class, BASE_URL);

	}

	@RequestMapping(path = "/vote/", method = RequestMethod.GET)
	public String adminVoteGet(Model model) {

		return REDIRECT + MoodController.BASE_URL + "/vote";

	}

	@RequestMapping(path = "/choose", method = RequestMethod.GET)
	public String chooseView(Model model, @RequestParam(value = "projectName", defaultValue = "") String projectName) {

		List<Project> projects = null;
		if (!projectName.equals(null) && !projectName.equals("")) {
			projects = projectCrud.findProjectsByName("%" + projectName + "%");
		}
		
		model.addAttribute("projects", projects);
		
		model.addAttribute("projectName", projectName);

		return "admin/choose";
	}

	@RequestMapping(path = "/{projectId}/members", method = RequestMethod.GET)
	public String membersView(Model model, 
			@PathVariable(value = "projectId") String projectId,
			@RequestParam(value = "projectName") String projectName,
			@RequestParam(value = "userRegistration", defaultValue = "") String userRegistration) {

		List<User> users = null;
		if (!userRegistration.equals(null) && !userRegistration.equals("")) {
			users = userCrud.findUsersByRegistration("%" + userRegistration + "%");
		}
		
		List<User> members = null;

		members = userCrud.findMembersByProject(projectName);

		model.addAttribute("projectId", projectId);
		
		model.addAttribute("projectName", projectName);
		
		model.addAttribute("userRegistration", userRegistration);

		model.addAttribute("members", members);
		
		model.addAttribute("users", users);

		return "admin/members";
	}
	
	@RequestMapping(path = "/{projectId}/members/{userId}/remove", method = RequestMethod.GET)
	public String memberRemove(Model model, 
			@PathVariable(value = "projectId") String projectId,
			@PathVariable(value = "userId") String userId,
			@RequestParam(value = "projectName", defaultValue = "") String projectName,
			final BindingResult childBindingResult, final Model model2, 
			final RedirectAttributes redirectAttributes) {


		
		redirectAttributes.addAttribute("projectName", projectName);
		

		return REDIRECT + BASE_URL + "/" + projectId +"/members";
	}
	
}
