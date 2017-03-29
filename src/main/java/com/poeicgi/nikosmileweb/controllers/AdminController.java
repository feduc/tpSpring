package com.poeicgi.nikosmileweb.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import com.poeicgi.nikosmileweb.controllers.base.view.ViewBaseController;
import com.poeicgi.nikosmileweb.dao.IMoodCrudRepository;
import com.poeicgi.nikosmileweb.dao.IProjectCrudRepository;
import com.poeicgi.nikosmileweb.dao.ISecurityRoleCrudRepository;
import com.poeicgi.nikosmileweb.dao.ISecurityUserCrudRepository;
import com.poeicgi.nikosmileweb.dao.IUserCrudRepository;
import com.poeicgi.nikosmileweb.models.Project;
import com.poeicgi.nikosmileweb.models.User;
import com.poeicgi.nikosmileweb.models.security.SecurityRole;
import com.poeicgi.nikosmileweb.models.security.SecurityUser;

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
	
	@Autowired
	private ISecurityUserCrudRepository secuCrud;
	
	@Autowired
	private ISecurityRoleCrudRepository roleCrud;

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
	
	@RequestMapping(path = "/chooseUser", method = RequestMethod.GET)
	public String chooseUserView(Model model, @RequestParam(value = "userRegistration", defaultValue = "") String userRegistration) {

		List<User> users = null;
		if (!userRegistration.equals(null) && !userRegistration.equals("")) {
			users = userCrud.findUsersByRegistration("%" + userRegistration + "%");
		}
		
		model.addAttribute("users", users);
		
		model.addAttribute("userRegistration", userRegistration);

		return "admin/chooseUser";
	}

	@RequestMapping(path = "/{projectId}/members", method = RequestMethod.GET)
	public String membersView(Model model, 
			@PathVariable(value = "projectId") String projectId,
			@RequestParam(value = "projectName", defaultValue = "") String projectName,
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
	
	@RequestMapping(path = "/{userId}/roles", method = RequestMethod.GET)
	public String rolesView(Model model, 
			@PathVariable(value = "userId") String userId,
			@RequestParam(value = "userRegistration", defaultValue = "") String userRegistration) {
		
		Set<SecurityRole> roles = null;

		roles = (secuCrud.findOne(Long.parseLong(userId))).getRoles();

		model.addAttribute("userId", userId);
		
		model.addAttribute("userRegistration", userRegistration);

		model.addAttribute("roles", roles);

		return "admin/roles";
	}
	
	@RequestMapping(path = "/{projectId}/members/{userId}/remove", method = RequestMethod.POST)
	public String memberRemove(Model model, 
			@PathVariable(value = "projectId") String projectId,
			@PathVariable(value = "userId") String userId,
			@RequestParam(value = "projectName") String projectName) {

		Project project = projectCrud.findOne(Long.parseLong(projectId));
		User user = userCrud.findOne(Long.parseLong(userId));
		user.getProjects().remove(project);
		project.getTeam().remove(user);
		
		userCrud.save(user);
		projectCrud.save(project);

		return REDIRECT + BASE_URL + "/" + projectId +"/members?projectName="+projectName;
	}
	
	@RequestMapping(path = "/{userId}/roles/{roleId}/remove", method = RequestMethod.POST)
	public String roleRemove(Model model, 
			@PathVariable(value = "roleId") String roleId,
			@PathVariable(value = "userId") String userId,
			@RequestParam(value = "userRegistration") String userRegistration) {

		SecurityUser secu = secuCrud.findOne(Long.parseLong(userId));
		SecurityRole role = roleCrud.findOne(Long.parseLong(roleId));
		secu.getRoles().remove(role);
		role.getSecurities().remove(secu);
		
		secuCrud.save(secu);
		roleCrud.save(role);

		return REDIRECT + BASE_URL + "/" + userId +"/roles?userRegistration="+userRegistration;
	}
	
	@RequestMapping(path = "/{projectId}/members/{userId}/add", method = RequestMethod.POST)
	public String memberAdd(Model model, 
			@PathVariable(value = "projectId") String projectId,
			@PathVariable(value = "userId") String userId,
			@RequestParam(value = "projectName") String projectName) {

		Project project = projectCrud.findOne(Long.parseLong(projectId));
		User user = userCrud.findOne(Long.parseLong(userId));
		user.getProjects().add(project);
		project.getTeam().add(user);
		
		userCrud.save(user);
		projectCrud.save(project);

		return REDIRECT + BASE_URL + "/" + projectId +"/members?projectName="+projectName;
	}
	
	@RequestMapping(path = "/{userId}/roles/{roleId}/add", method = RequestMethod.POST)
	public String roleAdd(Model model, 
			@PathVariable(value = "roleId") String roleId,
			@PathVariable(value = "userId") String userId,
			@RequestParam(value = "userRegistration") String userRegistration) {

		SecurityUser secu = secuCrud.findOne(Long.parseLong(userId));
		SecurityRole role = roleCrud.findOne(Long.parseLong(roleId));
		secu.getRoles().add(role);
		role.getSecurities().add(secu);
		
		secuCrud.save(secu);
		roleCrud.save(role);

		return REDIRECT + BASE_URL + "/" + userId +"/roles?userRegistration="+userRegistration;
	}
	
}
