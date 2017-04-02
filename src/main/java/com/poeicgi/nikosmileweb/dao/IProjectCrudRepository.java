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
				+ " WHERE project.name like :name ")
		List<Project> findProjectsByName(@Param("name") String name);

		@Query("SELECT project FROM Project project "
				+ " WHERE project.name = :name ")
		Project findExactProjectByName(@Param("name") String name);

		@Query("SELECT project.startDate FROM Project project "
				+ " WHERE project.name = :name ")
		Date findProjectStartDatebyName(@Param("name") String name);

		@Query("SELECT project.endDate FROM Project project "
				+ " WHERE project.name = :name ")
		Date findProjectEndDatebyName(@Param("name") String name);

		@Query("SELECT project.projectLeader FROM Project project "
				+ " WHERE project.name = :name")
		User findProjectsLeaderByName(@Param("name") String name);

		@Query("SELECT project.isHidden FROM Project project "
				+ " WHERE project.name = :name")
		Boolean findHiddenStatusbyName(@Param("name") String name);

		@Query("SELECT project.isAnonymous FROM Project project "
				+ " WHERE project.name = :name")
		Boolean findAnonymousStatusbyName(@Param("name") String name);

}
