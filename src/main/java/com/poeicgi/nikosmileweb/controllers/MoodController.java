package com.poeicgi.nikosmileweb.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Calendar;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.poeicgi.nikosmileweb.controllers.base.view.ViewBaseController;
import com.poeicgi.nikosmileweb.controllers.security.SecurityController;
import com.poeicgi.nikosmileweb.dao.IMoodCrudRepository;
import com.poeicgi.nikosmileweb.dao.IUserCrudRepository;
import com.poeicgi.nikosmileweb.models.ChangeDate;
import com.poeicgi.nikosmileweb.models.Mood;
import com.poeicgi.nikosmileweb.models.User;

@Controller
@RequestMapping(path = MoodController.BASE_URL)
public class MoodController extends ViewBaseController<Mood> {

	@Autowired
	private IMoodCrudRepository moodCrud;

	@Autowired
	private IUserCrudRepository userCrud;

	@Autowired
	private ChangeDateController changeCont;

	@Autowired
	private SecurityController securityController;

	public MoodController() {
		super(Mood.class, BASE_URL);
	}

	public final static String BASE_URL = "/mood";

	// value is the address to enter in the browser to launch index(), it can be
	// more than one when writing value = {"/path1", "/path2"}
	@RequestMapping(path = "/vote", method = RequestMethod.GET)
	public String voteView(Model model) {

		User child = securityController.getConnectedUser();

		Long id = 0L;
		// creation d'une date d'aujourd'hui
		Date date = new Date();
		// que l'on ajoute dans le modele
		model.addAttribute("date", date);

		// on cree un gregoriancalendat
		GregorianCalendar todayTest = new GregorianCalendar();
		// auquel on attribue la date
		todayTest.setTime(date);
		// on met a 0 l'heure
		todayTest.set(GregorianCalendar.HOUR_OF_DAY, 00);
		todayTest.set(GregorianCalendar.MINUTE, 00);
		todayTest.set(GregorianCalendar.SECOND, 00);
		todayTest.set(GregorianCalendar.MILLISECOND, 00);
		// que l'on recupere en millisecond > Date d'aujourd'hui en millis
		Date today = new Date(todayTest.getTimeInMillis());

		// gregorian d'hier
		GregorianCalendar yesterdayTest = new GregorianCalendar();
		yesterdayTest.setTimeInMillis(todayTest.getTimeInMillis());
		// auquel on attribue date -1
		yesterdayTest.add(GregorianCalendar.DATE, -1);
		// en millisecond> Date d'hier en millis
		Date yesterday = new Date(yesterdayTest.getTimeInMillis());

		// on recupere la date du dernier vote
		Date lastVote = moodCrud.findLastVote(child);

		// on transforme cette date en calendar
		GregorianCalendar lastVoteTest = new GregorianCalendar();

		Date voteDate;

		// si la la date du dernier vote est null alors on vote pour la journee
		// d'hier
		// on a jamais vote
		if (lastVote == null) {
			voteDate = yesterday;
		} else {
			// sinon on transforme en calendar cette valeur lastvote
			lastVoteTest.setTime(lastVote);

			// si la date d'aujourd'hui est �gale � cette date test
			if (todayTest.get(GregorianCalendar.YEAR) == lastVoteTest.get(GregorianCalendar.YEAR)
					&& todayTest.get(GregorianCalendar.MONTH) == lastVoteTest.get(GregorianCalendar.MONTH) && todayTest
							.get(GregorianCalendar.DAY_OF_MONTH) == lastVoteTest.get(GregorianCalendar.DAY_OF_MONTH)) {

				// alors on est parti pour modifier le vote d'aujourd'hui
				voteDate = today;
				// on recupere l'id du vote a modifier
				id = moodCrud.findLastVoteID(child, lastVote);

				// sinon si la datetestd'hier est egale au lastvotetest
			} else if (yesterdayTest.get(GregorianCalendar.YEAR) == lastVoteTest.get(GregorianCalendar.YEAR)
					&& yesterdayTest.get(GregorianCalendar.MONTH) == lastVoteTest.get(GregorianCalendar.MONTH)
					&& yesterdayTest.get(GregorianCalendar.DAY_OF_MONTH) == lastVoteTest
							.get(GregorianCalendar.DAY_OF_MONTH)) {
				// alors la date du vote est aujourd'hui
				voteDate = today;
			} else {
				// sinon la date du vote est pour la journee hier
				voteDate = yesterday;
			}
		}

		model.addAttribute("voteDate", voteDate);
		model.addAttribute("MoodId", id);

		return "mood/vote";

		// return "toto" works because toto.ftl is directly in templates, if it
		// was in templates.pages return would have to be equal to "pages/toto"
	}

