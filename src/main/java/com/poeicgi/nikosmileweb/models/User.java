package com.poeicgi.nikosmileweb.models;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.poeicgi.nikosmileweb.models.security.SecurityUser;
import com.poeicgi.nikosmileweb.models.Project;
import com.poeicgi.nikosmileweb.models.Mood;
import com.poeicgi.nikosmileweb.models.User;

@Entity
@Table(name="utilisateur")
public class User extends SecurityUser{
	
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
	private ArrayList<Mood> moods;
	
	@ManyToMany(targetEntity=Project.class)
	private ArrayList<Project> projects;

	
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
	
	public ArrayList<Project> getProjects() {
		return projects;
	}
	
	public void setProjects(ArrayList<Project> projects) {
		this.projects = projects;
	}
	
	public ArrayList<Mood> getMoods() {
		return moods;
	}
	
	public void setMoods(ArrayList<Mood> moods) {
		this.moods = moods;
	}

	public User(String login, String password, String lastname, String firstname, String registration_cgi) {
		super(login, password);
		this.lastName = lastname;
		this.firstName = firstname;
		this.registrationCGI = registration_cgi;
		this.projects = new ArrayList<Project>();
	}
	
	public User() {
		this.projects = new ArrayList<Project>();
	}
	
	

}

