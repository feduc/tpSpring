package com.poeicgi.nikosmileweb.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.poeicgi.nikosmileweb.models.security.SecurityUser;
import com.poeicgi.nikosmileweb.models.Project;
import com.poeicgi.nikosmileweb.models.Mood;
import com.poeicgi.nikosmileweb.models.User;
import com.poeicgi.nikosmileweb.models.modelbase.DataBaseItem;

@Entity
@Table(name="utilisateur")
public class User extends DataBaseItem{

	@Transient
	public static final String TABLE = "utilisateur";

	@Transient
	public static final String[] FIELDS =  {"id", "nom", "prenom", "matricule_CGI", "verticale", "agence"};


	@Override
	public ArrayList<Map<String,Object>> getMyFields() {
		ArrayList<Map<String,Object>> myFields = new ArrayList<Map<String,Object>>();

		myFields.add(new HashMap<String,Object>());
		(myFields.get(0)).put("name", "id");
		(myFields.get(0)).put("type", "Long");
		myFields.add(new HashMap<String,Object>());
		(myFields.get(1)).put("name", "lastName");
		(myFields.get(1)).put("type", "String");
		myFields.add(new HashMap<String,Object>());
		(myFields.get(2)).put("name", "firstName");
		(myFields.get(2)).put("type", "String");
		myFields.add(new HashMap<String,Object>());
		(myFields.get(3)).put("name", "registrationCGI");
		(myFields.get(3)).put("type", "String");
		myFields.add(new HashMap<String,Object>());
		(myFields.get(4)).put("name", "verticale");
		(myFields.get(4)).put("type", "String");
		myFields.add(new HashMap<String,Object>());
		(myFields.get(5)).put("name", "agency");
		(myFields.get(5)).put("type", "String");

		return myFields;
	}

	@Column(name="nom")
	private String lastName;

	@Column(name="prenom")
	private String firstName;

	@Column(name="matricule_CGI")
	private String registrationCGI;

	private String verticale;

	@Column(name="agence")
	private String agency;

	@OneToMany(targetEntity=Mood.class)
	private Set<Mood> moods;

	@ManyToMany(targetEntity=Project.class)
	private Set<Project> projects;

	@OneToOne(targetEntity=SecurityUser.class)
	private SecurityUser security;


	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}


	public String getVerticale() {
		return verticale;
	}

	public void setVerticale(String verticale) {
		this.verticale = verticale;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getRegistrationCGI() {
		return registrationCGI;
	}

	public void setRegistrationCGI(String registrationCGI) {
		this.registrationCGI = registrationCGI;
	}

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects =  projects;
	}

	public Set<Mood> getMoods() {
		return moods;
	}

	public void setMoods(Set<Mood> moods) {
		this.moods = moods;
	}

	public SecurityUser getSecurity() {
		return security;
	}

	public void setSecurity(SecurityUser security) {
		this.security = security;
	}

	public User(String login, String password, String lastname, String firstname, String registration_cgi) {
		this();
		this.security.setLogin(login);
		this.security.setPassword(password);
		this.lastName = lastname;
		this.firstName = firstname;
		this.registrationCGI = registration_cgi;
	}

	public User() {
		super(User.TABLE, User.FIELDS);
	}

	@Override
	public String toString() {
		return "User [lastName=" + lastName + ", firstName=" + firstName + ", registrationCGI=" + registrationCGI
				+ ", verticale=" + verticale + ", agency=" + agency + "]";
	}



}