	@RequestMapping(path = "/create/done", method = RequestMethod.POST)
	public String create(Model model, @ModelAttribute Mood item, @ModelAttribute User child,
			@ModelAttribute("MoodID") String MoodID, @ModelAttribute("child") User userChild,
			final BindingResult childBindingResult, final Model model2, final RedirectAttributes redirectAttributes) {
		Map<String, Object> map = model.asMap();

		Long id = Long.parseLong(MoodID);

		item.setId(id);

		if (item.getId() != 0) {
			ChangeDate changeDate = new ChangeDate();
			changeDate.setChangeDate(item.getLogDate());
			changeDate.setMood(item);
			item.setLogDate(getItem(id).getLogDate());
			item.getChangeDates().add(changeDate);
			changeCont.insertItem(changeDate);
		}

		insertItem(item);

		User user = userCrud.findOne(child.getId());
		user.getMoods().add(item);
		userCrud.save(user);
		item.setUser(user);

		updateItem(item);

		Set<Mood> list = user.getMoods();
		list.add(item);
		user.setMoods(list);
		userCrud.save(user);

		userChild = userCrud.findOne(user.getId());
		redirectAttributes.addAttribute("child", userChild);

		return REDIRECT + UserController.BASE_URL + "/resume";
	}

	@RequestMapping(path = "/week", method = RequestMethod.GET)
	public String weekView(Model model,@ModelAttribute("child") User child,
			@ModelAttribute("date") Long date,
			@ModelAttribute("projectName") String projectName)

