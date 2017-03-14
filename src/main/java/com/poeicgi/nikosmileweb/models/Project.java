package com.poeicgi.nikosmileweb.models;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.poeicgi.nikosmileweb.models.modelbase.DataBaseItem;

@Entity
@Table(name="projet")
public class Project extends DataBaseItem {

	@Column(name="nom_projet")
	private String name;
	
	@Column(name="chef_de_projet")
	private String projectLeader;
	
	private String verticale;
	
	@Column(name="date_debut")
	private Date startDate;
	
	@Column(nullable=true,name ="date_fin")
	private Date endDate;
	
	@Column(name="anonyme")
	private Boolean isAnonymous;
	
	@Column(name="cache")
	private Boolean isHidden;
	
	@ManyToMany(targetEntity=User.class)
	private ArrayList<User> team;

	public String getProjectLeader() {
		return projectLeader;
	}

	public void setProjectLeader(String projectLeader) {
		this.projectLeader = projectLeader;
	}

	public void setVerticale(String verticale) {
		this.verticale = verticale;
	}

	public String getVerticale() {
		return verticale;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Boolean getIsAnonymous() {
		if (this.isAnonymous == null) {
			this.isAnonymous = true;
		}
		return isAnonymous;
	}

	public void setIsAnonymous(Boolean isAnonymous) {
		this.isAnonymous = isAnonymous;
	}

	public ArrayList<User> getTeam() {
		return team;
	}

	public void setTeam(ArrayList<User> team) {
		this.team = team;
	}

	public Boolean getIsHidden() {
		return isHidden;
	}

	public void setIsHidden(Boolean isHidden) {
		this.isHidden = isHidden;
	}
	
	public Project(String name, Date start_date) {
		this.name = name;
		this.startDate = start_date;
		this.team = new ArrayList<User>();
		for (User user : team) {
			user.getProjects().add(this);
		}
		this.setIsAnonymous(true);
		this.setIsHidden(false);
	}

	public Project(String name, Date start_date, Date end_date) {
		this.name = name;
		this.startDate = start_date;
		this.endDate = end_date;
		this.team = new ArrayList<User>();
		for (User user : team) {
			user.getProjects().add(this);
		}
		this.setIsHidden(false);
	}

	public Project() {
		this.team = new ArrayList<User>();
		for (User user : team) {
			user.getProjects().add(this);
		}
		this.setIsAnonymous(true);
		this.setIsHidden(false);

	}

}
