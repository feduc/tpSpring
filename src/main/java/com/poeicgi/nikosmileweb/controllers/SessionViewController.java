package com.poeicgi.nikosmileweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes ("thought")
public class SessionViewController {

	@RequestMapping(value="/single_field")
	public ModelAndView singleFieldPage {
		
	}
	
	
}