	{

		Date sd = new Date(date);
		Calendar cd = Calendar.getInstance();
		cd.setTime(sd);

		String jour = "";

		Date lundi = null;
		Date mardi = null;
		Date mercredi = null;
		Date jeudi = null;
		Date vendredi = null;
		Integer debutsemaine = null;
		Integer finsemaine = null;
		Integer mois = null;
		Integer mois1 = null;
		Integer annee = null;


		if (cd.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
			jour = "Lundi";
			debutsemaine = cd.get(Calendar.DAY_OF_MONTH);
			sd = new Date(cd.getTimeInMillis());
			lundi = new Date(cd.getTimeInMillis());
			mois = cd.get(Calendar.MONTH);
			cd.add(Calendar.DATE, 4);
			finsemaine = cd.get(Calendar.DAY_OF_MONTH);
		}

		else if (cd.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
			jour = "Mardi";
			cd.add(Calendar.DATE, -1);
			debutsemaine = cd.get(Calendar.DAY_OF_MONTH);
			sd = new Date(cd.getTimeInMillis());
			lundi = new Date(cd.getTimeInMillis());
			mois = cd.get(Calendar.MONTH);
			cd.add(Calendar.DATE, 4);
			finsemaine = cd.get(Calendar.DAY_OF_MONTH);
		}

		else if (cd.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
			jour = "Mercredi";
			cd.add(Calendar.DATE, -2);
			debutsemaine = cd.get(Calendar.DAY_OF_MONTH);
			sd = new Date(cd.getTimeInMillis());
			lundi = new Date(cd.getTimeInMillis());
			mois = cd.get(Calendar.MONTH);
			cd.add(Calendar.DATE, 4);
			finsemaine = cd.get(Calendar.DAY_OF_MONTH);
		}

		else if (cd.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
			jour = "Jeudi";
			cd.add(Calendar.DATE, -3);
			debutsemaine = cd.get(Calendar.DAY_OF_MONTH);
			sd = new Date(cd.getTimeInMillis());
			lundi = new Date(cd.getTimeInMillis());
			mois = cd.get(Calendar.MONTH);
			cd.add(Calendar.DATE, 4);
			finsemaine = cd.get(Calendar.DAY_OF_MONTH);
		}

		else if (cd.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
			jour = "Vendredi";
			cd.add(Calendar.DATE, -4);
			debutsemaine = cd.get(Calendar.DAY_OF_MONTH);
			sd = new Date(cd.getTimeInMillis());
			lundi = new Date(cd.getTimeInMillis());
			mois = cd.get(Calendar.MONTH);
			cd.add(Calendar.DATE, 4);
			finsemaine = cd.get(Calendar.DAY_OF_MONTH);
		}

		else if (cd.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
			jour = "Samedi";
			cd.add(Calendar.DATE, -5);
			debutsemaine = cd.get(Calendar.DAY_OF_MONTH);
			sd = new Date(cd.getTimeInMillis());
			lundi = new Date(cd.getTimeInMillis());
			mois = cd.get(Calendar.MONTH);
			cd.add(Calendar.DATE, 4);
			finsemaine = cd.get(Calendar.DAY_OF_MONTH);
		}

		else if (cd.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			jour = "Dimanche";
			cd.add(Calendar.DATE, -6);
			debutsemaine = cd.get(Calendar.DAY_OF_MONTH);
			sd = new Date(cd.getTimeInMillis());
			lundi = new Date(cd.getTimeInMillis());
			mois = cd.get(Calendar.MONTH);
			cd.add(Calendar.DATE, 4);
			finsemaine = cd.get(Calendar.DAY_OF_MONTH);
		}


		mois1 = cd.get(Calendar.MONTH);
		annee = cd.get(Calendar.YEAR);
		model.addAttribute("date", jour);
		model.addAttribute("debutsemaine", String.format("%02d", debutsemaine));
		model.addAttribute("finsemaine", String.format("%02d", finsemaine));
		model.addAttribute("mois", String.format("%02d", mois + 1));
		model.addAttribute("mois1", String.format("%02d", mois1 + 1));
		model.addAttribute("annee", String.format("%04d", annee));

		model.addAttribute("date", sd.getTime());

		model.addAttribute("projectName", projectName);

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

		model.addAttribute("lundi", lundi.getTime());

		cd.setTime(lundi);
		cd.add(Calendar.DATE, 1);
		mardi = new Date(cd.getTimeInMillis());
		model.addAttribute("mardi", mardi.getTime());

		cd.add(Calendar.DATE, 1);
		mercredi = new Date(cd.getTimeInMillis());
		model.addAttribute("mercredi", mercredi.getTime());

		cd.add(Calendar.DATE, 1);
		jeudi = new Date(cd.getTimeInMillis());
		model.addAttribute("jeudi", jeudi.getTime());

		cd.add(Calendar.DATE, 1);
		vendredi = new Date(cd.getTimeInMillis());
		model.addAttribute("vendredi", vendredi.getTime());

		return "mood/week";
	}

