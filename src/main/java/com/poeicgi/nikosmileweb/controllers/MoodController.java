package com.poeicgi.nikosmileweb.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
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
import com.poeicgi.nikosmileweb.dao.IUserCrudRepository;
import com.poeicgi.nikosmileweb.models.Mood;
import com.poeicgi.nikosmileweb.models.User;
import com.poeicgi.nikosmileweb.utils.DumpFields;

@Controller
@RequestMapping(path = MoodController.BASE_URL)
public class MoodController extends ViewBaseController<Mood> {

	public final static String BASE_URL = "/mood";

	// value is the address to enter in the browser to launch index(), it can be
	// more than one when writing value = {"/path1", "/path2"}
	@RequestMapping(path = "/vote", method = RequestMethod.GET)
	public String voteView(Model model, @ModelAttribute("child") final User child) {

		Long id = 0L;

		Date date = new Date();
		model.addAttribute("date", date);

		GregorianCalendar todayTest = new GregorianCalendar();
		todayTest.setTime(date);
		todayTest.set(GregorianCalendar.HOUR_OF_DAY, 00);
		todayTest.set(GregorianCalendar.MINUTE, 00);
		todayTest.set(GregorianCalendar.SECOND, 00);
		todayTest.set(GregorianCalendar.MILLISECOND, 00);
		Date today = new Date(todayTest.getTimeInMillis());

		GregorianCalendar yesterdayTest = todayTest;
		yesterdayTest.add(GregorianCalendar.DATE, -1);
		Date yesterday = new Date(yesterdayTest.getTimeInMillis());

		Date lastVote = moodCrud.findLastVote(child);
		// lastMood.getVoteDate();
		GregorianCalendar lastVoteTest = new GregorianCalendar();

		Date voteDate;

		if (lastVote == null) {
			voteDate = yesterday;
		} else {

			lastVoteTest.setTime(lastVote);

			if (todayTest.getTimeInMillis() == lastVoteTest.getTimeInMillis()) {
				voteDate = today;

				id = moodCrud.findLastVoteID(child, lastVote);
				

			} else if (yesterdayTest.getTimeInMillis() == lastVoteTest.getTimeInMillis()) {
				voteDate = today;
			} else {
				voteDate = yesterday;
			}
		}

		model.addAttribute("voteDate", voteDate);
		model.addAttribute("MoodId", id);
		model.addAttribute("child", child);

		return "mood/vote";
		// return "toto" works because toto.ftl is directly in templates, if it
		// was in templates.pages return would have to be equal to "pages/toto"
	}

	@RequestMapping(path = "/create/done", method = RequestMethod.POST)
	public String create(Model model, @ModelAttribute Mood item, @ModelAttribute User child,
			@ModelAttribute("MoodID") String MoodID) {
		Map<String, Object> map = model.asMap();

		Long id = Long.parseLong(MoodID);

		item.setUser((User) map.get("user"));
		
		item.setId(id);
		
		User user = userCont.getItem(item.getUser().getId());
		user.getMoods().add(item);
		userCont.updateItem(user);
		item.setUser(user);

		insertItem(item);

		return REDIRECT + UserController.BASE_URL + "/home";
	}

	@Autowired
	private IMoodCrudRepository moodCrud;

	@Autowired
	private UserController userCont;

	public MoodController() {
		super(Mood.class, BASE_URL);
	}
}
