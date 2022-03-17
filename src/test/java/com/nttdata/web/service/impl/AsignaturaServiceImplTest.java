package com.nttdata.web.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.nttdata.web.repository.AsignaturaRepoJPA;
import com.nttdata.web.repository.entity.Asignaturas;
import com.nttdata.web.service.AsignaturaService;

@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
class AsignaturaServiceImplTest {
	
	private Asignaturas as1 ,as2;
	
	
	@Autowired
	AsignaturaRepoJPA repo;
	
	@Autowired
	AsignaturaService service;
	

	@BeforeEach
	void setUp() throws Exception {
		
		repo.deleteAll();
		
		as1 = new Asignaturas();
		as1.setNombre("Frances");
		as1.setDescripcion("avanzado");
		as1.setCurso(3);
		as1= repo.save(as1);
		
		as2 = new Asignaturas();
		as2.setNombre("Informatica");
		as2.setDescripcion("Spring boot");
		as2.setCurso(4);
		as2= repo.save(as2);
	}

	@AfterEach
	void tearDown() throws Exception {
		repo.deleteAll();
		
	}

	@Test
	void testListar() throws Exception {
		
	
		
		//GIVEN:
		//Existen dos asignaturas
		assertEquals(2, service.listar().size(), "2 Asignaturas en BBDD");
	
	//WHEN:
		List<Asignaturas> le = service.listar();
	
	//THEN:
		assertEquals(2, le.size(), "Hay 2 Asignaturas en BBDD");
		List<Asignaturas> l1 = service.listar();
		int contador = 0;
		for (Asignaturas asignaturas : l1) {
			if (as1.equals(asignaturas) || as2.equals(asignaturas))
				contador++;
		}
		
		assertEquals(2, contador,"Estan todos los empleados");
		
	}

	@Test
	void testGetById() throws Exception {
		
		//GIVEN:
				//Existen dos asignaturas
				assertEquals(2, service.listar().size(), "2 Asignaturas en BBDD");
			
			//WHEN:
				Asignaturas as3 = service.getById(as1.getId());
			
			//THEN:
				assertEquals(as1, as3, "Misma asignatura");;
		
		
	}

	@Test
	void testBorrarTodo() throws Exception {
		
		//GIVEN:
		//Existen dos asignaturas
		assertEquals(2, service.listar().size(), "2 Asignaturas en BBDD");
	
	//WHEN:
		service.borrarTodo();
	
	//THEN:
		assertEquals(0, service.listar().size(), "No hay elementos en la BBDD");
		
	}

	@Test
	void testBorrarPorId() throws Exception {
		//GIVEN:
				//Existen dos asignaturas
				assertEquals(2, service.listar().size(), "2 Asignaturas en BBDD");
			
			//WHEN:
				service.borrarPorId(as1.getId());
			
			//THEN:
				assertEquals(1, service.listar().size(), "Se ha eliminado un elemento");
				List<Asignaturas> l1 = service.listar();
				int contador = 0;
				for (Asignaturas asignaturas : l1) {
					if (as1.equals(asignaturas))
						contador++;
				}
				
				assertEquals(0, contador,"No se ha eliminado correctamente");
	}

	@Test
	void testModificar() throws Exception {
		
		//GIVEN:
				//Existen dos asignaturas
				assertEquals(2, service.listar().size(), "2 Asignaturas en BBDD");
			
			//WHEN:

				String nuevoNombre = "Fisica";
				as2.setNombre(nuevoNombre);
				service.modificar(as2);
				
			
			//THEN:
				assertEquals(2, service.listar().size(), "Sigue habiendo dos asignaturas en BBDD");
				assertEquals (nuevoNombre, service.getById(as2.getId()).getNombre(), "Modificado el nombre" );
		
	}

	@Test
	void testInserta() throws Exception {
		//GIVEN:
		//Existen dos asignaturas
		assertEquals(2, service.listar().size(), "2 Asignaturas en BBDD");
	
	//WHEN:
		
		Asignaturas as3 = new Asignaturas();
		as3.setNombre("Quimica");
		as3.setDescripcion("avanzado");
		as3.setCurso(1);
		as3= repo.save(as3);
	
	//THEN:
		assertEquals(3, service.listar().size(), "Se ha insertado la asignatura en BBDD");

	}

}
