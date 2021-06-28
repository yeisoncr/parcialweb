package com.examenweb2021.model.dao;

import org.springframework.data.repository.CrudRepository;
import com.examenweb2021.model.entities.Usuario;


public interface IUsuarioDao extends CrudRepository <Usuario, Integer>{
	public Usuario findByUsername(String usuario);
}
