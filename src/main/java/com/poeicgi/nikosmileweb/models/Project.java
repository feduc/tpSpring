package com.poeicgi.nikosmileweb.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.poeicgi.nikosmileweb.models.modelbase.DataBaseItem;

@Entity
@Table(name = "projet")
public class Project extends DataBaseItem {

	@Transient
	public static final String TABLE = "projet";

	@Transient
	public static final String[] FIELDS = { "id", "nom_projet", "verticale", "date_debut", "date_fin", "projet_anonyme",
			"projet_cache" };

	@Override
	public ArrayList<Map<String, Object>> getMyFields() {
		ArrayList<Map<String, Object>> myFields = new ArrayList<Map<String, Object>>();

		myFields.add(new HashMap<String, Object>());
		(myFields.get(0)).put("name", "id");
		(myFields.get(0)).put("type", "Long");
		myFields.add(new HashMap<String, Object>());
		(myFields.get(1)).put("name", "name");
		(myFields.get(1)).put("type", "String");
		myFields.add(new HashMap<String, Object>());
		(myFields.get(2)).put("name", "verticale");
		(myFields.get(2)).put("type", "String");
		myFields.add(new HashMap<String, Object>());
		(myFields.get(3)).put("name", "startDate");
		(myFields.get(3)).put("type", "Date");
		myFields.add(new HashMap<String, Object>());
		(myFields.get(4)).put("name", "endDate");
		(myFields.get(4)).put("type", "Date");
		myFields.add(new HashMap<String, Object>());
		(myFields.get(5)).put("name", "isAnonymous");
		(myFields.get(5)).put("type", "boolean");
		myFields.add(new HashMap<String, Object>());
		(myFields.get(6)).put("name", "isHidden");
		(myFields.get(6)).put("type", "boolean");

		return myFields;
	}

	@Column(name = "nom_projet")
	private String name;

	@ManyToOne(targetEntity = User.class)
	private User projectLeader;

	private String verticale;

	@Column(name = "date_debut")
	private Date startDate;

	@Column(nullable = true, name = "date_fin")
	private Date endDate;

	@Column(name = "projet_anonyme")
	private Boolean isAnonymous;

	@Column(name = "projet_cache")
	private Boolean isHidden;

	@ManyToMany(targetEntity = User.class)
	private Set<User> team;

	public User getProjectLeader() {
		return projectLeader;
	}

	public void setProjectLeader(User projectLeader) {
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

	public Set<User> getTeam() {
		
			return team;
		
	}

	public void setTeam(Set<User> team) {
		this.team = team;
	}

	public Boolean getIsHidden() {
		return isHidden;
	}

	public void setIsHidden(Boolean isHidden) {
		this.isHidden = isHidden;
	}

	public Project(String name, Date start_date) {
		this();
		this.name = name;
		this.startDate = start_date;

	}

	public Project(String name, Date start_date, Date end_date) {
		this(name, start_date);
		this.endDate = end_date;
	}

	public Project() {

		super(Project.TABLE, Project.FIELDS);
		this.setIsAnonymous(true);
		this.setIsHidden(false);

		// for (User user : team) {
		// user.getProjects().add(this);
		// }

	}

}
