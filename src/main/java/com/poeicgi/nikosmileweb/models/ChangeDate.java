package com.poeicgi.nikosmileweb.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.poeicgi.nikosmileweb.models.modelbase.DataBaseItem;

@Entity
@Table(name="date_modif")
public class ChangeDate extends DataBaseItem{

	@Transient
	public static final String TABLE = "date_modif";

	@Transient
	public static final String[] FIELDS = { "id", "date_modif"};

	@Override
	public ArrayList<Map<String,Object>> getMyFields() {
		ArrayList<Map<String,Object>> myFields = new ArrayList<Map<String,Object>>();

		myFields.add(new HashMap<String,Object>());
		(myFields.get(0)).put("name", "id");
		(myFields.get(0)).put("type", "Long");
		myFields.add(new HashMap<String,Object>());
		(myFields.get(1)).put("name", "changeDate");
		(myFields.get(1)).put("type", "Date");

		return myFields;
	}

	@Column(name = "date_modif", nullable = false)
	private Date changeDate;

	@ManyToOne(targetEntity=Mood.class)
	private Mood mood;

	public Date getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}

	public Mood getMood() {
		return mood;
	}

	public void setMood(Mood mood) {
		this.mood = mood;
	}

	public ChangeDate() {
		super(ChangeDate.TABLE, ChangeDate.FIELDS);
	}


}