	@RequestMapping(path = "/week/change", method = RequestMethod.GET)
	public String weekChange(Model model,@ModelAttribute User child,
			@ModelAttribute("date") Long date,@ModelAttribute("projectName") String projectName,@ModelAttribute("changeWeek") String changeWeek,
			final BindingResult childBindingResult, final Model model2, final RedirectAttributes redirectAttributes) {

			Date sd= new Date();
			Calendar cd = Calendar.getInstance();

		if (changeWeek.equals("previous")){

			sd = new Date(date);

			cd.setTime(sd);
			cd.add(Calendar.DATE, -7);
			sd = new Date(cd.getTimeInMillis());
		}
		else if (changeWeek.equals("next")){

			sd = new Date(date);

			cd.setTime(sd);
			cd.add(Calendar.DATE, 7);
			sd = new Date(cd.getTimeInMillis());
		}
		else {
			cd = Calendar.getInstance();
			cd.setTime(sd);

			cd.set(Calendar.HOUR_OF_DAY, 00);
			cd.set(Calendar.MINUTE, 00);
			cd.set(Calendar.SECOND, 00);
			cd.set(Calendar.MILLISECOND, 00);
			sd = new Date(cd.getTimeInMillis());
		}

		redirectAttributes.addAttribute("projectName", projectName);
		redirectAttributes.addAttribute("date", sd.getTime());

		return REDIRECT + MoodController.BASE_URL + "/week/" ;

	}

	@RequestMapping(path = "/day/", method = RequestMethod.GET)
	public String dayView(Model model,
			@ModelAttribute("date") Long date,@ModelAttribute("projectName") String projectName) {

			Date sd= new Date();
			Calendar cd = Calendar.getInstance();
			sd = new Date(date);
			cd.setTimeInMillis(date);

			int jour = cd.get(Calendar.DAY_OF_MONTH);
			int mois = cd.get(Calendar.MONTH);
			int annee = cd.get(Calendar.YEAR);

			model.addAttribute("jour", String.format("%02d", jour));
			model.addAttribute("mois", String.format("%02d", mois + 1));
			model.addAttribute("annee", String.format("%04d", annee));

			model.addAttribute("date", date);

			model.addAttribute("projectName", projectName);

			List<Mood> moods = moodCrud.findMoodsByProjectAndDate(projectName,sd);

			ArrayList<Map<String,Object>> dayInfos = new ArrayList<Map<String,Object>>();

			int i=0;
			for (Mood mood : moods) {
				dayInfos.add(new HashMap<String,Object>());
				(dayInfos.get(i)).put("satis", mood.getSatisfaction());
				(dayInfos.get(i)).put("comment", mood.getCommentSat());

				i++;

			}

			model.addAttribute("dayInfos", dayInfos);

		return "mood/day" ;

	}

