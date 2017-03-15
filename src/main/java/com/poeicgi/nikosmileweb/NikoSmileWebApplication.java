package com.poeicgi.nikosmileweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.poeicgi.nikosmileweb.controllers.MoodController;
import com.poeicgi.nikosmileweb.controllers.UserController;
import com.poeicgi.nikosmileweb.models.Mood;
import com.poeicgi.nikosmileweb.models.User;

@SpringBootApplication
public class NikoSmileWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(NikoSmileWebApplication.class, args);
				
	}
	
}
