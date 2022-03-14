package com.nttdata.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nttdata.web.repository.UsuarioRepoJPA;
import com.nttdata.web.repository.entity.Usuario;
import com.nttdata.web.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService , UserDetailsService{

	@Autowired
	UsuarioRepoJPA usuarioDAO;
	
	@Override
	public List<Usuario> listar() {
		return usuarioDAO.findAll();
	}

	@Override
	public Usuario buscarPorUsername(String username) {
		//return usuarioDAO.getById(username);
		return usuarioDAO.findById(username).get(); //devuelve un optional Usuario y por eso hay que usar el .get u otro metodo que nos interese
	}

	@Override
	public List<Usuario> listarPorRol(Integer rol) {
		return usuarioDAO.listarCuyoRolEs(rol);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return buscarPorUsername(username);
	}

}
