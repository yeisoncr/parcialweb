package com.examenweb2021.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.examenweb2021.model.dao.ITypedbDao;
import com.examenweb2021.model.entities.Typedb;


@Service
public class TypedbServiceImp<T> implements IGenericService2<T> {
	
	@Autowired
	private ITypedbDao typedbdbDao;
		
	@Transactional(readOnly = true)
	@Override
	public T findOne(Integer id) {		
		// TODO Auto-generated method stub
		return (T) typedbdbDao.findById(id).orElse(null);
	}

	@Transactional(readOnly = true)
	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		return (List<T>) typedbdbDao.findAll();
	}

	@Transactional
	@Override
	public void save(T entity) {
		// TODO Auto-generated method stub		
		typedbdbDao.save((Typedb)entity);		
	}
	
	@Transactional
	@Override
	public void delete(T entity) {
		// TODO Auto-generated method stub
		typedbdbDao.delete((Typedb) entity);
		
	}

	@Transactional
	@Override
	public void deleteById(Integer entityId) {
		// TODO Auto-generated method stub
		typedbdbDao.deleteById(entityId);
	}

	

}
