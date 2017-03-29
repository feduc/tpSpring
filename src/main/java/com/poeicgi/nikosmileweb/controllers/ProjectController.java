package com.poeicgi.nikosmileweb.controllers;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	// value is the address to enter in the browser to launch index(), it can be
	// more than one when writing value = {"/path1", "/path2"}
	@RequestMapping(value = "/Project")
	public String index() {
		return "toto";
		// return "toto" works because toto.ftl is directly in templates, if it
		// was in templates.pages return would have to be equal to "pages/toto"
	}

	@Autowired
	private IProjectCrudRepository projectCrud;

	public ProjectController() {
		super(Project.class,BASE_URL);
	}

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
