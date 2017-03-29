package com.poeicgi.nikosmileweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poeicgi.nikosmileweb.controllers.base.view.ViewBaseController;
import com.poeicgi.nikosmileweb.dao.IChangeDateCrudRepository;
import com.poeicgi.nikosmileweb.models.ChangeDate;

@Controller
@RequestMapping(path = ChangeDateController.BASE_URL)
public class ChangeDateController extends ViewBaseController<ChangeDate>{

	public final static String BASE_URL = "/changedate";

	@Autowired
	private IChangeDateCrudRepository changeDateCrud;

	public ChangeDateController() {
		super(ChangeDate.class,BASE_URL);
	}
}
