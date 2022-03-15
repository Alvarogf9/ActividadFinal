package com.nttdata.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.web.repository.AsignaturaRepoJPA;
import com.nttdata.web.repository.entity.Asignaturas;
import com.nttdata.web.service.AsignaturaService;

@Service
public class AsignaturaServiceImpl implements AsignaturaService{
	
	@Autowired
	AsignaturaRepoJPA repoJPA;

	@Override
	public List<Asignaturas> listar() {
		return repoJPA.findAll();
	}

	@Override
	public Asignaturas getById(Integer id) {
		return repoJPA.findById(id).orElse(null);
	}

	@Override
	public void borrarTodo() {
		repoJPA.deleteAll();
		
	}

	@Override
	public void borrarPorId(Integer id) {
		repoJPA.deleteById(id);
		
	}

	@Override
	public Asignaturas modificar(Asignaturas asi) {
		return repoJPA.save(asi);
	}

	@Override
	public Asignaturas inserta(Asignaturas asi) {
		return repoJPA.save(asi);
	}

}
