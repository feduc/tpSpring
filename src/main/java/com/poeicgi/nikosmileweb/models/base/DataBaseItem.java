package com.poeicgi.nikosmileweb.models.base;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

//n'existe pas dans bdd 
@MappedSuperclass
public abstract class DataBaseItem {

	public String table;
	public String[] fields;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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

	public DataBaseItem(String table, String[] fields) {
		this.table = table;
		this.fields = fields;
	}

	public DataBaseItem() {
	}
}
