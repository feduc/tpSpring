package com.poeicgi.nikosmileweb.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poeicgi.nikosmileweb.dao.base.IBaseCrudRepository;
import com.poeicgi.nikosmileweb.models.security.SecurityUser;

public interface ISecurityUserCrudRepository extends IBaseCrudRepository<SecurityUser>{

<<<<<<< HEAD
	SecurityUser findByLogin(String login);

=======
	//we can erase this method, the "findByLogin" does exactly the same
	@Query("SELECT security FROM SecurityUser security "
			+ " WHERE security.login = :login ")
	SecurityUser getSecurityTest(@Param("login") String login);
	
	SecurityUser findByLogin(String login);
>>>>>>> c79c90bbed6d0157adab534d48c89dfab99db84a

}
