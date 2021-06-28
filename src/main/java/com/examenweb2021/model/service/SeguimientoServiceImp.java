package com.examenweb2021.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.examenweb2021.model.dao.ISeguimientoDao;
import com.examenweb2021.model.entities.Seguimiento;


@Service
public class SeguimientoServiceImp<T> implements IGenericService2<T> {
	
	@Autowired
	private ISeguimientoDao seguimientoDao;
		
	@Transactional(readOnly = true)
	@Override
	public T findOne(Integer id) {		
		// TODO Auto-generated method stub
		return (T) seguimientoDao.findById(id).orElse(null);
	}

	@Transactional(readOnly = true)
	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		return (List<T>) seguimientoDao.findAll();
	}

	@Transactional
	@Override
	public void save(T entity) {
		// TODO Auto-generated method stub		
		seguimientoDao.save((Seguimiento)entity);		
	}
	
	@Transactional
	@Override
	public void delete(T entity) {
		// TODO Auto-generated method stub
		seguimientoDao.delete((Seguimiento) entity);
		
	}

	@Transactional
	@Override
	public void deleteById(Integer entityId) {
		// TODO Auto-generated method stub
		seguimientoDao.deleteById(entityId);
	}

	

}
