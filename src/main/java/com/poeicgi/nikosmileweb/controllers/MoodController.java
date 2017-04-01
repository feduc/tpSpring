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
import com.poeicgi.nikosmileweb.controllers.security.SecurityController;
import com.poeicgi.nikosmileweb.dao.IMoodCrudRepository;
import com.poeicgi.nikosmileweb.dao.IProjectCrudRepository;
import com.poeicgi.nikosmileweb.dao.ISecurityUserCrudRepository;
import com.poeicgi.nikosmileweb.dao.IUserCrudRepository;
import com.poeicgi.nikosmileweb.models.ChangeDate;
import com.poeicgi.nikosmileweb.models.Mood;
import com.poeicgi.nikosmileweb.models.User;
import com.poeicgi.nikosmileweb.models.security.SecurityUser;

@Controller
@RequestMapping(path = MoodController.BASE_URL)
public class MoodController extends ViewBaseController<Mood> {

	@Autowired
	private IMoodCrudRepository moodCrud;

	@Autowired
	private IUserCrudRepository userCrud;

	@Autowired
	private IProjectCrudRepository projectCrud;

	@Autowired
	private ChangeDateController changeCont;

	@Autowired
	private SecurityController securityController;

	@Autowired
	private ISecurityUserCrudRepository secuCrud;

	public MoodController() {
		super(Mood.class, BASE_URL);
	}

	public final static String BASE_URL = "/mood";



	// value is the address to enter in the browser to launch index(), it can be
	// more than one when writing value = {"/path1", "/path2"}
	@Secured("ROLE_USER")
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

	@Secured("ROLE_USER")
	@RequestMapping(path = "/create/done", method = RequestMethod.POST)
	public String create(Model model, @ModelAttribute Mood item,
			@RequestParam("MoodID") String MoodID) {

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

		User user = securityController.getConnectedUser();
		user.getMoods().add(item);
		userCrud.save(user);
		item.setUser(user);

		updateItem(item);

		Set<Mood> list = user.getMoods();
		list.add(item);
		user.setMoods(list);
		userCrud.save(user);

		return REDIRECT + UserController.BASE_URL + "/resume";
	}

	@Secured("ROLE_USER")
	@RequestMapping(path = "/week", method = RequestMethod.GET)
	public String weekView(Model model,@ModelAttribute("child") User child,
			@ModelAttribute("date") Long date,
			@ModelAttribute("projectName") String projectName)

