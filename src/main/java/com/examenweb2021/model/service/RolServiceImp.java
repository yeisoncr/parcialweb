package com.examenweb2021.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.examenweb2021.model.dao.IRolDao;
import com.examenweb2021.model.entities.Rol;


@Service
public class RolServiceImp<T> implements IGenericService2<T> {
	
	@Autowired
	private IRolDao rolDao;
		
	@Transactional(readOnly = true)
	@Override
	public T findOne(Integer id) {		
		// TODO Auto-generated method stub
		return (T) rolDao.findById(id).orElse(null);
	}

	@Transactional(readOnly = true)
	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		return (List<T>) rolDao.findAll();
	}

	@Transactional
	@Override
	public void save(T entity) {
		// TODO Auto-generated method stub		
		rolDao.save((Rol)entity);		
	}
	
	@Transactional
	@Override
	public void delete(T entity) {
		// TODO Auto-generated method stub
		rolDao.delete((Rol) entity);
		
	}

	@Transactional
	@Override
	public void deleteById(Integer entityId) {
		// TODO Auto-generated method stub
		rolDao.deleteById(entityId);
	}

	

}
