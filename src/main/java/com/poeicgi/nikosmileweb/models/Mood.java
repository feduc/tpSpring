package com.poeicgi.nikosmileweb.models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.poeicgi.nikosmileweb.models.modelbase.DataBaseItem;
import com.poeicgi.nikosmileweb.models.Mood;
import com.poeicgi.nikosmileweb.models.Project;
import com.poeicgi.nikosmileweb.models.User;

@Entity
@Table(name="avis")
public class Mood extends DataBaseItem {
	
	@Transient
	public static final String TABLE = "avis";
	@Transient
	public static final String[] FIELDS = {"id","satisfaction", "logDate", "commentSat", "user"};
	
	@Column(name="avis_journee")
	private int satisfaction;
	
	@Column(name="date_jour")
	private Date logDate;
	
	@Column(nullable=true, name="texte_jour")
	private String commentSat;
	
	@OneToMany(targetEntity=ChangeDate.class)
	private Set<ChangeDate> changeDates;
	
	@ManyToOne(targetEntity=User.class)
	private User user;

	
	public Date getLogDate() {
		if (this.logDate==null) {
			this.logDate= new Date();
		}
		return logDate;
	}
	
	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}
	
	public ArrayList<ChangeDate> getChangeDates() {
		if (changeDates == null) {
			return new ArrayList<ChangeDate>();
		}else {
		return (ArrayList<ChangeDate>) changeDates;
		}
	}
	
	public void setChangeDates(ArrayList<ChangeDate> changeDates) {
		this.changeDates = (Set<ChangeDate>) changeDates;
	}
	
	public int getSatisfaction() {
		return satisfaction;
	}
	
	public void setSatisfaction(int satisfaction) {
		if (Fonction.nikoCheck(satisfaction)){
			this.satisfaction = satisfaction;
		}else {
			this.satisfaction = 0;
		}
		
	}
	
	public String getCommentSat() {
		return commentSat;
	}
	
	public void setCommentSat(String comment) {
		this.commentSat = comment;
	}

	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public Mood(String logDate, int satisfaction, User user) {
		super();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			this.logDate = format.parse(logDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (Fonction.nikoCheck(satisfaction)){
			this.satisfaction = satisfaction;
		}else {
			this.satisfaction = 0;
		}
		this.user = user;
		user.getMoods().add(this);
	}
	
	public Mood(int satisfaction, String commentSat) {
		super();
		this.logDate= new Date();
		if (Fonction.nikoCheck(satisfaction)){
			this.satisfaction = satisfaction;
		}else {
			this.satisfaction = 0;
		}
		this.commentSat = commentSat;
	}
	
	public Mood(int satisfaction) {
		super();
		this.logDate= new Date();
		if (Fonction.nikoCheck(satisfaction)){
			this.satisfaction = satisfaction;
		}else {
			this.satisfaction = 0;
		}
	}
	
	public Mood(User user, int satisfaction) {
		this();
		this.user = user;
		this.setSatisfaction(satisfaction);
		this.logDate = new Date();
		this.user.getMoods().add(this);
	}

	public Mood(User user, int satisfaction, String comment) {
		this(user, satisfaction);
		this.commentSat = comment;
	}

	public Mood() {
		super(Mood.TABLE, Mood.FIELDS);
		this.logDate = new Date();
	}

	@Override
	public String toString() {
		return "Avis [log_date=" + logDate + ", change_date="
				+ changeDates + ", satisfaction=" + satisfaction + ", comment="
				+ commentSat + "]";
	}
	
	private static class Fonction {
		
		public static boolean nikoCheck(int intgiven) {
			
			ArrayList<Integer> test = new ArrayList<Integer>();
			test.add(1);
			test.add(0);
			test.add(-1);
			if (test.contains(intgiven)){
				return true;
			}else{
				return false;
			}
	
	}

	}

}



