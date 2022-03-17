package com.nttdata.web.service;

import java.util.List;

import com.nttdata.web.repository.entity.Asignaturas;

public interface AsignaturaService {

	public List<Asignaturas> listar() throws Exception;

	public Asignaturas getById(Integer id)  throws Exception;

	public void borrarTodo() throws Exception;

	public void borrarPorId(Integer id) throws Exception;

	public Asignaturas modificar(Asignaturas asi) throws Exception;

	public Asignaturas inserta(Asignaturas asi) throws Exception;
}
