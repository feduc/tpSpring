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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.poeicgi.nikosmileweb.controllers.base.view.ViewBaseController;
import com.poeicgi.nikosmileweb.dao.IMoodCrudRepository;
import com.poeicgi.nikosmileweb.dao.IProjectCrudRepository;
import com.poeicgi.nikosmileweb.dao.IUserCrudRepository;
import com.poeicgi.nikosmileweb.models.Project;
import com.poeicgi.nikosmileweb.models.User;

@Controller
@RequestMapping(path = AdminController.BASE_URL)
public class AdminController extends ViewBaseController<User>{
	
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
	public String adminVoteGet(Model model, @ModelAttribute User user, @ModelAttribute("child") User child,
			final BindingResult childBindingResult, final Model model2, final RedirectAttributes redirectAttributes) {

		child = userCrud.findOne(user.getId());
		redirectAttributes.addAttribute("child", child);
		return REDIRECT + MoodController.BASE_URL + "/vote";

	}
	  
	@RequestMapping(path = "/choose/", method = RequestMethod.GET)
	public String chooseView(Model model,@ModelAttribute("child") User child,
			@ModelAttribute("projectName") String projectName) {

		List<Project> projects = projectCrud.findProjectByName(projectName);

		model.addAttribute("child", child);

		model.addAttribute("projectName", projectName);
		
		model.addAttribute("projects", projects);

		
		
		return "admin/choose";
	}
}
