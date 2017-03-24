package com.poeicgi.nikosmileweb.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poeicgi.nikosmileweb.dao.base.IBaseCrudRepository;
import com.poeicgi.nikosmileweb.models.Mood;
import com.poeicgi.nikosmileweb.models.Project;
import com.poeicgi.nikosmileweb.models.User;

public interface IProjectCrudRepository extends IBaseCrudRepository<Project>{

	//creation d'une requete d'interrogation de la bdd par nom de projet et date
		@Query("SELECT project.name FROM User user "
				+ " JOIN user.projects AS project"
				+ " WHERE user = :user "
				+ " AND (project.endDate > :date OR project.endDate IS NULL)")
		List<String> findActualProjectsByUser(@Param("user") User user, @Param("date") Date dateTest);

		@Query("SELECT project.name FROM User user "
				+ " JOIN user.projects AS project"
				+ " WHERE user = :user "
				+ " AND project.endDate <= :date")
		List<String> findOldProjectsByUser(@Param("user") User user, @Param("date") Date dateTest);
		
		@Query("SELECT project FROM Project project "
				+ " WHERE project.name like %:name% ")
		List<Project> findProjectByName(@Param("name") String name);
}
