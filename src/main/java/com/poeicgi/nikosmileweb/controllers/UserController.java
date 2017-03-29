package com.poeicgi.nikosmileweb.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.poeicgi.nikosmileweb.controllers.base.view.ViewBaseController;
import com.poeicgi.nikosmileweb.controllers.security.SecurityController;
import com.poeicgi.nikosmileweb.dao.IMoodCrudRepository;
import com.poeicgi.nikosmileweb.dao.IProjectCrudRepository;
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

	// value is the address to enter in the browser to launch index(), it can be
	// more than one when writing value = {"/path1", "/path2"}
	@RequestMapping(value = "/home")
	public String index() {
		return "toto";
		// return "toto" works because toto.ftl is directly in templates, if it
		// was in templates.pages return would have to be equal to "pages/toto"
	}

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

	public UserController() {
		super(User.class, BASE_URL);

	}

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

		return "user/resume";
	}
	
	@Override
	@RequestMapping(path = "/create/", method = RequestMethod.GET)
	public String createView(Model model){

		String pageName = "Create a User";

		model.addAttribute("fields", DumpFields.createContentsEmpty(super.getClazz()).getMyFields());
		model.addAttribute("page", pageName);

		return "admin/createUser";
	}
	
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
}
