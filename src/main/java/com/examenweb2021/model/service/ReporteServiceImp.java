package com.examenweb2021.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.examenweb2021.model.dao.IReporteDao;
import com.examenweb2021.model.entities.Reporte;


@Service
public class ReporteServiceImp<T> implements IGenericService2<T> {
	
	@Autowired
	private IReporteDao reporteDao;
		
	@Transactional(readOnly = true)
	@Override
	public T findOne(Integer id) {		
		// TODO Auto-generated method stub
		return (T) reporteDao.findById(id).orElse(null);
	}

	@Transactional(readOnly = true)
	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		return (List<T>) reporteDao.findAll();
	}

	@Transactional
	@Override
	public void save(T entity) {
		// TODO Auto-generated method stub		
		reporteDao.save((Reporte)entity);		
	}
	
	@Transactional
	@Override
	public void delete(T entity) {
		// TODO Auto-generated method stub
		reporteDao.delete((Reporte) entity);
		
	}

	@Transactional
	@Override
	public void deleteById(Integer entityId) {
		// TODO Auto-generated method stub
		reporteDao.deleteById(entityId);
	}

	

}