	@RequestMapping(path = "/month", method = RequestMethod.GET)
	public String MonthView(Model model,
			@ModelAttribute("date") Long date,@ModelAttribute("projectName") String projectName) {

		Date sd = new Date(date);
		Calendar cd = Calendar.getInstance();
		cd.setTime(sd);

		Integer dayBeginMonth = 01;
		Integer dayEndMonth = cd.getActualMaximum(Calendar.DAY_OF_MONTH);
		Integer Month = cd.get(Calendar.MONTH);
		Integer today = cd.get(Calendar.DAY_OF_MONTH);
		Integer annee = cd.get(Calendar.YEAR);

		ArrayList<Map<String,Object>> days = new ArrayList<Map<String,Object>>();

		int i =0;
		for (i=1; i<=dayEndMonth ; i++){
			days.add(new HashMap<String, Object>());
			cd.set(GregorianCalendar.DAY_OF_MONTH,i);

			sd = new Date(cd.getTimeInMillis());

			days.get(i-1).put("jour", i);
			days.get(i-1).put("date", sd.getTime());

			GregorianCalendar todayTest = new GregorianCalendar();

			todayTest.set(GregorianCalendar.DAY_OF_MONTH, i);

			todayTest.set(GregorianCalendar.MONTH, Month);
			todayTest.set(GregorianCalendar.YEAR, annee);
			Integer joursem = todayTest.get(GregorianCalendar.DAY_OF_WEEK);

			days.get(i-1).put("joursem", joursem);

			String nomjour = "";

			if (joursem == 2){
				nomjour = "Lundi";
			}
			else if (joursem == 3){
				nomjour = "Mardi";
			}
			else if (joursem == 4){
				nomjour = "Mercredi";
			}
			else if (joursem == 5){
				nomjour = "Jeudi";
			}
			else if (joursem == 6){
				nomjour = "Vendredi";
			}
			else if (joursem == 7){
				nomjour = "Samedi";
			}
			else if (joursem == 1){
				nomjour = "Dimanche";
			}

			days.get(i-1).put("nomjour", nomjour);
			todayTest.set(GregorianCalendar.HOUR_OF_DAY, 00);
			todayTest.set(GregorianCalendar.MINUTE, 00);
			todayTest.set(GregorianCalendar.SECOND, 00);
			todayTest.set(GregorianCalendar.MILLISECOND, 00);

			Date todayTestDate = new Date (todayTest.getTimeInMillis());

			Float nbavis = moodCrud.countMoodsByDate(projectName,todayTestDate);
			Float somAvis = moodCrud.sumMoodsByDate(projectName,todayTestDate);

			days.get(i-1).put("nbavis", nbavis);
			days.get(i-1).put("somAvis", somAvis);

			Float med = null;
			Integer red = null;
			Integer green = null;
			Integer blue = 0;

			if (somAvis != null){

			med = (somAvis / nbavis);

			if(med > 0)
			{
				green = 255;
				red = Math.round(255 * (1 - med));
			}
			else if (med < 0)
			{
				red = 255;
				green = Math.round(255 * (1 + med));
			}
			else
			{
				red = 255;
				green = 255;
				blue = 0;
			}

			}

			days.get(i-1).put("red",red);
			days.get(i-1).put("green",green);
			days.get(i-1).put("blue",blue);
			days.get(i-1).put("med", med);

		}

		model.addAttribute("days",days);
		model.addAttribute("mois", String.format("%02d", Month+1));
		model.addAttribute("debutmois", String.format("%02d", dayBeginMonth));
		model.addAttribute("finmois", String.format("%02d", dayEndMonth));
		model.addAttribute("jouractuel", String.format("%02d", today));
		model.addAttribute("annee", String.format("%04d", annee));

		return "mood/month";
	}

	@RequestMapping(path = "/month/change", method = RequestMethod.GET)
	public String monthChange(Model model,
		@ModelAttribute("date") Long date,@ModelAttribute("projectName") String projectName,@ModelAttribute("changeMonth") String changeMonth,
		final BindingResult childBindingResult, final Model model2, final RedirectAttributes redirectAttributes) {

		Date sd= new Date();
		Calendar cd = Calendar.getInstance();

		if (changeMonth.equals("previous")){

			sd = new Date(date);

			cd.setTime(sd);
			cd.set(Calendar.DAY_OF_MONTH, 1);
			cd.add(Calendar.MONTH, -1);
			sd = new Date(cd.getTimeInMillis());
		}
		else if (changeMonth.equals("next")){

			sd = new Date(date);
			Integer dayEndMonth = cd.getActualMaximum(Calendar.DAY_OF_MONTH);
			cd.setTime(sd);
			cd.add(Calendar.MONTH, 1);
			cd.set(Calendar.DAY_OF_MONTH, 1);
			sd = new Date(cd.getTimeInMillis());
		}
		else {
			cd = Calendar.getInstance();
			cd.setTime(sd);

			cd.set(Calendar.HOUR_OF_DAY, 00);
			cd.set(Calendar.MINUTE, 00);
			cd.set(Calendar.SECOND, 00);
			cd.set(Calendar.MILLISECOND, 00);
			sd = new Date(cd.getTimeInMillis());
		}

		redirectAttributes.addAttribute("projectName", projectName);
		redirectAttributes.addAttribute("date", sd.getTime());

		return REDIRECT + MoodController.BASE_URL + "/month/" ;

	}
}