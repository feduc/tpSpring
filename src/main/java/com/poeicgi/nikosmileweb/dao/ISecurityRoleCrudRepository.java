package com.poeicgi.nikosmileweb.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poeicgi.nikosmileweb.dao.base.IBaseCrudRepository;
import com.poeicgi.nikosmileweb.models.security.SecurityRole;
import com.poeicgi.nikosmileweb.models.security.SecurityUser;

public interface ISecurityRoleCrudRepository extends IBaseCrudRepository<SecurityRole>{

	@Query("SELECT role.role FROM SecurityRole AS role "
			+ "	JOIN role.securities AS secu"
			+ " WHERE secu = :secu ")
	List<String> getRolesForSecurityUser(@Param("secu") SecurityUser secu);
}
