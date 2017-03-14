package com.poeicgi.nikosmileweb.models.security;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Table;

import com.poeicgi.nikosmileweb.models.User;
import com.poeicgi.nikosmileweb.models.base.DataBaseItem;

@Table(name = "securite")
public class SecurityUser extends DataBaseItem {

	@Column(name = "login")
	private String login;

	@Column(name = "mot_de_passe")
	private String password;

	@Column(name = "statut")
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

	public SecurityUser(String table, String[] fields, String login, String password) {
		super(table, fields);
		this.login = login;
		this.password = password;
	}

	public SecurityUser(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public SecurityUser() {

	}


	public SecurityUser(String table, String[] fields) {
		super(table, fields);
	}
}

