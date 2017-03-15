package com.poeicgi.nikosmileweb.dao.base;

import org.springframework.data.repository.CrudRepository;

import com.poeicgi.nikosmileweb.models.modelbase.DataBaseItem;

public interface IBaseCrudRepository <T extends DataBaseItem> extends CrudRepository<T, Long>{

}
