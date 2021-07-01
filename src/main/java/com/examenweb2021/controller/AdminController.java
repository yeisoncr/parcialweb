package com.examenweb2021.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.examenweb2021.model.entities.Connectiontoken;
import com.examenweb2021.model.entities.Typedb;
import com.examenweb2021.model.entities.Usuario;
import com.examenweb2021.model.service.ConnectiontokenServiceImp;
import com.examenweb2021.model.service.TypedbServiceImp;
import com.examenweb2021.model.service.UsuarioServiceImp;

@Controller
public class AdminController {
	
	@Autowired
	private TypedbServiceImp<Typedb> typedbDao;
	
	@Autowired
	private ConnectiontokenServiceImp<Connectiontoken> connectionTokenDao;
	
	@Autowired
	private UsuarioServiceImp<Usuario> usuarioDao;

	
	@GetMapping(value = "/tiposDeBases")
	public String registarTiposDB(Map<String, Object>model) {
		Typedb db= new Typedb();
		model.put("TipoDB", db);
		model.put("titulo","Tipos de bases de datos");
		return "nuevoTipoDB";
	}
	
	
	@RequestMapping(value = "/crearTipoDB")
	public String guardarTypeDB(Map<String, Object>model) {
		Typedb db= new Typedb();
		model.put("TipoDB", db);
		model.put("titulo","Tipos de bases de datos");
		
		return "crearTipoDB";
	}
	
	@RequestMapping(value = "/crearTipoDB", method = RequestMethod.POST)
	public String guardar(@Validated Typedb typedb, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			model.addAttribute("titulo","crear tipo db");
			return "redirect:tiposDeBases";
		}
		typedbDao.save(typedb);
		return "redirect:index";
	}

	@GetMapping(value = "/nuevoToken")
	public String registarToken(Map<String, Object>model) {
		Connectiontoken tk= new Connectiontoken();
		model.put("token", tk);
		model.put("titulo","Token");
		model.put("tipoDB", typedbDao.findAll());
		return "nuevoToken";
	}
	
	
	@RequestMapping(value = "/crearToken", method = RequestMethod.POST)
	public String guardarToken(@RequestParam(name = "host") String host,
			@RequestParam(name = "userdb") String userdb,
			@RequestParam(name = "pass") String pass,
			@RequestParam(name = "db") String db,
			@RequestParam(name = "token") String token,
			@RequestParam(name = "port") String port,
			@RequestParam(name = "user") String user,
			@RequestParam(name = "estado") String estado,
			@RequestParam(name = "type") String type) {
		
		Integer ti=Integer.parseInt(type);
		Connectiontoken tk= new Connectiontoken();
		Typedb t = typedbDao.findOne(ti);
		Usuario u =usuarioDao.findByUsuario(user);
		
		tk.setHost(host);
		tk.setUserdb(userdb);
		tk.setPass(pass);
		tk.setDb(db);
		tk.setToken(token);
		tk.setPort((short)Integer.parseInt(port));
		tk.setState((short)Integer.parseInt(estado));
		tk.setTypedb(t);
		
		connectionTokenDao.save(tk);
		
		return null;
	}
	
	
	
}
