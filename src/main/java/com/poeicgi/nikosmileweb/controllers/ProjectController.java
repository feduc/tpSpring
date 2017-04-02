package com.poeicgi.nikosmileweb.controllers;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.poeicgi.nikosmileweb.controllers.base.view.ViewBaseController;
import com.poeicgi.nikosmileweb.controllers.security.SecurityController;
import com.poeicgi.nikosmileweb.dao.IProjectCrudRepository;
import com.poeicgi.nikosmileweb.dao.ISecurityRoleCrudRepository;
import com.poeicgi.nikosmileweb.dao.ISecurityUserCrudRepository;
import com.poeicgi.nikosmileweb.models.Project;
import com.poeicgi.nikosmileweb.models.User;
import com.poeicgi.nikosmileweb.models.security.SecurityUser;

@Controller
@RequestMapping(path = ProjectController.BASE_URL)
public class ProjectController extends ViewBaseController<Project>{

	public final static String BASE_URL = "/project";

	@Autowired
	private IProjectCrudRepository projectCrud;


	@Autowired
	private SecurityController securityController;

	@Autowired
	private ISecurityUserCrudRepository secuCrud;

	@Autowired
	private ISecurityRoleCrudRepository roleCrud;

	public ProjectController() {
		super(Project.class,BASE_URL);
	}

	@Secured("ROLE_USER")
	@RequestMapping(path = "/choose", method = RequestMethod.GET)
	public String chooseView(Model model, @ModelAttribute("date") Long date, @RequestParam(value = "projectName", defaultValue = "") String projectName) {

		List<Project> projects = null;
		if (!projectName.equals(null) && !projectName.equals("")) {
			projects = projectCrud.findProjectsByName("%" + projectName + "%");
		}
		model.addAttribute("projectName", projectName);
		model.addAttribute("projects", projects);

		User child = securityController.getConnectedUser();
		SecurityUser secu = secuCrud.findOne(child.getId());
		Boolean visu= false;
		List<String> roles = roleCrud.getRolesForSecurityUser(secu);
		if (roles.contains("ROLE_VISU")) {
			visu = true;
		}

		model.addAttribute("visu", visu);

		return "visu/choose_proj";
	}


	@Secured("ROLE_USER")
	@RequestMapping(path = "/HiddenAnonymous", method = RequestMethod.POST)
	public String projectParam(Model model, @ModelAttribute("projectName") String projectName,
	@RequestParam(value = "isAnonymous",  required = false) String isAnonymous,
	@RequestParam(value = "isHidden",  required = false) String isHidden){

		Project project = projectCrud.findExactProjectByName(projectName);

		if(isAnonymous!=null){
		project.setIsAnonymous(true);
		}
		else{
		project.setIsAnonymous(false);
		}

		if (isHidden!=null){
		project.setIsHidden(true);
		}
		else{
		project.setIsHidden(false);
		}

		projectCrud.save(project);
		updateItem(project);

		return REDIRECT + UserController.BASE_URL + "/resume";
	}

}
