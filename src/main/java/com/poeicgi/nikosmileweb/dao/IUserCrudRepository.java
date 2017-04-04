package com.poeicgi.nikosmileweb.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poeicgi.nikosmileweb.dao.base.IBaseCrudRepository;
import com.poeicgi.nikosmileweb.models.User;

public interface IUserCrudRepository extends IBaseCrudRepository<User>{

	@Query("SELECT user FROM User user "
			+ "JOIN user.projects as project"
			+ " WHERE project.name= :name ")
	List<User> findMembersByProject(@Param("name") String name);

	@Query("SELECT user FROM User user "
			+ " WHERE user.registrationCGI like :registrationCGI ")
	List<User> findUsersByRegistration(@Param("registrationCGI") String registrationCGI);

	@Query("SELECT user FROM User user "
			+ " WHERE user.registrationCGI = :registrationCGI ")
	User findExactUserByRegistration(@Param("registrationCGI") String registrationCGI);



}
