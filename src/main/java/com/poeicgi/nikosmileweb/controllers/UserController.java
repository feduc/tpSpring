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
	private IMoodCrudRepository moodCrud;

	@Autowired
	private IUserCrudRepository userCrud;

	public UserController() {
		super(User.class,BASE_URL);

	}

	//vers la page de resume de l'user pour visu globale
//	@RequestMapping(path = "/resume", method = RequestMethod.GET)
//	public String resumeView(Model model, @ModelAttribute("child")User child){
//
//			Date date = new Date();
//
//			GregorianCalendar todayTest = new GregorianCalendar();
//
//			todayTest.setTime(date);
//
//			todayTest.set(GregorianCalendar.HOUR_OF_DAY, 00);
//			todayTest.set(GregorianCalendar.MINUTE, 00);
//			todayTest.set(GregorianCalendar.SECOND, 00);
//			todayTest.set(GregorianCalendar.MILLISECOND, 00);
//			Date today = new Date(todayTest.getTimeInMillis());
//
//			int satisfaction= moodCrud.findLastSatisf(child, today);
//			String image = null;
//
//			if (satisfaction == -1)
//			{
//				image = "/img/niko-rouge.png";
//			}
//
//			else if (satisfaction == 0)
//			{
//				image = "/img/niko-jaune.png";
//			}
//
//			else if (satisfaction == 1)
//			{
//				image = "/img/niko-vert.png";
//			}
//
//			model.addAttribute("image", image);
//
//			return "user/resume";
//	}
}
