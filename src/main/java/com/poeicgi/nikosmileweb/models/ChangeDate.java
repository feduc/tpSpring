package com.poeicgi.nikosmileweb.models;

import java.util.Date;

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
	public static final String[] FIELDS = {"id","changeDate"};
	
	@Column(name = "date_modif")
	private Date changeDate;
	
	@ManyToOne(targetEntity=Mood.class)
	private Mood mood;

	public Date getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}

	public ChangeDate() {
		super(ChangeDate.TABLE, ChangeDate.FIELDS);
	}

	
}
