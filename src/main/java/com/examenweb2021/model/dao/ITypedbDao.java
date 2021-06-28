package com.examenweb2021.model.dao;

import org.springframework.data.repository.CrudRepository;
import com.examenweb2021.model.entities.Typedb;


public interface ITypedbDao extends CrudRepository <Typedb, Integer>{
}
