package com.poeicgi.nikosmileweb.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.poeicgi.nikosmileweb.controllers.base.view.ViewBaseController;
import com.poeicgi.nikosmileweb.controllers.security.SecurityController;
import com.poeicgi.nikosmileweb.dao.IMoodCrudRepository;
import com.poeicgi.nikosmileweb.dao.IProjectCrudRepository;
import com.poeicgi.nikosmileweb.dao.ISecurityRoleCrudRepository;
import com.poeicgi.nikosmileweb.dao.ISecurityUserCrudRepository;
import com.poeicgi.nikosmileweb.dao.IUserCrudRepository;
import com.poeicgi.nikosmileweb.models.ChangeDate;
import com.poeicgi.nikosmileweb.models.Mood;
import com.poeicgi.nikosmileweb.models.Project;
import com.poeicgi.nikosmileweb.models.User;
import com.poeicgi.nikosmileweb.models.security.SecurityUser;
import com.poeicgi.nikosmileweb.utils.DumpFields;

@Controller
@RequestMapping(path = UserController.BASE_URL)
public class UserController extends ViewBaseController<User> {

	public final static String BASE_URL = "/user";

	@Autowired
	private SecurityController securityController;

	@Autowired
	ISecurityRoleCrudRepository securityRoleCrud;

	@Autowired
	private IUserCrudRepository userCrud;

	@Autowired
	private IProjectCrudRepository projectCrud;

	@Autowired
	private IMoodCrudRepository moodCrud;

	@Autowired
	private ISecurityUserCrudRepository secuCrud;

	public UserController() {
		super(User.class, BASE_URL);

	}
	@Secured("ROLE_USER")
	// vers la page de resume de l'user pour visu globale
	@RequestMapping(path = "/resume", method = RequestMethod.GET)
	public String resumeView(Model model) {

		GregorianCalendar todayTest = new GregorianCalendar();

		todayTest.setTime(new Date());

		todayTest.set(GregorianCalendar.HOUR_OF_DAY, 00);
		todayTest.set(GregorianCalendar.MINUTE, 00);
		todayTest.set(GregorianCalendar.SECOND, 00);
		todayTest.set(GregorianCalendar.MILLISECOND, 00);

		Date today = new Date(todayTest.getTimeInMillis());

		User user = securityController.getConnectedUser();


		model.addAttribute("date", today.getTime());
		Integer satisfaction = null;
		if (moodCrud.findLastVote(user).compareTo(today)<0){ satisfaction = 40;}
		else{satisfaction=moodCrud.findSatisfaction(user, today);}
		model.addAttribute("smile", satisfaction);

		List<String> actualProjectsNames = projectCrud.findActualProjectsByUser(user, today);
		ArrayList<Map<String,Object>> actualProjectsInfo = new ArrayList<Map<String,Object>>();

		int i=0;

		for (String projectName : actualProjectsNames) {
			actualProjectsInfo.add(new HashMap<String,Object>());
			(actualProjectsInfo.get(i)).put("name", projectName);
			(actualProjectsInfo.get(i)).put("Content", moodCrud.countMoodsBySatisfactionForSummary(projectName, today, 1));
			(actualProjectsInfo.get(i)).put("Normal", moodCrud.countMoodsBySatisfactionForSummary(projectName, today, 0));
			(actualProjectsInfo.get(i)).put("Mecontent", moodCrud.countMoodsBySatisfactionForSummary(projectName, today, -1));
			i++;
		}

		List<String> oldProjectsNames = projectCrud.findOldProjectsByUser(user, today);

		model.addAttribute("actualProjectsInfo", actualProjectsInfo);
		model.addAttribute("actualProjectsNames", actualProjectsNames);
		model.addAttribute("oldProjectsNames", oldProjectsNames);

		User child = securityController.getConnectedUser();

		SecurityUser secu = secuCrud.findOne(child.getId());

		List<String> roles = securityRoleCrud.getRolesForSecurityUser(secu);

		Boolean admin=false;

		if (roles.contains("ROLE_ADMIN")) {
			admin = true;
		}
		Boolean visu = false;

		if (roles.contains("ROLE_VISU")) {
			visu = true;
		}

		Boolean modo = false;

		if (roles.contains("ROLE_MODO")) {
			modo = true;
		}


		model.addAttribute("admin", admin);
		model.addAttribute("visu", visu);
		model.addAttribute("modo", modo);
		return "user/resume";
	}
	@Secured("ROLE_ADMIN")
	@Override
	@RequestMapping(path = "/create/", method = RequestMethod.GET)
	public String createView(Model model){

		//bloc de mise à jour du navigateur pour modo
		User child = securityController.getConnectedUser();
		SecurityUser secu = secuCrud.findOne(child.getId());
		Boolean admin= false;
		List<String> roles = securityRoleCrud.getRolesForSecurityUser(secu);
		if (roles.contains("ROLE_ADMIN")) {
			admin = true;
		}
		model.addAttribute("admin", admin);
		//

		String pageName = "Create a User";

		model.addAttribute("fields", DumpFields.createContentsEmpty(super.getClazz()).getMyFields());
		model.addAttribute("page", pageName);

		return "admin/createUser";
	}
	@Secured("ROLE_ADMIN")
	@RequestMapping(path = "/create/done", method = RequestMethod.POST)
	public String create(Model model, @ModelAttribute User item, @ModelAttribute SecurityUser security) {

		insertItem(item);

		security.setId(item.getId());
		security.setEnable(true);

		secuCrud.save(security);

		item.setSecurity(security);

		updateItem(item);

		return REDIRECT + UserController.BASE_URL + "/resume";
	}

	@Secured("ROLE_USER")
	@RequestMapping(path = "/parameters", method = RequestMethod.GET)
	public String parameters(Model model) {


 			return "user/parameters";
	}

	@Secured("ROLE_USER")
	@RequestMapping(path = "/parameters/do", method = RequestMethod.POST)
	public String parameter(Model model,
			@RequestParam(value = "oldPassword", defaultValue = "") String oldPassword,
			@RequestParam(value = "newPassword1", defaultValue = "") String newPassword1,
			@RequestParam(value = "newPassword2", defaultValue = "") String newPassword2) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		org.springframework.security.core.userdetails.User userDetail = (org.springframework.security.core.userdetails.User) auth.getPrincipal();

		String login = userDetail.getUsername();
		SecurityUser security = secuCrud.findByLogin(login);

		String password = security.getPassword();

		Boolean changePass = false;

		model.addAttribute("password", password);

		if (oldPassword.equals(password))
		{
		security.setPassword(newPassword1);
		secuCrud.save(security);
		changePass = true;
		}

		model.addAttribute("changePass", changePass);

 		return "user/parameters";
	}
}
