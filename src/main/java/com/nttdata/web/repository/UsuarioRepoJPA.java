package com.nttdata.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nttdata.web.repository.entity.Usuario;

public interface UsuarioRepoJPA extends JpaRepository<Usuario, String> {
	
	
	@Query(value="select * from usuario where rol_ID=?1",nativeQuery = true)
	public List<Usuario> listarCuyoRolEs(Integer rol);


}