	{

		Long leaderId;
		leaderId = projectCrud.findProjectsLeaderByName(projectName).getId();

		User connect = securityController.getConnectedUser();

		Boolean leader = false;

		if (leaderId == connect.getId())
		{
			leader=true;
		}

		model.addAttribute("leader", leader);

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

		Date startDate = projectCrud.findProjectStartDatebyName(projectName);
		Date endDate = projectCrud.findProjectEndDatebyName(projectName);

		for (int j = 1; j <= 5; j++) {
			for (int i = 0; i < nbChoice; i++) {
				String nom = "jour" + j + "satis" + i;
				int satis = i - (nbChoice / 2);

				if(endDate!=null){

					if ((startDate.compareTo(sd) == -1) && (sd.compareTo(endDate))==-1) {
						model.addAttribute(nom, moodCrud.countMoodsBySatisfactionForSummary(projectName, sd, satis));
					}

					if ((startDate.compareTo(sd) == 0) || (sd.compareTo(endDate))==0){
						model.addAttribute(nom, moodCrud.countMoodsBySatisfactionForSummary(projectName, sd, satis));
					}

					if (startDate.compareTo(sd)  == 1 ||(sd.compareTo(endDate))==1) {
						model.addAttribute(nom, moodCrud.countMoodsBySatisfactionForSummary(projectName, sd, -2));
					}
				}
				else
					{
					if ((startDate.compareTo(sd) == -1)){
						model.addAttribute(nom, moodCrud.countMoodsBySatisfactionForSummary(projectName, sd, satis));
					}

					if ((startDate.compareTo(sd) == 0)){
						model.addAttribute(nom, moodCrud.countMoodsBySatisfactionForSummary(projectName, sd, satis));
					}

					if (startDate.compareTo(sd)  == 1) {
						model.addAttribute(nom, moodCrud.countMoodsBySatisfactionForSummary(projectName, sd, -2));
					}
					}
			}

			cd.setTime(sd);
			cd.add(Calendar.DATE, 1);
			sd.setTime(cd.getTimeInMillis());
		}

		model.addAttribute("lundi", lundi.getTime());
		String encourslundi="";
		if(endDate!=null){
			if ((startDate.compareTo(lundi) == -1) && (lundi.compareTo(endDate))==-1) {
				encourslundi="ok";
			}
			if ((startDate.compareTo(lundi) == 0) || (lundi.compareTo(endDate))==0){
				encourslundi="ok";
			}
			if (startDate.compareTo(lundi)  == 1 ||(lundi.compareTo(endDate))==1) {
				encourslundi="PasEnCours";
			}
		}
		else
			{
			if ((startDate.compareTo(lundi) == -1)){
				encourslundi="ok";
			}
			if ((startDate.compareTo(lundi) == 0)){
				encourslundi="ok";
			}
			if (startDate.compareTo(lundi)  == 1) {
				encourslundi="PasEnCours";
			}
			}
		model.addAttribute("encourslundi", encourslundi);
		cd.setTime(lundi);
		cd.add(Calendar.DATE, 1);
		mardi = new Date(cd.getTimeInMillis());
		model.addAttribute("mardi", mardi.getTime());
		String encoursmardi="";
		if(endDate!=null){
			if ((startDate.compareTo(mardi) == -1) && (mardi.compareTo(endDate))==-1) {
				encoursmardi="ok";
			}
			if ((startDate.compareTo(mardi) == 0) || (mardi.compareTo(endDate))==0){
				encoursmardi="ok";
			}
			if (startDate.compareTo(mardi)  == 1 ||(mardi.compareTo(endDate))==1) {
				encoursmardi="PasEnCours";
			}
		}
		else
			{
			if ((startDate.compareTo(mardi) == -1)){
				encoursmardi="ok";
			}
			if ((startDate.compareTo(mardi) == 0)){
				encoursmardi="ok";
			}
			if (startDate.compareTo(mardi)  == 1) {
				encoursmardi="PasEnCours";
			}
			}
		model.addAttribute("encoursmardi", encoursmardi);

		cd.add(Calendar.DATE, 1);
		mercredi = new Date(cd.getTimeInMillis());
		model.addAttribute("mercredi", mercredi.getTime());
		String encoursmercredi="";
		if(endDate!=null){
			if ((startDate.compareTo(mercredi) == -1) && (mercredi.compareTo(endDate))==-1) {
				encoursmercredi="ok";
			}
			if ((startDate.compareTo(mercredi) == 0) || (mercredi.compareTo(endDate))==0){
				encoursmercredi="ok";
			}
			if (startDate.compareTo(mercredi)  == 1 ||(mercredi.compareTo(endDate))==1) {
				encoursmercredi="PasEnCours";
			}
		}
		else
			{
			if ((startDate.compareTo(mercredi) == -1)){
				encoursmercredi="ok";
			}
			if ((startDate.compareTo(mercredi) == 0)){
				encoursmercredi="ok";
			}
			if (startDate.compareTo(mercredi)  == 1) {
				encoursmercredi="PasEnCours";
			}
			}
		model.addAttribute("encoursmercredi", encoursmercredi);

		cd.add(Calendar.DATE, 1);
		jeudi = new Date(cd.getTimeInMillis());
		model.addAttribute("jeudi", jeudi.getTime());
		String encoursjeudi="";
		if(endDate!=null){
			if ((startDate.compareTo(jeudi) == -1) && (jeudi.compareTo(endDate))==-1) {
				encoursjeudi="ok";
			}
			if ((startDate.compareTo(jeudi) == 0) || (jeudi.compareTo(endDate))==0){
				encoursjeudi="ok";
			}
			if (startDate.compareTo(jeudi)  == 1 ||(jeudi.compareTo(endDate))==1) {
				encoursjeudi="PasEnCours";
			}
		}
		else
			{
			if ((startDate.compareTo(jeudi) == -1)){
				encoursjeudi="ok";
			}
			if ((startDate.compareTo(jeudi) == 0)){
				encoursjeudi="ok";
			}
			if (startDate.compareTo(jeudi)  == 1) {
				encoursjeudi="PasEnCours";
			}
			}
		model.addAttribute("encoursjeudi", encoursjeudi);

		cd.add(Calendar.DATE, 1);
		vendredi = new Date(cd.getTimeInMillis());
		model.addAttribute("vendredi", vendredi.getTime());
		String encoursvendredi="";
		if(endDate!=null){
			if ((startDate.compareTo(vendredi) == -1) && (vendredi.compareTo(endDate))==-1) {
				encoursvendredi="ok";
			}
			if ((startDate.compareTo(vendredi) == 0) || (vendredi.compareTo(endDate))==0){
				encoursvendredi="ok";
			}
			if (startDate.compareTo(vendredi)  == 1 ||(vendredi.compareTo(endDate))==1) {
				encoursvendredi="PasEnCours";
			}
		}
		else
			{
			if ((startDate.compareTo(vendredi) == -1)){
				encoursvendredi="ok";
			}
			if ((startDate.compareTo(vendredi) == 0)){
				encoursvendredi="ok";
			}
			if (startDate.compareTo(vendredi)  == 1) {
				encoursvendredi="PasEnCours";
			}
			}
		model.addAttribute("encoursvendredi", encoursvendredi);

		return "mood/week";
	}

