package com.nttdata.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nttdata.web.repository.entity.Usuario;
import com.nttdata.web.service.AsignaturaService;
import com.nttdata.web.service.UsuarioService;

@Controller
public class WebAsignaturaController {
	
	@Autowired
	AsignaturaService asignaturaService;

	@Autowired
	UsuarioService usuarioService;
	
	 @GetMapping("/")
		public String index (Model model) {
			Usuario u = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("usuario", u);
			return "index";
		}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/listarAsignaturas")
	public String listarEmp(Model model) throws Exception {
		model.addAttribute("listaAS", asignaturaService.listar());
		return "listarAsignaturas";
	}
	
	@GetMapping("/listarUsuarios")
	public String listarUsuarios(@RequestParam("rol") int rol, Model model) {
		
		model.addAttribute("listaUsuarios", usuarioService.listarPorRol(rol));
		return "listarUsuarios";
	}
	
	
	@GetMapping("/error")
	public String errorPage()
	{
		return "error";
	}
	
}
