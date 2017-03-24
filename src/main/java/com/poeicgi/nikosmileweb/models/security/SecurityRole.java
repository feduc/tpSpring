package com.poeicgi.nikosmileweb.models.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.poeicgi.nikosmileweb.models.User;
import com.poeicgi.nikosmileweb.models.modelbase.DataBaseItem;

@Entity
@Table(name= SecurityRole.TABLE)
public class SecurityRole extends DataBaseItem{
	
	@Transient
	public static final String TABLE = "security_role";
	
	@Transient
	public static final String[] FIELDS = { "id", "role"};

	private String role;
	
	@ManyToMany
	@JoinTable(name = "users_securityroles",
				joinColumns = @JoinColumn(name= "security_id"),
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<SecurityUser> securities;
	
	public ArrayList<Map<String,Object>> getMyFields() {
		ArrayList<Map<String,Object>> myFields = new ArrayList<Map<String,Object>>();
		
		myFields.add(new HashMap<String,Object>());
		(myFields.get(0)).put("name", "id");
		(myFields.get(0)).put("type", "Long");
		myFields.add(new HashMap<String,Object>());
		(myFields.get(1)).put("name", "role");
		(myFields.get(1)).put("type", "String");
		
		return myFields;
	}
	
	
	
	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}



	public Set<SecurityUser> getSecurities() {
		return securities;
	}



	public void setSecurities(Set<SecurityUser> securities) {
		this.securities = securities;
	}



	public SecurityRole(String table,String[] fields, String role) {
		super(table,fields);
		
		this.role = role;
	}
	
	public SecurityRole() {
		super(SecurityRole.TABLE, SecurityRole.FIELDS);
	}
}