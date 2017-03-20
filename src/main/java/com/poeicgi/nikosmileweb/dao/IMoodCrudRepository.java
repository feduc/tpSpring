package com.poeicgi.nikosmileweb.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.poeicgi.nikosmileweb.dao.base.IBaseCrudRepository;
import com.poeicgi.nikosmileweb.models.Mood;

public interface IMoodCrudRepository extends IBaseCrudRepository<Mood>{

	 @Query("SELECT COUNT(*) FROM Mood mood WHERE mood.satisfaction = -1")
	    public int findBadSatisaction();

	 @Query("SELECT COUNT(*) FROM Mood mood WHERE mood.satisfaction = 0")
	    public int findNormalSatisaction();

	 @Query("SELECT COUNT(*) FROM Mood mood WHERE mood.satisfaction = 1")
	    public int findGoodSatisaction();
}
