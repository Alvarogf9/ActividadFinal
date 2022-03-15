package com.nttdata.web.service;

import java.util.List;

import com.nttdata.web.repository.entity.Asignaturas;

public interface AsignaturaService {

	public List<Asignaturas> listar();

	public Asignaturas getById(Integer id);

	public void borrarTodo();

	public void borrarPorId(Integer id);

	public Asignaturas modificar(Asignaturas asi);

	public Asignaturas inserta(Asignaturas asi);
}
