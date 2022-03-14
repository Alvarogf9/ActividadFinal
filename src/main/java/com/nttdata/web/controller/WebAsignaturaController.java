package com.nttdata.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.nttdata.web.service.AsignaturaService;

@Controller
public class WebAsignaturaController {
	
	@Autowired
	AsignaturaService asignaturaService;

	
	@GetMapping("/listarAsignaturas")
	public String listarEmp(Model model) {
		model.addAttribute("listaAS", asignaturaService.listar());
		return "listarAsignaturas";
	}
	
}
