package com.poeicgi.nikosmileweb.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="date_modif")
public class ChangeDate {
	
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

}
