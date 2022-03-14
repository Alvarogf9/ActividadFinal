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

}
