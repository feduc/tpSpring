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

import com.poeicgi.nikosmileweb.controllers.base.view.ViewBaseController;
import com.poeicgi.nikosmileweb.controllers.security.SecurityController;
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
	private SecurityController securityController;

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

	@Secured("ROLE_ADMIN")
	@RequestMapping(path = "/vote/", method = RequestMethod.GET)
	public String adminVoteGet(Model model) {

		return REDIRECT + MoodController.BASE_URL + "/vote";

	}

	@Secured({ "ROLE_ADMIN", "ROLE_MODO" })
	@RequestMapping(path = "/choose", method = RequestMethod.GET)
	public String chooseView(Model model, @RequestParam(value = "projectName", defaultValue = "") String projectName) {

		// creation d'une liste de projets en remplissage du resultat de la
		// requete
		List<Project> projects = null;
		if (!projectName.equals(null) && !projectName.equals("")) {
			projects = projectCrud.findProjectsByName("%" + projectName + "%");
		}

		// bloc de mise à jour du navigateur pour modo
		User child = securityController.getConnectedUser();
		SecurityUser secu = secuCrud.findOne(child.getId());
		Boolean admin = false;
		List<String> roles = roleCrud.getRolesForSecurityUser(secu);
		if (roles.contains("ROLE_ADMIN")) {
			admin = true;
		}
		//

		model.addAttribute("admin", admin);
		model.addAttribute("projects", projects);
		model.addAttribute("projectName", projectName);

		return "admin/choose";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(path = "/chooseUser", method = RequestMethod.GET)
	public String chooseUserView(Model model,
			@RequestParam(value = "userRegistration", defaultValue = "") String userRegistration,
			@RequestParam(value = "action") String action) {

		// creation d'une liste de users en remplissage du resultat de la
		// requete
		List<User> users = null;
		if (!userRegistration.equals(null) && !userRegistration.equals("")) {
			users = userCrud.findUsersByRegistration("%" + userRegistration + "%");
		}

		// bloc de mise à jour du navigateur pour modo
		User child = securityController.getConnectedUser();
		SecurityUser secu = secuCrud.findOne(child.getId());
		Boolean admin = false;
		List<String> roles = roleCrud.getRolesForSecurityUser(secu);
		if (roles.contains("ROLE_ADMIN")) {
			admin = true;
		}
		model.addAttribute("admin", admin);
		model.addAttribute("action",action);
		model.addAttribute("users", users);
		model.addAttribute("userRegistration", userRegistration);
		return "admin/chooseUser";
	}

	@Secured({ "ROLE_ADMIN", "ROLE_MODO" })
	@RequestMapping(path = "/{projectId}/members", method = RequestMethod.GET)
	public String membersView(Model model, @PathVariable(value = "projectId") String projectId,
			@RequestParam(value = "projectName", defaultValue = "") String projectName,
			@RequestParam(value = "userRegistration", defaultValue = "") String userRegistration) {

		// apres renseignement de l'id projet selectionné dans choose
		// remplissage de la liste user correspondant
		// aux caracteres rentres
		List<User> users = null;
		if (!userRegistration.equals(null) && !userRegistration.equals("")) {
			users = userCrud.findUsersByRegistration("%" + userRegistration + "%");
		}

		// renseignement de la liste members de ce projet
		List<User> members = null;
		members = userCrud.findMembersByProject(projectName);

		// bloc de mise à jour du navigateur pour modo
		User child = securityController.getConnectedUser();
		SecurityUser secu = secuCrud.findOne(child.getId());
		Boolean admin = false;
		List<String> roles = roleCrud.getRolesForSecurityUser(secu);
		if (roles.contains("ROLE_ADMIN")) {
			admin = true;
		}
		model.addAttribute("admin", admin);

		model.addAttribute("projectId", projectId);
		model.addAttribute("projectName", projectName);
		model.addAttribute("userRegistration", userRegistration);
		model.addAttribute("members", members);
		model.addAttribute("users", users);

		// affichage des infos dans le ftl appellé
		return "admin/members";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(path = "/{userId}/roles", method = RequestMethod.GET)
	public String rolesView(Model model, @PathVariable(value = "userId") String userId,
			@RequestParam(value = "userRegistration", defaultValue = "") String userRegistration) {

		// creation d'une liste nulle
		Set<SecurityRole> roles = null;

		// auquel on vient afficher les roles du user
		roles = (secuCrud.findOne(Long.parseLong(userId))).getRoles();

		// bloc de mise à jour du navigateur pour modo
		User child = securityController.getConnectedUser();
		SecurityUser secu = secuCrud.findOne(child.getId());
		Boolean admin = false;
		List<String> roles2 = roleCrud.getRolesForSecurityUser(secu);
		if (roles2.contains("ROLE_ADMIN")) {
			admin = true;
		}
		model.addAttribute("admin", admin);
		//

		model.addAttribute("userId", userId);
		model.addAttribute("userRegistration", userRegistration);
		model.addAttribute("roles", roles);

		return "admin/roles";
	}

	@Secured({ "ROLE_ADMIN", "ROLE_MODO" })
	@RequestMapping(path = "/{projectId}/members/{userId}/remove", method = RequestMethod.POST)
	public String memberRemove(Model model, @PathVariable(value = "projectId") String projectId,
			@PathVariable(value = "userId") String userId, @RequestParam(value = "projectName") String projectName) {

		// recherche du projet en fonction de l'id
		Project project = projectCrud.findOne(Long.parseLong(projectId));
		// recherche du projet en fonction de l'id
		User user = userCrud.findOne(Long.parseLong(userId));
		// que l'on retire à la liste des projets associe au user
		user.getProjects().remove(project);
		// de même dans l'autre sens
		project.getTeam().remove(user);
		// sauvegarde des deux objets
		userCrud.save(user);
		projectCrud.save(project);

		return REDIRECT + BASE_URL + "/" + projectId + "/members?projectName=" + projectName;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(path = "/{userId}/roles/{roleId}/remove", method = RequestMethod.POST)
	public String roleRemove(Model model, @PathVariable(value = "roleId") String roleId,
			@PathVariable(value = "userId") String userId,
			@RequestParam(value = "userRegistration") String userRegistration) {

		SecurityUser secu = secuCrud.findOne(Long.parseLong(userId));
		SecurityRole role = roleCrud.findOne(Long.parseLong(roleId));
		secu.getRoles().remove(role);
		role.getSecurities().remove(secu);
		secuCrud.save(secu);
		roleCrud.save(role);

		return REDIRECT + BASE_URL + "/" + userId + "/roles?userRegistration=" + userRegistration;
	}

	@Secured({ "ROLE_ADMIN", "ROLE_MODO" })
	@RequestMapping(path = "/{projectId}/members/{userId}/add", method = RequestMethod.POST)
	public String memberAdd(Model model, @PathVariable(value = "projectId") String projectId,
			@PathVariable(value = "userId") String userId, @RequestParam(value = "projectName") String projectName) {

		Project project = projectCrud.findOne(Long.parseLong(projectId));
		User user = userCrud.findOne(Long.parseLong(userId));
		user.getProjects().add(project);
		project.getTeam().add(user);

		userCrud.save(user);
		projectCrud.save(project);

		return REDIRECT + BASE_URL + "/" + projectId + "/members?projectName=" + projectName;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(path = "/{userId}/roles/{roleId}/add", method = RequestMethod.POST)
	public String roleAdd(Model model, @PathVariable(value = "roleId") String roleId,
			@PathVariable(value = "userId") String userId,
			@RequestParam(value = "userRegistration") String userRegistration) {

		SecurityUser secu = secuCrud.findOne(Long.parseLong(userId));
		SecurityRole role = roleCrud.findOne(Long.parseLong(roleId));

		secu.getRoles().add(role);
		role.getSecurities().add(secu);

		secuCrud.save(secu);
		roleCrud.save(role);

		return REDIRECT + BASE_URL + "/" + userId + "/roles?userRegistration=" + userRegistration;
	}

}
