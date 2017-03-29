package com.poeicgi.nikosmileweb.controllers.base.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poeicgi.nikosmileweb.controllers.base.BaseController;
import com.poeicgi.nikosmileweb.dao.IMoodCrudRepository;
import com.poeicgi.nikosmileweb.dao.base.IBaseCrudRepository;
import com.poeicgi.nikosmileweb.models.User;
import com.poeicgi.nikosmileweb.models.modelbase.DataBaseItem;
import com.poeicgi.nikosmileweb.utils.DumpFields;

public abstract class ViewBaseController<T extends DataBaseItem> extends BaseController<T>{

	protected String basePath;

	protected String basePage;

	protected String createView;
	protected String createRedirect;

	protected String deleteView;
	protected String deleteRedirect;

	protected String updateView;
	protected String updateRedirect;

	protected String showView;
	protected String showRedirect;

	protected String listView;
	protected String listRedirect;
	protected String baseView;





	private String baseName;
	private String baseUrl;

	@Secured("ROLE_ADMIN")
	@RequestMapping(path = "/list/", method = RequestMethod.GET)
	public String list(Model model) {

		ArrayList<T> items = (ArrayList<T>) baseCrud.findAll();

		String pageName = baseName.toUpperCase() + "S";

		ArrayList<Map<String,Object>> fields= DumpFields.createContentsEmpty(super.getClazz()).getMyFields();

		model.addAttribute("items", DumpFields.listFielder(items));
		model.addAttribute("fields", fields);
		model.addAttribute("page", pageName);
		model.addAttribute("items", DumpFields.listFielder(super.getItems()));

		return "base/list";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(path = "{id}/show", method = RequestMethod.GET)
	public String showOne(Model model,@PathVariable long id){

		T item = (T) baseCrud.findOne(id);

		String pageName = baseName + " n° "+ id;

		model.addAttribute("item", DumpFields.fielder(item));
		model.addAttribute("fields", DumpFields.createContentsEmpty(super.getClazz()).getMyFields());
		model.addAttribute("page", pageName);
		model.addAttribute("currentItem", DumpFields.fielder(super.getItem(id)));

		return "base/show";
	}

	@Secured({"ROLE_ADMIN", "ROLE_MODO"})
	@RequestMapping(path = "/{id}/update", method = RequestMethod.GET)
	public String updateView(Model model,@PathVariable long id){

		T item = (T) baseCrud.findOne(id);

		String pageName = "Update "+ baseName + " n° "+ id;

		model.addAttribute("item", DumpFields.fielder(item));
		model.addAttribute("fields", DumpFields.createContentsEmpty(super.getClazz()).getMyFields());
		model.addAttribute("page", pageName);
		model.addAttribute("path", baseUrl);

		return "base/update";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(path = "/{id}/delete", method = RequestMethod.GET)
	public String deleteView(Model model,@PathVariable long id){

		T item = (T) baseCrud.findOne(id);

		String pageName = "Delete "+ baseName + " n° "+ id;

		model.addAttribute("item", DumpFields.fielder(item));
		model.addAttribute("fields", DumpFields.createContentsEmpty(super.getClazz()).getMyFields());
		model.addAttribute("page", pageName);
		model.addAttribute("path", baseUrl);

		return "base/delete";
	}

	@Secured({"ROLE_ADMIN", "ROLE_MODO"})
	@RequestMapping(path = "/create/", method = RequestMethod.GET)
	public String createView(Model model){

		String pageName = "Create a "+ baseName;

		model.addAttribute("fields", DumpFields.createContentsEmpty(super.getClazz()).getMyFields());
		model.addAttribute("page", pageName);
		model.addAttribute("path", baseUrl);

		return "base/create";
	}

	@Secured({"ROLE_ADMIN", "ROLE_MODO"})
	@RequestMapping(path = "/create/do", method = RequestMethod.POST)
	public String create(Model model, T item){

		insertItem(item);

//		ArrayList<T> items = (ArrayList<T>) baseCrud.findAll();
//		String pageName = baseName.toUpperCase() + "S";
//		model.addAttribute("items", DumpFields.listFielder(items));
//		model.addAttribute("fields", DumpFields.createContentsEmpty(super.getClazz()).fields);
//		model.addAttribute("page", pageName);

		return super.REDIRECT+baseUrl +"/list/";
	}

	@Secured({"ROLE_ADMIN", "ROLE_MODO"})
	@RequestMapping(path = "/{id}/update/do", method = RequestMethod.POST)
	public String update(Model model, T item){

		updateItem(item);

//		ArrayList<T> items = (ArrayList<T>) baseCrud.findAll();
//		String pageName = baseName.toUpperCase() + "S";
//		model.addAttribute("items", DumpFields.listFielder(items));
//		model.addAttribute("fields", DumpFields.createContentsEmpty(super.getClazz()).fields);
//		model.addAttribute("page", pageName);

		return super.REDIRECT+baseUrl +"/list/";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(path = "/{id}/delete/do", method = RequestMethod.POST)
	public String delete(Model model, T item){

		deleteItem(item);

//		ArrayList<T> items = (ArrayList<T>) baseCrud.findAll();
//		String pageName = baseName.toUpperCase() + "S";
//		model.addAttribute("items", DumpFields.listFielder(items));
//		model.addAttribute("fields", DumpFields.createContentsEmpty(super.getClazz()).fields);
//		model.addAttribute("page", pageName);

		return super.REDIRECT+baseUrl +"/list/";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(path = "/{id}/update/{child}", method = RequestMethod.GET)
	public String updateChildView(Model model,@PathVariable String child,@PathVariable Long id){

		T item = (T) baseCrud.findOne(id);

		String pageName = "Update "+ baseName + " n° "+ id + " children of type : "+ child;

		model.addAttribute("item", DumpFields.fielder(item));
		model.addAttribute("field", child);
		model.addAttribute("page", pageName);
		model.addAttribute("path", baseUrl);

		return "base/updateChild";
	}

	@Autowired
	private IBaseCrudRepository<T> baseCrud;

	public ViewBaseController(Class<T> clazz, String base_url) {
		super(clazz);

		this.baseUrl= base_url;
		this.baseName = this.getClazz().getSimpleName();

	}

}
