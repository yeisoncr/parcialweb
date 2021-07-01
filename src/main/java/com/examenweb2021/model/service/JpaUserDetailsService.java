package com.examenweb2021.model.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.examenweb2021.model.dao.IUsuarioDao;
import com.examenweb2021.model.entities.Rol;
import com.examenweb2021.model.entities.Usuario;



@Service("jpaUserDetailsService")
public class JpaUserDetailsService implements UserDetailsService {

	@Autowired
	private IUsuarioDao usuarioDao;

	private Logger logger = LoggerFactory.getLogger(JpaUserDetailsService.class);

	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
		
        Usuario user =(Usuario) usuarioDao.findByUsuario(usuario);
        
        if(user == null) {
        	System.out.println("usuario null");
        	return null;
        }
        
        if(user.getState()==0) {
        	return null;
        }
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        
        Rol role= user.getRol();
        	logger.info("Role: ".concat(role.getDescription()));
        	authorities.add(new SimpleGrantedAuthority(role.getDescription()));        	              
		return new User(user.getUsuario(), user.getPass(), true, true, true, true, authorities);
	}

}
