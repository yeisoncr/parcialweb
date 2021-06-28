package com.examenweb2021.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.examenweb2021.model.entities.Usuario;
import com.examenweb2021.model.entities.Rol;
import com.examenweb2021.model.service.RolServiceImp;
import com.examenweb2021.model.service.UsuarioServiceImp;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioServiceImp<Usuario> usuarioService;

	@Autowired
	private RolServiceImp<Rol> rolService;

	@RequestMapping(value = {"/","/login"})
	public String n() {
		return "login";
	}

	@RequestMapping(value = "/nuevoUsuario")
	public String nuevoUsuario(Model model) {
		model.addAttribute("roles", rolService.findAll());
		return "nuevoUsuario";
	}

	@RequestMapping(value = "/registrarUsuario")
	public String registrar(@RequestParam("username") String usuario, 
			@RequestParam("password") String password,
			@RequestParam("tipoU") String tipo, @RequestParam("email") String email) {

		if (usuarioService.findByUsuario(usuario) == null) {
			Usuario u = new Usuario();
			Rol r = rolService.findOne(Integer.parseInt(tipo));

			u.setUsuario(usuario);
			u.setEmail(email);
			u.setRol(r);
			u.setState((short) 0);
			u.setPass(password);
			u.setRol(r);
			usuarioService.save(u);
			return "redirect:/login";
		}

		return "redirect:nuevoUsuario";
	}

}
