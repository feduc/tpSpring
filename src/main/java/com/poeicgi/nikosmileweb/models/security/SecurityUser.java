package com.poeicgi.nikosmileweb.models.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
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
	public static final String[] FIELDS = {"id","login", "password", "status"};

	private String login;
	
	@Column(name="mot_de_passe")
	private String password;
	
	@Column(name="statut")
	private String status;
	
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
	
	public SecurityUser(String login, String password) {
		this();
		this.login = login;
		this.password = password;
	}

	public SecurityUser() {
		super(SecurityUser.TABLE, SecurityUser.FIELDS);
	}
}

