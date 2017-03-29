package com.poeicgi.nikosmileweb.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("thought")
public class SessionViewController {

	@RequestMapping(value = "/single-field")
	public ModelAndView singleFieldPage() {
		return new ModelAndView("single-field-page");
	}

	// function that get as param (given by user) thoughtParam and put it in the
	// model as thought (which is defined earlier as session attribute)
	@Secured("admin")
	@RequestMapping(value = "/remember")
	public ModelAndView rememberThought(@RequestParam String thoughtParam) {
		ModelAndView modelAndView = new ModelAndView();
		// <=> a model.addAttribute
		modelAndView.addObject("thought", thoughtParam);
		// <=> a return "toto/view"
		modelAndView.setViewName("single-field-page");
		return modelAndView;
	}
}
