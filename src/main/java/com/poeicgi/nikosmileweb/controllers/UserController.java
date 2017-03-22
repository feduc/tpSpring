package com.poeicgi.nikosmileweb.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poeicgi.nikosmileweb.controllers.base.view.ViewBaseController;
import com.poeicgi.nikosmileweb.dao.IMoodCrudRepository;
import com.poeicgi.nikosmileweb.dao.IUserCrudRepository;
import com.poeicgi.nikosmileweb.models.ChangeDate;
import com.poeicgi.nikosmileweb.models.User;
import com.poeicgi.nikosmileweb.utils.DumpFields;

@Controller
@RequestMapping(path = UserController.BASE_URL)
public class UserController extends ViewBaseController<User>{

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
	private IUserCrudRepository userCrud;

	@Autowired
	private IMoodCrudRepository moodCrud;

	public UserController() {
		super(User.class,BASE_URL);

	}

	//vers la page de resume de l'user pour visu globale
	@RequestMapping(path = "/resume", method = RequestMethod.GET)
	public String resumeView(Model model, @ModelAttribute("child")User child){

			Date date = new Date();
			model.addAttribute("date", date);

			GregorianCalendar todayTest = new GregorianCalendar();

			todayTest.setTime(date);

			todayTest.set(GregorianCalendar.HOUR_OF_DAY, 00);
			todayTest.set(GregorianCalendar.MINUTE, 00);
			todayTest.set(GregorianCalendar.SECOND, 00);
			todayTest.set(GregorianCalendar.MILLISECOND, 00);

			Date today = new Date(todayTest.getTimeInMillis());

			int satisfaction = moodCrud.findSatisfaction(child, today);
			model.addAttribute("smile", satisfaction);

//			String smile ="";
//
//			if (satisfaction == -1)
//			{
//				smile = "niko-rouge.png";
//			}
//
//			else if (satisfaction == 0)
//			{
//				smile = "niko-jaune.png";
//			}
//
//			else if (satisfaction == 1)
//			{
//				smile = "niko-rouge.png";
//			}
//			model.addAttribute("smile", smile);

			return "user/resume";
	}
}