	@Secured("ROLE_USER")
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

	@Secured("ROLE_USER")
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

	@Secured("ROLE_USER")
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

		Date startDate = projectCrud.findProjectStartDatebyName(projectName);
		Date endDate = projectCrud.findProjectEndDatebyName(projectName);

		String encours = "";

		int i =0;
		for (i=1; i<=dayEndMonth ; i++){
			days.add(new HashMap<String, Object>());
			cd.set(GregorianCalendar.DAY_OF_MONTH,i);
			sd = new Date(cd.getTimeInMillis());

			if(endDate!=null){

				if ((startDate.compareTo(sd) == -1) && (sd.compareTo(endDate))==-1) {
					encours="ok";
				}

				if ((startDate.compareTo(sd) == 0) || (sd.compareTo(endDate))==0){
					encours="ok";
				}

				if (startDate.compareTo(sd)  == 1 ||(sd.compareTo(endDate))==1) {
					encours="PasEnCours";
				}
			}
			else
				{
				if ((startDate.compareTo(sd) == -1)){
					encours="ok";
				}

				if ((startDate.compareTo(sd) == 0)){
					encours="ok";
				}

				if (startDate.compareTo(sd)  == 1) {
					encours="PasEnCours";
				}
				}

			days.get(i-1).put("jour", i);
			days.get(i-1).put("date", sd.getTime());
			days.get(i-1).put("encours",encours);

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
		model.addAttribute("encours",encours);
		model.addAttribute("days",days);
		model.addAttribute("mois", String.format("%02d", Month+1));
		model.addAttribute("debutmois", String.format("%02d", dayBeginMonth));
		model.addAttribute("finmois", String.format("%02d", dayEndMonth));
		model.addAttribute("jouractuel", String.format("%02d", today));
		model.addAttribute("annee", String.format("%04d", annee));

		return "mood/month";
	}

	@Secured("ROLE_USER")
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