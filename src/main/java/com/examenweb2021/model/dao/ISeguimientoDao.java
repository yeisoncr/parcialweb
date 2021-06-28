package com.examenweb2021.model.dao;

import org.springframework.data.repository.CrudRepository;
import com.examenweb2021.model.entities.Seguimiento;


public interface ISeguimientoDao extends CrudRepository <Seguimiento, Integer>{;
}
