package com.poeicgi.nikosmileweb.models.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.poeicgi.nikosmileweb.models.Mood;
import com.poeicgi.nikosmileweb.models.modelbase.DataBaseItem;

@Entity
@Table(name="securite")
public class SecurityUser extends DataBaseItem {


	@Transient
	public static final String TABLE = "securite";

	@Transient
	public static final String[] FIELDS =  {"id", "login", "mot_de_passe","enable"};

	public ArrayList<Map<String,Object>> getMyFields() {
		ArrayList<Map<String,Object>> myFields = new ArrayList<Map<String,Object>>();

		myFields.add(new HashMap<String,Object>());
		(myFields.get(0)).put("name", "id");
		(myFields.get(0)).put("type", "Long");
		myFields.add(new HashMap<String,Object>());
		(myFields.get(1)).put("name", "login");
		(myFields.get(1)).put("type", "String");
		myFields.add(new HashMap<String,Object>());
		(myFields.get(2)).put("name", "password");
		(myFields.get(2)).put("type", "String");
		myFields.add(new HashMap<String,Object>());
		(myFields.get(3)).put("name", "enable");
		(myFields.get(3)).put("type", "Boolean");

		return myFields;
	}

	@Column(unique = true, nullable = false)
	private String login;

	@Column(name = "mot_de_passe", nullable = false)
	private String password;

	@Column(nullable = false)
	private Boolean enable;

	@ManyToMany
	private Set<SecurityRole> roles;

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login
	 *            the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public Set<SecurityRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<SecurityRole> roles) {
		this.roles = roles;
	}

	public SecurityUser(String login, String password) {
		this();
		this.login = login;
		this.password = password;
	}

	public SecurityUser() {
		super(SecurityUser.TABLE, SecurityUser.FIELDS);
	}
}

