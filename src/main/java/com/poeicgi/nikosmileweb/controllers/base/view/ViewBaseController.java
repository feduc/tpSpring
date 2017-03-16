package com.poeicgi.nikosmileweb.controllers.base.view;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poeicgi.nikosmileweb.controllers.base.BaseController;
import com.poeicgi.nikosmileweb.dao.base.IBaseCrudRepository;
import com.poeicgi.nikosmileweb.models.modelbase.DataBaseItem;
import com.poeicgi.nikosmileweb.utils.DumpFields;

public abstract class ViewBaseController<T extends DataBaseItem> extends BaseController<T>{
	
	private String baseName;
	
	@RequestMapping(path = "/list/", method = RequestMethod.GET)
	public String list(Model model) {
		
		ArrayList<T> items = (ArrayList<T>) baseCrud.findAll();
		
		String pageName = baseName.toUpperCase() + "S";
		
		model.addAttribute("items", DumpFields.listFielder(items));
		model.addAttribute("fields", DumpFields.createContentsEmpty(super.getClazz()).fields);
		model.addAttribute("page", pageName);

		return "base/list";
	}
	
	@RequestMapping(path = "/show/{id}", method = RequestMethod.GET)
	public String showOne(Model model,@PathVariable long id){
		
		T item = (T) baseCrud.findOne(id);

		String pageName = baseName + " n° "+ id;
		
		model.addAttribute("item", DumpFields.fielder(item));
		model.addAttribute("fields", DumpFields.createContentsEmpty(super.getClazz()).fields);
		model.addAttribute("page", pageName);

		return "base/show";
	}
	
	@RequestMapping(path = "/update/{id}", method = RequestMethod.GET)
	public String updateView(Model model,@PathVariable long id){
		
		T item = (T) baseCrud.findOne(id);

		String pageName = "Update "+ baseName + " n° "+ id;
		
		model.addAttribute("item", DumpFields.fielder(item));
		model.addAttribute("fields", DumpFields.createContentsEmpty(super.getClazz()).fields);
		model.addAttribute("page", pageName);
		model.addAttribute("path", baseName.toLowerCase());

		return "base/update";
	}
	
	@RequestMapping(path = "/delete/{id}", method = RequestMethod.GET)
	public String deleteView(Model model,@PathVariable long id){
		
		T item = (T) baseCrud.findOne(id);

		String pageName = "Delete "+ baseName + " n° "+ id;
		
		model.addAttribute("item", DumpFields.fielder(item));
		model.addAttribute("fields", DumpFields.createContentsEmpty(super.getClazz()).fields);
		model.addAttribute("page", pageName);
		model.addAttribute("path", baseName.toLowerCase());

		return "base/delete";
	}
	
	@RequestMapping(path = "/create/", method = RequestMethod.GET)
	public String createView(Model model){

		String pageName = "Create a "+ baseName;
				
		model.addAttribute("fields", DumpFields.createContentsEmpty(super.getClazz()).fields);
		model.addAttribute("page", pageName);
		model.addAttribute("path", baseName.toLowerCase());

		return "base/create";
	}
	
	@RequestMapping(path = "/create/do", method = RequestMethod.POST)
	public String create(Model model, T item){

		insertItem(item);
		
		ArrayList<T> items = (ArrayList<T>) baseCrud.findAll();	
		String pageName = baseName.toUpperCase() + "S";	
		model.addAttribute("items", DumpFields.listFielder(items));
		model.addAttribute("fields", DumpFields.createContentsEmpty(super.getClazz()).fields);
		model.addAttribute("page", pageName);

		return "base/list";
	}
	
	@RequestMapping(path = "/update/do", method = RequestMethod.POST)
	public String update(Model model, T item){

		updateItem(item);
		
		ArrayList<T> items = (ArrayList<T>) baseCrud.findAll();	
		String pageName = baseName.toUpperCase() + "S";	
		model.addAttribute("items", DumpFields.listFielder(items));
		model.addAttribute("fields", DumpFields.createContentsEmpty(super.getClazz()).fields);
		model.addAttribute("page", pageName);

		return "base/list";
	}
	
	@RequestMapping(path = "/delete/do", method = RequestMethod.POST)
	public String delete(Model model, T item){

		deleteItem(item);
		
		ArrayList<T> items = (ArrayList<T>) baseCrud.findAll();	
		String pageName = baseName.toUpperCase() + "S";	
		model.addAttribute("items", DumpFields.listFielder(items));
		model.addAttribute("fields", DumpFields.createContentsEmpty(super.getClazz()).fields);
		model.addAttribute("page", pageName);

		return "base/list";
	}
	
	@RequestMapping(path = "/update/{id}/{child}", method = RequestMethod.GET)
	public String updateChildView(Model model,@PathVariable String child,@PathVariable Long id){
		
		T item = (T) baseCrud.findOne(id);

		String pageName = "Update "+ baseName + " n° "+ id + " children of type : "+ child;
		
		model.addAttribute("item", DumpFields.fielder(item));
		model.addAttribute("field", child);
		model.addAttribute("page", pageName);
		model.addAttribute("path", baseName.toLowerCase());

		return "base/updateChild";
	}
	
	@Autowired
	private IBaseCrudRepository<T> baseCrud;
	
	public ViewBaseController(Class<T> clazz) {
		super(clazz);
		
		this.baseName = this.getClazz().getSimpleName();
		
	}

}
