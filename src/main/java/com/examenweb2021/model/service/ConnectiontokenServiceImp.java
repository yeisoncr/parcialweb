package com.examenweb2021.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.examenweb2021.model.dao.IConnectiontokenDao;
import com.examenweb2021.model.entities.Connectiontoken;


@Service
public class ConnectiontokenServiceImp<T> implements IGenericService2<T> {
	
	@Autowired
	private IConnectiontokenDao connectiontokenDao;
		
	@Transactional(readOnly = true)
	@Override
	public T findOne(Integer id) {		
		// TODO Auto-generated method stub
		return (T) connectiontokenDao.findById(id).orElse(null);
	}

	@Transactional(readOnly = true)
	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		return (List<T>) connectiontokenDao.findAll();
	}

	@Transactional
	@Override
	public void save(T entity) {
		// TODO Auto-generated method stub		
		connectiontokenDao.save((Connectiontoken)entity);		
	}
	
	@Transactional
	@Override
	public void delete(T entity) {
		// TODO Auto-generated method stub
		connectiontokenDao.delete((Connectiontoken) entity);
		
	}

	@Transactional
	@Override
	public void deleteById(Integer entityId) {
		// TODO Auto-generated method stub
		connectiontokenDao.deleteById(entityId);
	}

	

}
