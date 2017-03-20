package com.poeicgi.nikosmileweb.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poeicgi.nikosmileweb.dao.base.IBaseCrudRepository;
import com.poeicgi.nikosmileweb.models.Mood;

public interface IMoodCrudRepository extends IBaseCrudRepository<Mood>{

	 @Query("SELECT COUNT(*) FROM Mood AS mood WHERE mood.satisfaction = :satisfaction " )
	    public int findSatisaction(@Param("satisfaction")int satisfaction);

}
