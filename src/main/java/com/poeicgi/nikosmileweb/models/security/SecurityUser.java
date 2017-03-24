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
	public static final String[] FIELDS =  {"id", "login", "mot_de_passe", "statut", "enable"};
	
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
		(myFields.get(3)).put("name", "status");
		(myFields.get(3)).put("type", "String");
		myFields.add(new HashMap<String,Object>());
		(myFields.get(4)).put("name", "enable");
		(myFields.get(4)).put("type", "Boolean");
		
		return myFields;
	}

	private String login;
	
	@Column(name="mot_de_passe")
	private String password;
	
	@Column(name="statut")
	private String status;
	
	private Boolean enable;
	
	@ManyToMany
	@JoinTable(name = "users_securityroles",
				joinColumns = @JoinColumn(name= "role_id"),
				inverseJoinColumns = @JoinColumn(name = "security_id"))
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

	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

