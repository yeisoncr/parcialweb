package com.examenweb2021.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.examenweb2021.model.dao.IUsuarioDao;
import com.examenweb2021.model.entities.Usuario;

@Service
public class UsuarioServiceImp<T> implements IGenericServiceUser<T> {

	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Transactional(readOnly = true)
	@Override
	public T findByUsuario(String usuario) {		
		// TODO Auto-generated method stub
		return (T) usuarioDao.findByUsuario(usuario);
	}
	
	
	@Transactional(readOnly = true)
	@Override
	public T findOne(Integer id) {		
		// TODO Auto-generated method stub
		return (T) usuarioDao.findById(id).orElse(null);
	}

	@Transactional(readOnly = true)
	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		return (List<T>) usuarioDao.findAll();
	}

	@Transactional
	@Override
	public void save(T entity) {
		// TODO Auto-generated method stub		
		usuarioDao.save((Usuario)entity);		
	}
	
	@Transactional
	@Override
	public void delete(T entity) {
		// TODO Auto-generated method stub
		usuarioDao.delete((Usuario) entity);
		
	}

	@Transactional
	@Override
	public void deleteById(Integer entityId) {
		// TODO Auto-generated method stub
		usuarioDao.deleteById(entityId);
	}

	

}
