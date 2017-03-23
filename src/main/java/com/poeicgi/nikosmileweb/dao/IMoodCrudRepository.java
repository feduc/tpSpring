package com.poeicgi.nikosmileweb.dao;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poeicgi.nikosmileweb.dao.base.IBaseCrudRepository;
import com.poeicgi.nikosmileweb.models.Mood;
import com.poeicgi.nikosmileweb.models.User;

public interface IMoodCrudRepository extends IBaseCrudRepository<Mood> {

	//creation d'une requete d'interrogation de la bdd par nom de projet et date
	@Query("SELECT mood FROM User user "
			+ "	JOIN user.moods AS mood"
			+ " JOIN user.projects AS project"
			+ " WHERE project.name = :name "
			+ " AND mood.voteDate = :date")
	List<Mood> findMoodsByProjectAndDate(@Param("name") String projectName, @Param("date") Date dateTest);

	//creation d'une requete d'interrogation de la bdd par nom de projet
	@Query("SELECT mood FROM User user "
			+ "	JOIN user.moods AS mood"
			+ " JOIN user.projects AS project"
			+ " WHERE project.name = :name ")
	List<Mood> findMoodsByProject(@Param("name") String projectName);

	//creation d'une requete d'interrogation de la bdd pour trouver la date du dernier vote
	@Query("SELECT max(mood.voteDate) FROM Mood AS mood"
			+ " WHERE mood.user = :user ")
	Date findLastVote(@Param("user") User user);

	//creation d'une requete d'interrogation de la bdd pour trouver l'id de la date du dernier vote
	@Query("SELECT id FROM Mood AS mood"
			+ " WHERE mood.user = :user AND mood.voteDate = :date")
	Long findLastVoteID(@Param("user") User user, @Param("date") Date date);

	//creation d'une requete d'interrogation de la bdd pour trouver la somme de toutes les satisfactions
	@Query("SELECT COUNT(*) FROM Mood AS mood WHERE mood.satisfaction = :satisfaction ")
	public int findSatisactionCount(@Param("satisfaction") int satisfaction);

	//creation d'une requete d'interrogation de la bdd pour trouver la satisfaction
	@Query("SELECT mood.satisfaction FROM Mood AS mood WHERE mood.user = :user AND mood.voteDate = :date")
	public int findSatisfaction(@Param("user") User user, @Param("date") Date date);;

	@Query("SELECT COUNT(*) FROM User user "
			+ "	JOIN user.moods AS mood"
			+ " JOIN user.projects AS project"
			+ " WHERE project.name = :name "
			+ " AND mood.voteDate = :date"
			+ " AND mood.satisfaction = :satis")
	int countMoodsBySatisfactionForSummary(@Param("name") String projectName,
										   @Param("date") Date dateTest,
										   @Param("satis") int satis);

	@Query("SELECT COUNT(mood) FROM User user "
			+ "	JOIN user.moods AS mood"
			+ " JOIN user.projects AS project"
			+ " WHERE project.name = :name "
			+ " AND mood.voteDate = :date")
	Float countMoodsByDate(@Param("name") String projectName,
										   @Param("date") Date dateTest);

	@Query("SELECT SUM(mood.satisfaction) FROM User user "
			+ "	JOIN user.moods AS mood"
			+ " JOIN user.projects AS project"
			+ " WHERE project.name = :name "
			+ " AND mood.voteDate = :date")
	Float sumMoodsByDate(@Param("name") String projectName,
										   @Param("date") Date dateTest);

}