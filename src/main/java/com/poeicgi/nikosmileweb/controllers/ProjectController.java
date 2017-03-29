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
import com.poeicgi.nikosmileweb.dao.IProjectCrudRepository;
import com.poeicgi.nikosmileweb.models.Project;
import com.poeicgi.nikosmileweb.models.User;
import com.poeicgi.nikosmileweb.models.security.SecurityUser;

@Controller
@RequestMapping(path = ProjectController.BASE_URL)
public class ProjectController extends ViewBaseController<Project>{

	public final static String BASE_URL = "/project";

	@Autowired
	private IProjectCrudRepository projectCrud;

	public ProjectController() {
		super(Project.class,BASE_URL);
	}

	@Secured("ROLE_VISU")
	@RequestMapping(path = "/choose", method = RequestMethod.GET)
	public String chooseView(Model model, @ModelAttribute("date") Long date, @RequestParam(value = "projectName", defaultValue = "") String projectName) {

		List<Project> projects = null;
		if (!projectName.equals(null) && !projectName.equals("")) {
			projects = projectCrud.findProjectsByName("%" + projectName + "%");
		}

		model.addAttribute("date", date);
		model.addAttribute("projects", projects);

		model.addAttribute("projectName", projectName);

		return "visu/choose_proj";
	}
}
