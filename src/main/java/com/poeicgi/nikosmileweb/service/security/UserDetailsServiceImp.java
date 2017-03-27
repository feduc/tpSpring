package com.poeicgi.nikosmileweb.service.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poeicgi.nikosmileweb.dao.ISecurityUserCrudRepository;
<<<<<<< HEAD
import com.poeicgi.nikosmileweb.dao.IUserCrudRepository;
=======
>>>>>>> c79c90bbed6d0157adab534d48c89dfab99db84a
import com.poeicgi.nikosmileweb.models.security.SecurityRole;
import com.poeicgi.nikosmileweb.models.security.SecurityUser;

@Service
<<<<<<< HEAD
@Transactional(readOnly = true)
public class UserDetailsServiceImp implements UserDetailsService {

	@Autowired
	private ISecurityUserCrudRepository securityUserCrud;

	@Override
	public UserDetails loadUserByUsername(String login)
			throws UsernameNotFoundException {
		SecurityUser security = securityUserCrud.findByLogin(login);

		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

		if (security.getEnable()){
			for (SecurityRole role : security.getRoles()){
				grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
			}
		}
		return new org.springframework.security.core.userdetails.User(security.getLogin(),
				security.getPassword(), grantedAuthorities);
	}
}
=======
public class UserDetailsServiceImp implements UserDetailsService {

	@Autowired
	private ISecurityUserCrudRepository secuCrud;

	// this method is called when an authentication is attempted using the form
	// in /login
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		SecurityUser security = secuCrud.findByLogin(login);

		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

		if (security.getEnable()) {
			for (SecurityRole role : security.getRoles()) {
				grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
			}
		}
		return new org.springframework.security.core.userdetails.User(security.getLogin(), security.getPassword(),
				grantedAuthorities);
	}

}
>>>>>>> c79c90bbed6d0157adab534d48c89dfab99db84a
