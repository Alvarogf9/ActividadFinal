package com.nttdata.web.service;

import java.util.List;

import com.nttdata.web.repository.entity.Usuario;

public interface UsuarioService {

	List<Usuario> listar();
	Usuario buscarPorUsername(String username);
	List<Usuario> listarPorRol(Integer rol);
}
