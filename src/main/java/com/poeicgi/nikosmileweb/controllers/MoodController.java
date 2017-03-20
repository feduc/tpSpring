package com.poeicgi.nikosmileweb.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poeicgi.nikosmileweb.controllers.base.view.ViewBaseController;
import com.poeicgi.nikosmileweb.dao.IMoodCrudRepository;
import com.poeicgi.nikosmileweb.dao.base.IBaseCrudRepository;
import com.poeicgi.nikosmileweb.models.Mood;
import com.poeicgi.nikosmileweb.models.security.SecurityUser;
import com.poeicgi.nikosmileweb.utils.DumpFields;

@Controller
@RequestMapping(path = MoodController.BASE_URL)
public class MoodController extends ViewBaseController<Mood>{

	@Autowired
	private IMoodCrudRepository moodCrud;

	public final static String BASE_URL = "/mood";

	// value is the address to enter in the browser to launch index(), it can be
	// more than one when writing value = {"/path1", "/path2"}
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String index(Model model) {

		ArrayList<Mood> moods = (ArrayList<Mood>) moodCrud.findAll();

		model.addAttribute("items", DumpFields.listFielder(moods));
		model.addAttribute("fields", moods.get(0).getMyFields());
		model.addAttribute("page", "MOODS");

		return "toto";
		// return "toto" works because toto.ftl is directly in templates, if it
		// was in templates.pages return would have to be equal to "pages/toto"
	}

	public MoodController() {
		super(Mood.class,BASE_URL);
	}

	@RequestMapping(path = "/vote", method = RequestMethod.GET)
	public String voteView(Model model){

			model.addAttribute("date", new Date());
			return "mood/vote";
	}

	@RequestMapping(path = "/week", method = RequestMethod.GET)
	public String weekView(Model model){

	    Date sd=new Date();
	     Calendar cd=Calendar.getInstance();
	     cd.setTime(sd);
		String jour ="truc";
		Integer debutsemaine = null;
		Integer finsemaine = null;
		Integer mois = null;

		if ( cd.get( Calendar.DAY_OF_WEEK )== Calendar.MONDAY ) {
			jour = "Lundi";
			debutsemaine = cd.get( Calendar.DAY_OF_MONTH);
			cd.add(Calendar.DATE,4);
			finsemaine = cd.get( Calendar.DAY_OF_MONTH);
		}

		else if ( cd.get( Calendar.DAY_OF_WEEK )== Calendar.TUESDAY ) {
			jour = "Mardi";
			cd.add(Calendar.DATE,-1);
			debutsemaine = cd.get( Calendar.DAY_OF_MONTH);
			cd.add(Calendar.DATE,4);
			finsemaine = cd.get( Calendar.DAY_OF_MONTH);
		}

		else if ( cd.get( Calendar.DAY_OF_WEEK )== Calendar.WEDNESDAY ) {
			jour = "Mercredi";
			cd.add(Calendar.DATE,-2);
			debutsemaine = cd.get( Calendar.DAY_OF_MONTH);
			cd.add(Calendar.DATE,4);
			finsemaine = cd.get( Calendar.DAY_OF_MONTH);
		}

		else if ( cd.get( Calendar.DAY_OF_WEEK )== Calendar.THURSDAY ) {
			jour = "Jeudi";
			cd.add(Calendar.DATE,-3);
			debutsemaine = cd.get( Calendar.DAY_OF_MONTH);
			cd.add(Calendar.DATE,4);
			finsemaine = cd.get( Calendar.DAY_OF_MONTH);
		}

		else if ( cd.get( Calendar.DAY_OF_WEEK )== Calendar.FRIDAY ) {
			jour = "Vendredi";
			cd.add(Calendar.DATE,-4);
			debutsemaine = cd.get( Calendar.DAY_OF_MONTH);
			cd.add(Calendar.DATE,4);
			finsemaine = cd.get( Calendar.DAY_OF_MONTH);

		}

		else if ( cd.get( Calendar.DAY_OF_WEEK )== Calendar.SATURDAY ) {
			jour = "Vendredi";
			cd.add(Calendar.DATE,-5);
			debutsemaine = cd.get( Calendar.DAY_OF_MONTH);
			cd.add(Calendar.DATE,4);
			finsemaine = cd.get( Calendar.DAY_OF_MONTH);
		}

		else if ( cd.get( Calendar.DAY_OF_WEEK )== Calendar.SUNDAY ) {
			jour = "Vendredi";
			cd.add(Calendar.DATE,-6);
			debutsemaine = cd.get( Calendar.DAY_OF_MONTH);
			cd.add(Calendar.DATE,4);
			finsemaine = cd.get( Calendar.DAY_OF_MONTH);

		}

		mois = cd.get(Calendar.MONTH);
		model.addAttribute("date", jour);
		model.addAttribute("debutsemaine", debutsemaine);
		model.addAttribute("finsemaine", finsemaine);
		model.addAttribute("mois", String.format("%02d",mois+1));

		int totbad;
		totbad = moodCrud.findSatisaction(-1);
		model.addAttribute("totbad", totbad);
		return "mood/week";
	}
}
