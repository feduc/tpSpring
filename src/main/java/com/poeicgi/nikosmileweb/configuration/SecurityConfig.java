package com.poeicgi.nikosmileweb.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableAutoConfiguration

@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//impose that to request anything you need to be authenticated
		http
				.authorizeRequests()
				.anyRequest()
				.authenticated()
			.and()
				.formLogin()
					.loginPage("/login")
					.usernameParameter("username")
					.passwordParameter("password")
					.permitAll()
					.defaultSuccessUrl("/security/login/do",true);
//			.and()
//				.authorizeRequests().anyRequest().anonymous()
//				.antMatchers("/demo","/demo/**")
//				.permitAll();
	}

}
