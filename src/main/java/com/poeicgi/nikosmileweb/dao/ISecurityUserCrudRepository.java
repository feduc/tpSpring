package com.poeicgi.nikosmileweb.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poeicgi.nikosmileweb.dao.base.IBaseCrudRepository;
import com.poeicgi.nikosmileweb.models.security.SecurityUser;

public interface ISecurityUserCrudRepository extends IBaseCrudRepository<SecurityUser>{

	SecurityUser findByLogin(String login);


}
