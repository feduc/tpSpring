package com.poeicgi.nikosmileweb.controllers;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poeicgi.nikosmileweb.controllers.base.view.ViewBaseController;
import com.poeicgi.nikosmileweb.dao.IMoodCrudRepository;
import com.poeicgi.nikosmileweb.models.ChangeDate;
import com.poeicgi.nikosmileweb.models.Mood;
import com.poeicgi.nikosmileweb.models.User;

@Controller
@RequestMapping(path = MoodController.BASE_URL)
public class MoodController extends ViewBaseController<Mood> {

	@Autowired
	private IMoodCrudRepository moodCrud;

	@Autowired
	private UserController userCont;

	@Autowired
	private ChangeDateController changeCont;

	public MoodController() {
		super(Mood.class, BASE_URL);
	}

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

		GregorianCalendar yesterdayTest = new GregorianCalendar();
		yesterdayTest.setTimeInMillis(todayTest.getTimeInMillis());
		yesterdayTest.add(GregorianCalendar.DATE, -1);
		Date yesterday = new Date(yesterdayTest.getTimeInMillis());

		Date lastVote = moodCrud.findLastVote(child);
		GregorianCalendar lastVoteTest = new GregorianCalendar();

		Date voteDate;

		if (lastVote == null) {
			voteDate = yesterday;
		} else {

			lastVoteTest.setTime(lastVote);

			if (todayTest.get(GregorianCalendar.YEAR) == lastVoteTest.get(GregorianCalendar.YEAR) && 
					todayTest.get(GregorianCalendar.MONTH) == lastVoteTest.get(GregorianCalendar.MONTH) && 
					todayTest.get(GregorianCalendar.DAY_OF_MONTH) == lastVoteTest.get(GregorianCalendar.DAY_OF_MONTH)) {
				voteDate = today;

				id = moodCrud.findLastVoteID(child, lastVote);

			} else if (yesterdayTest.get(GregorianCalendar.YEAR) == lastVoteTest.get(GregorianCalendar.YEAR) && 
					yesterdayTest.get(GregorianCalendar.MONTH) == lastVoteTest.get(GregorianCalendar.MONTH) && 
					yesterdayTest.get(GregorianCalendar.DAY_OF_MONTH)==lastVoteTest.get(GregorianCalendar.DAY_OF_MONTH)) {
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

		if (item.getId() != 0) {
			ChangeDate changeDate = new ChangeDate();
			changeDate.setChangeDate(item.getLogDate());
			changeDate.setMood(item);
			item.setLogDate(getItem(id).getLogDate());
			item.getChangeDates().add(changeDate);
			changeCont.insertItem(changeDate);
		}

		User user = userCont.getItem(item.getUser().getId());
		user.getMoods().add(item);
		userCont.updateItem(user);
		item.setUser(user);

		insertItem(item);
		
		Set<Mood> list = user.getMoods();
		list.add(item);
		user.setMoods(list);
		userCont.updateItem(user);

		return REDIRECT + UserController.BASE_URL + "/resume";
	}

	@RequestMapping(path = "/week", method = RequestMethod.GET)
	public String weekView(Model model) {

		Date sd = new Date();
		Calendar cd = Calendar.getInstance();
		cd.setTime(sd);

		cd.set(Calendar.HOUR_OF_DAY, 00);
		cd.set(Calendar.MINUTE, 00);
		cd.set(Calendar.SECOND, 00);
		cd.set(Calendar.MILLISECOND, 00);

		String jour = "truc";
		Integer debutsemaine = null;
		Integer finsemaine = null;
		Integer mois = null;

		if (cd.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
			jour = "Lundi";
			debutsemaine = cd.get(Calendar.DAY_OF_MONTH);
			sd = new Date(cd.getTimeInMillis());
			cd.add(Calendar.DATE, 4);
			finsemaine = cd.get(Calendar.DAY_OF_MONTH);
		}

		else if (cd.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
			jour = "Mardi";
			cd.add(Calendar.DATE, -1);
			debutsemaine = cd.get(Calendar.DAY_OF_MONTH);
			sd = new Date(cd.getTimeInMillis());
			cd.add(Calendar.DATE, 4);
			finsemaine = cd.get(Calendar.DAY_OF_MONTH);
		}

		else if (cd.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
			jour = "Mercredi";
			cd.add(Calendar.DATE, -2);
			debutsemaine = cd.get(Calendar.DAY_OF_MONTH);
			sd = new Date(cd.getTimeInMillis());
			cd.add(Calendar.DATE, 4);
			finsemaine = cd.get(Calendar.DAY_OF_MONTH);
		}

		else if (cd.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
			jour = "Jeudi";
			cd.add(Calendar.DATE, -3);
			debutsemaine = cd.get(Calendar.DAY_OF_MONTH);
			sd = new Date(cd.getTimeInMillis());
			cd.add(Calendar.DATE, 4);
			finsemaine = cd.get(Calendar.DAY_OF_MONTH);
		}

		else if (cd.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
			jour = "Vendredi";
			cd.add(Calendar.DATE, -4);
			debutsemaine = cd.get(Calendar.DAY_OF_MONTH);
			sd = new Date(cd.getTimeInMillis());
			cd.add(Calendar.DATE, 4);
			finsemaine = cd.get(Calendar.DAY_OF_MONTH);

		}

		else if (cd.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
			jour = "Vendredi";
			cd.add(Calendar.DATE, -5);
			debutsemaine = cd.get(Calendar.DAY_OF_MONTH);
			sd = new Date(cd.getTimeInMillis());
			cd.add(Calendar.DATE, 4);
			finsemaine = cd.get(Calendar.DAY_OF_MONTH);
		}

		else if (cd.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			jour = "Vendredi";
			cd.add(Calendar.DATE, -6);
			debutsemaine = cd.get(Calendar.DAY_OF_MONTH);
			sd = new Date(cd.getTimeInMillis());
			cd.add(Calendar.DATE, 4);
			finsemaine = cd.get(Calendar.DAY_OF_MONTH);

		}

		mois = cd.get(Calendar.MONTH);
		model.addAttribute("date", jour);
		model.addAttribute("debutsemaine", debutsemaine);
		model.addAttribute("finsemaine", finsemaine);
		model.addAttribute("mois", String.format("%02d", mois + 1));

		String projectName = "Projet1";
		int nbChoice = 3;

		for (int j = 1; j <= 5; j++) {
			for (int i = 0; i < nbChoice; i++) {
				String nom = "jour" + j + "satis" + i;
				int satis = i - (nbChoice / 2);
				model.addAttribute(nom, moodCrud.countMoodsBySatisfactionForSummary(projectName, sd, satis));
			}
			cd.setTime(sd);
			cd.add(Calendar.DATE, 1);
			sd.setTime(cd.getTimeInMillis());
		}

		return "mood/week";
	}
}
