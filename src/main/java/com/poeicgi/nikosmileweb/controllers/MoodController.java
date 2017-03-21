package com.poeicgi.nikosmileweb.controllers;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poeicgi.nikosmileweb.controllers.base.view.ViewBaseController;
import com.poeicgi.nikosmileweb.dao.IMoodCrudRepository;
import com.poeicgi.nikosmileweb.models.Mood;
import com.poeicgi.nikosmileweb.models.User;

@Controller
@RequestMapping(path = MoodController.BASE_URL)
public class MoodController extends ViewBaseController<Mood> {

	public final static String BASE_URL = "/mood";

	// value is the address to enter in the browser to launch index(), it can be
	// more than one when writing value = {"/path1", "/path2"}
	@RequestMapping(path = "/vote", method = RequestMethod.GET)
	public String voteView(Model model, @ModelAttribute("child") final User child) {


		Long id = 0L;
		//creation d'une date d'aujourd'hui
		Date date = new Date();
		//que l'on ajoute dans le modele
		model.addAttribute("date", date);

		//on cree un gregoriancalendat
		GregorianCalendar todayTest = new GregorianCalendar();
		//auquel on attribue la date
		todayTest.setTime(date);
		//on met a 0 l'heure
		todayTest.set(GregorianCalendar.HOUR_OF_DAY, 00);
		todayTest.set(GregorianCalendar.MINUTE, 00);
		todayTest.set(GregorianCalendar.SECOND, 00);
		todayTest.set(GregorianCalendar.MILLISECOND, 00);
		//que l'on recupere en millisecond > Date d'aujourd'hui en millis
		Date today = new Date(todayTest.getTimeInMillis());

		//gregorian d'hier
		GregorianCalendar yesterdayTest = todayTest;
		// auquel on attribue date -1
		yesterdayTest.add(GregorianCalendar.DATE, -1);
		// en millisecond> Date d'hier en millis
		Date yesterday = new Date(yesterdayTest.getTimeInMillis());

		//on recupere la date du dernier vote
		Date lastVote = moodCrud.findLastVote(child);
		// on transforme cette date en calendar
		GregorianCalendar lastVoteTest = new GregorianCalendar();

		Date voteDate;

		//si la la date du dernier vote est null alors on vote pour la journee d'hier
		//on a jamais vote
		if (lastVote == null) {
			voteDate = yesterday;
		} else {
			//sinon on transforme en calendar cette valeur lastvote
			lastVoteTest.setTime(lastVote);
			//si la date d'aujourd'hui est �gale � cette date test
			if (todayTest.getTimeInMillis() == lastVoteTest.getTimeInMillis()) {
				//alors on est parti pour modifier le vote d'aujourd'hui
				voteDate = today;
				//on recupere l'id du vote � modifier
				id = moodCrud.findLastVoteID(child, lastVote);
			//sinon si la datetestd'hier est egale au lastvotetest
			} else if (yesterdayTest.getTimeInMillis() == lastVoteTest.getTimeInMillis()) {
				//alors la date du vote est aujourd'hui
				voteDate = today;
			} else {
				//sinon la date du vote est pour la journee hier
				voteDate = yesterday;
			}
		}

		model.addAttribute("voteDate", voteDate);
		model.addAttribute("MoodId", id);
		//en mode cache on recupere toutes les infos du child associe
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

		return REDIRECT + UserController.BASE_URL + "/resume";
	}

	@Autowired
	private IMoodCrudRepository moodCrud;

	@Autowired
	private UserController userCont;

	public MoodController() {
		super(Mood.class, BASE_URL);
	}

	@RequestMapping(path = "/week", method = RequestMethod.GET)
	public String weekView(Model model) {

		Date sd = new Date();
		Calendar cd = Calendar.getInstance();
		cd.setTime(sd);
		String jour = "truc";
		Integer debutsemaine = null;
		Integer finsemaine = null;
		Integer mois = null;

		if (cd.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
			jour = "Lundi";
			debutsemaine = cd.get(Calendar.DAY_OF_MONTH);
			cd.add(Calendar.DATE, 4);
			finsemaine = cd.get(Calendar.DAY_OF_MONTH);
		}

		else if (cd.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
			jour = "Mardi";
			cd.add(Calendar.DATE, -1);
			debutsemaine = cd.get(Calendar.DAY_OF_MONTH);
			cd.add(Calendar.DATE, 4);
			finsemaine = cd.get(Calendar.DAY_OF_MONTH);
		}

		else if (cd.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
			jour = "Mercredi";
			cd.add(Calendar.DATE, -2);
			debutsemaine = cd.get(Calendar.DAY_OF_MONTH);
			cd.add(Calendar.DATE, 4);
			finsemaine = cd.get(Calendar.DAY_OF_MONTH);
		}

		else if (cd.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
			jour = "Jeudi";
			cd.add(Calendar.DATE, -3);
			debutsemaine = cd.get(Calendar.DAY_OF_MONTH);
			cd.add(Calendar.DATE, 4);
			finsemaine = cd.get(Calendar.DAY_OF_MONTH);
		}

		else if (cd.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
			jour = "Vendredi";
			cd.add(Calendar.DATE, -4);
			debutsemaine = cd.get(Calendar.DAY_OF_MONTH);
			cd.add(Calendar.DATE, 4);
			finsemaine = cd.get(Calendar.DAY_OF_MONTH);
		}

		else if (cd.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
			jour = "Vendredi";
			cd.add(Calendar.DATE, -5);
			debutsemaine = cd.get(Calendar.DAY_OF_MONTH);
			cd.add(Calendar.DATE, 4);
			finsemaine = cd.get(Calendar.DAY_OF_MONTH);
		}

		else if (cd.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			jour = "Vendredi";
			cd.add(Calendar.DATE, -6);
			debutsemaine = cd.get(Calendar.DAY_OF_MONTH);
			cd.add(Calendar.DATE, 4);
			finsemaine = cd.get(Calendar.DAY_OF_MONTH);
		}

		mois = cd.get(Calendar.MONTH);
		model.addAttribute("date", jour);
		model.addAttribute("debutsemaine", debutsemaine);
		model.addAttribute("finsemaine", finsemaine);
		model.addAttribute("mois", String.format("%02d", mois + 1));

		int totbad;
		totbad = moodCrud.findSatisaction(-1);
		model.addAttribute("totbad", totbad);

		int totnorm;
		totnorm = moodCrud.findSatisaction(0);
		model.addAttribute("totnorm", totnorm);

		int totgood;
		totgood = moodCrud.findSatisaction(1);
		model.addAttribute("totgood", totgood);

		return "mood/week";
	}
}
