package com.poeicgi.nikosmileweb.controllers.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.poeicgi.nikosmileweb.dao.base.IBaseCrudRepository;
import com.poeicgi.nikosmileweb.models.modelbase.DataBaseItem;

public abstract class BaseController <T extends DataBaseItem>{
	
	@Autowired
	private IBaseCrudRepository<T> baseCrud;
	
	private Class<T> clazz;
	
	public Class<T> getClazz() {
		return clazz;
	}

	public T insertItem(@ModelAttribute T item){
		baseCrud.save(item);
		return item;
		
	}
	
	public String updateItem(@ModelAttribute T item){
		try {
			baseCrud.save(item);
		} catch (Exception e) {
			return "Update failed";
		}
		return "Update done";
	}
	
	public String deleteItem(@ModelAttribute T item){
		try {
			baseCrud.delete(item);
		} catch (Exception e) {
			return "Delete failed";
		}
		return "Delete done";
	}
	
	public T getItem(long id){
		T item=null;
		item = baseCrud.findOne(id);
		return item;
	}
	
	public List<T> getItems(){
		List<T> items = null;
		items = (List<T>) baseCrud.findAll();
		return items;
	}

	
	public BaseController(Class<T> clazz) {
		this.clazz = clazz;
	}
}
