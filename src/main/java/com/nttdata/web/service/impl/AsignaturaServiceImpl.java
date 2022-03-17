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
	public List<Asignaturas> listar() throws Exception {
		return repoJPA.findAll();
	}

	@Override
	public Asignaturas getById(Integer id)  throws Exception {
		return repoJPA.findById(id).orElse(null);
	}

	@Override
	public void borrarTodo() throws Exception {
		repoJPA.deleteAll();
		
	}

	@Override
	public void borrarPorId(Integer id) throws Exception {
		repoJPA.deleteById(id);
		
	}

	@Override
	public Asignaturas modificar(Asignaturas asi) throws Exception {
		return repoJPA.save(asi);
	}

	@Override
	public Asignaturas inserta(Asignaturas asi) throws Exception {
		return repoJPA.save(asi);
	}

}
