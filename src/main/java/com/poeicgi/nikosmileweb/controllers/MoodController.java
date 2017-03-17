package com.poeicgi.nikosmileweb.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poeicgi.nikosmileweb.controllers.base.view.ViewBaseController;
import com.poeicgi.nikosmileweb.dao.IMoodCrudRepository;
import com.poeicgi.nikosmileweb.models.Mood;
import com.poeicgi.nikosmileweb.utils.DumpFields;

@Controller
@RequestMapping(path = MoodController.BASE_URL)
public class MoodController extends ViewBaseController<Mood>{
	
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
	
	@Autowired
	private IMoodCrudRepository moodCrud;
	
	public MoodController() {
		super(Mood.class,BASE_URL);
	}
}
