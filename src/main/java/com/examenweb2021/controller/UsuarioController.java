package com.examenweb2021.controller;

import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.examenweb2021.model.entities.Usuario;
import com.examenweb2021.model.entities.Rol;
import com.examenweb2021.model.service.RolServiceImp;
import com.examenweb2021.model.service.SendMailService;
import com.examenweb2021.model.service.UsuarioServiceImp;



@Controller
public class UsuarioController {

	@Autowired
	private UsuarioServiceImp<Usuario> usuarioService;

	@Autowired
	private RolServiceImp<Rol> rolService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;	

	@Autowired
	private SendMailService sendMail;

	@RequestMapping(value = "/login")
	public String ingresar2(Map<String, Object> model, Principal principal) {
		if (principal != null) {
			return "redirect:index";
		}		
		return "login";
	}
	
	@RequestMapping(value = "/index")
	public String indes() {
		return "index";
	}
	
	@RequestMapping(value = "/ingresar")
	public String ingresar(Model model,@RequestParam("username") String usuario, 
			@RequestParam("password") String password) {
		Usuario u= usuarioService.findByUsuario(usuario);
		if(u!=null) {			
			if(!u.getPass().equalsIgnoreCase(password)) {				
				if(u.getState()==1) {
					System.out.println("correcto");
					return "redirect:login";
				}
				model.addAttribute("info","esta cuenta no ha sido activada");
				return "login";
			}
			model.addAttribute("info","usuario o contraseña incorrectos");
			return "login";
		}
		model.addAttribute("info","usuario o contraseña incorrectos");
		return "login";
	}

	@RequestMapping(value = "/nuevoUsuario")
	public String nuevoUsuario(Model model) {
		model.addAttribute("roles", rolService.findAll());
		return "nuevoUsuario";
	}


	@RequestMapping(value = "/registrarUsuario")
	public String registrar(@RequestParam("username") String usuario, 
			@RequestParam("password") String password
			, @RequestParam("email") String email,
			Model model) {

		if (usuarioService.findByUsuario(usuario) == null) {
			Usuario u = new Usuario();
			Rol r = rolService.findOne(2);

			u.setUsuario(usuario);
			u.setEmail(email);
			u.setRol(r);
			u.setState((short) 0);
			u.setPass(passwordEncoder.encode(password));
			u.setRol(r);
			usuarioService.save(u);
			u=usuarioService.findByUsuario(usuario);
			
			String mensaje = "hola " + usuario 					
					+ "\ningrese usando del siguente enlace para activar su cuenta: http://localhost:8085//activacioCuenta/"+u.getId();

			try {
				model.addAttribute("info","\nSe ha enviado un correo a: "+email+"para la activación de su cuenta");
				sendMail.sendMail("webyeison482@gmail.com", email, "Activación de cuenta", mensaje);
			} catch (Exception e) {
				System.out.println("no enviado");
				System.out.println(e);
			}
			String cad="se ha enviado un correo a: "+ email+ " para la activación de su cuenta";
			model.addAttribute("info",cad);
			return "login";
		}

		model.addAttribute("info", "el usuario ya existe");
		model.addAttribute("roles", rolService.findAll());
		return "nuevoUsuario";
	}
	
	
	@RequestMapping(value ="/activacioCuenta/{idusuario}" )
	public String activarCuenta(Model model,@PathVariable(value = "idusuario") Integer id) {
		Usuario u = usuarioService.findOne(id);
		if(u!=null) {
			u.setState((short) 1);
			usuarioService.save(u);
			model.addAttribute("info","usuario activado por favor inicie sesión");
			return "login";
		}
		return "login";
	}

}
