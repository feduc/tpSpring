package com.poeicgi.nikosmileweb.models.modelbase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
public abstract class DataBaseItem {
	
	@Transient
	public String table;
	@Transient
	public ArrayList<Map<String,Object>> myFields = new ArrayList<Map<String,Object>>();
	@Transient
	public String[] dbfields;

//	public ArrayList<Map<String, Object>> getMyFields() {
//		return myFields;
//	}

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		if (id>0){
			this.id = id;
		}else {
			this.id = 0;
		}
		
	}

	public DataBaseItem(String table) {
		this.table = table;
	}	
	
	public abstract ArrayList<Map<String,Object>> getMyFields();
	
}