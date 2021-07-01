package com.examenweb2021.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.examenweb2021.model.entities.Typedb;
import com.examenweb2021.model.service.TypedbServiceImp;

@Controller
public class AdminController {
	
	@Autowired
	private TypedbServiceImp<Typedb> typedbDao;

	
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
			model.addAttribute("titulo","crear cliente");
			return "redirect:nuevoCliente";
		}
		typedbDao.save(typedb);
		return "redirect:index";
	}

	@GetMapping(value = "/tiposDeBases")
	public String registarToken(Map<String, Object>model) {
		Typedb db= new Typedb();
		model.put("TipoDB", db);
		model.put("titulo","Tipos de bases de datos");
		return "nuevoTipoDB";
	}
	
	
	@RequestMapping(value = "/crearTipoDB")
	public String guardarToken(Map<String, Object>model) {
		Typedb db= new Typedb();
		model.put("TipoDB", db);
		model.put("titulo","Tipos de bases de datos");
		
		return "crearTipoDB";
	}
	
	@RequestMapping(value = "/crearTipoDB", method = RequestMethod.POST)
	public String guardarT(@Validated Typedb typedb, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			model.addAttribute("titulo","crear cliente");
			return "redirect:nuevoCliente";
		}
		typedbDao.save(typedb);
		return "redirect:index";
	}
	
	
}
