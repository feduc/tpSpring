package com.poeicgi.nikosmileweb.models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.poeicgi.nikosmileweb.models.base.DataBaseItem;

@Entity
@Table(name = "avis")
public class Mood extends DataBaseItem {

	@Column(nullable = true, name = "avis_journee")
	private int satisfaction;

	@Column(nullable = true, name = "date_jour")
	private Date logDate;

	@Column(nullable = true, name = "texte_jour")
	private String commentSat;

	@OneToMany(targetEntity = ChangeDate.class)
	private ArrayList<Date> changeDates;

	@ManyToOne(targetEntity = User.class)
	private User user;

	private static class NikoNikoSatisfaction {

		public static final int[] satisfactionItems = { 1, 2, 3 };
		public static final int defaultSatisfactionError = 0;

		public static Boolean inSatisfactionItems(int satisfaction) {
			Boolean flag = false;
			for (int i = 0; i < satisfactionItems.length; i++) {
				if (satisfaction == satisfactionItems[i]) {
					flag = true;
					break;
				}
			}
			return flag;
		}

		public static int satisfactionRule(int satisfaction) {
			if (inSatisfactionItems(satisfaction)) {
				return satisfaction;
			} else {
				String error = "Error satisfaction not in ";
				for (int i = 0; i < satisfactionItems.length - 1; i++) {
					error += satisfactionItems[i] + ", ";
				}
				error += satisfactionItems[satisfactionItems.length - 1] + ".";
				System.err.println(error);
				return defaultSatisfactionError;
			}
		}

	}

	public Date getLogDate() {
		if (this.logDate == null) {
			this.logDate = new Date();
		}
		return logDate;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

	public ArrayList<Date> getChangeDates() {
		return changeDates;
	}

	public void setChangeDates(ArrayList<Date> changeDates) {
		this.changeDates = changeDates;
	}

	public int getSatisfaction() {
		return satisfaction;
	}

	public void setSatisfaction(int satisfaction) {
		if (Mood.nikoCheck(satisfaction)) {
			this.satisfaction = satisfaction;
		} else {
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
		if (Mood.nikoCheck(satisfaction)) {
			this.satisfaction = satisfaction;
		} else {
			this.satisfaction = 0;
		}
		this.user = user;
		user.getMoods().add(this);
	}

	public Mood(int satisfaction, String commentSat) {
		super();
		this.logDate = new Date();
		if (Mood.nikoCheck(satisfaction)) {
			this.satisfaction = satisfaction;
		} else {
			this.satisfaction = 0;
		}
		this.commentSat = commentSat;
	}

	public Mood(int satisfaction) {
		super();
		this.logDate = new Date();
		if (Mood.nikoCheck(satisfaction)) {
			this.satisfaction = satisfaction;
		} else {
			this.satisfaction = 0;
		}
	}

	public Mood(User user, Project project, int satisfaction) {
		this();
		this.user = user;
		this.setSatisfaction(satisfaction);
		this.logDate = new Date();
		this.user.getMoods().add(this);
	}

	public Mood(User user, Project project, int satisfaction, String comment) {
		this(user, project, satisfaction);
		this.commentSat = comment;
	}

	public Mood() {

	}

	@Override
	public String toString() {
		return "Avis [log_date=" + logDate + ", change_date=" + changeDates
				+ ", satisfaction=" + satisfaction + ", comment=" + commentSat
				+ "]";
	}

	public static boolean nikoCheck(int intgiven) {

		ArrayList<Integer> test = new ArrayList<Integer>();
		test.add(1);
		test.add(0);
		test.add(-1);
		if (test.contains(intgiven)) {
			return true;
		} else {
			return false;
		}

	}

}
