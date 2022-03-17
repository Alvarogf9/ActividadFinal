package com.nttdata.web.restController;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import com.nttdata.web.repository.AsignaturaRepoJPA;
import com.nttdata.web.repository.entity.Asignaturas;
import com.nttdata.web.service.AsignaturaService;

@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
class AsignaturaRestControlerTest {

	private Asignaturas as1 ,as2;
	
	@Autowired
	AsignaturaRepoJPA repo;
	
	@Autowired
	AsignaturaService service;
	
	@Autowired
	AsignaturaRestControler restController;
	
	@Mock // --> simular
	AsignaturaService serviceMock;
	
	@InjectMocks
	AsignaturaRestControler restcontrollerMock;
	
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
	void testConsultarTodos_listaVacia() throws Exception {
		//GIVEN:
		//Partimos de una base de datos vacia
		assertEquals(2, service.listar().size(), "Hay dos Asignaturas en BBDD");
		service.borrarTodo();
		assertEquals(0, service.listar().size(), "Hay 0 Asignaturas en BBDD");
				
		//WHEN:
		ResponseEntity<List<Asignaturas>> re = restController.consultarTodos();


		//THEN
		assertEquals (HttpStatus.NOT_FOUND, re.getStatusCode(), "Error not found, no hay asignaturas");
		
	
	}
	
	@Test
	void testConsultarTodos() throws Exception {
		//GIVEN:
		assertEquals(2, service.listar().size(), "Hay dos Asignaturas en BBDD");
		//WHEN:
		ResponseEntity<List<Asignaturas>> re = restController.consultarTodos();
		//THEN
		
		assertAll(
				() -> assertEquals (HttpStatus.OK , re.getStatusCode(), "Código ok"),
				() -> assertEquals (2, re.getBody().size(), "Tamaño de la lista 2")
				);
		;
		
	
	}

	
	@Test
	void testConsultarTodos_Exception() throws Exception{
		//GIVEN:
		when ( serviceMock.listar() ).thenThrow (new Exception());
		//WHEN:
		ResponseEntity<List<Asignaturas>> re = restcontrollerMock.consultarTodos();
		//THEN
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR , re.getStatusCode(), "Excepcion");
		
	
	}
	
	@Test
	void testConsultarPorId_Null() throws Exception {
		
		//GIVEN:
				assertEquals(2, service.listar().size(), "Hay dos Asignaturas en BBDD");
				//WHEN:
				ResponseEntity<Asignaturas> re = restController.consultarPorId(1);
				//THEN
				 assertEquals (HttpStatus.NOT_FOUND , re.getStatusCode(), "Código Not found, id null");
		
	}
	
	
	@Test
	void testConsultarPorId() throws Exception {
		
		//GIVEN:
				assertEquals(2, service.listar().size(), "Hay dos Asignaturas en BBDD");
				//WHEN:
				ResponseEntity<Asignaturas> re = restController.consultarPorId(as1.getId());
				//THEN
				 assertEquals (HttpStatus.OK , re.getStatusCode(), "Código OK, asignatura encontrada");
				 assertEquals (as1 , re.getBody(), "Comparando asignaturas");
	}

	
	@Test
	void testConsultarPorId_Exception() throws Exception {
		
		//GIVEN:
				when ( serviceMock.getById(as1.getId()) ).thenThrow (new Exception());
				//WHEN:
				ResponseEntity<Asignaturas> re = restcontrollerMock.consultarPorId(as1.getId());
				//THEN
				assertEquals(HttpStatus.INTERNAL_SERVER_ERROR , re.getStatusCode(), "Excepcion");
	}
	
	

	
	@Test
	void testEliminarTodo() throws Exception {
		
		//GIVEN:
		assertEquals(2, service.listar().size(), "Hay dos Asignaturas en BBDD");
		//WHEN:
		ResponseEntity<Asignaturas> re = restController.eliminarTodo();
		//THEN
		 assertEquals (HttpStatus.OK , re.getStatusCode(), "Código OK,eliminada BBDD");
		 assertEquals (0 , service.listar().size(), "Comparando asignaturas");
	}
	
		@Test
		void testEliminarTodo_Exception() throws Exception {
			
			
			//GIVEN:
			
			doThrow(new Exception()).when(serviceMock).borrarTodo();
			//WHEN:
			ResponseEntity<Asignaturas> re = restcontrollerMock.eliminarTodo();
			//THEN
			assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, re.getStatusCode(), "Excepcion");

		}

    @Test
	void testEliminarPorId_Null() throws Exception {
		
		//GIVEN:
		assertEquals(2, service.listar().size(), "Hay dos Asignaturas en BBDD");
		//WHEN:
		ResponseEntity<List<Asignaturas>> re = restController.eliminarPorId(null);
		//THEN
		 assertEquals (HttpStatus.NOT_FOUND , re.getStatusCode(), "Código not found,id null");
	}
    
  
	
	@Test
	void testEliminarPorId() throws Exception {
		
		//GIVEN:

		assertEquals(2, service.listar().size(), "Hay dos Asignaturas en BBDD");
		//WHEN:
		ResponseEntity<List<Asignaturas>> re = restController.eliminarPorId(as1.getId());
		//THEN
		 assertEquals (HttpStatus.OK , re.getStatusCode(), "Código ok, asignatura aliminada");
		 assertEquals (1, re.getBody().size(), "eliminado correctamente");
		 
	}
	
	
		@Test
		void testEliminarPorId_Exception() throws Exception {
			//GIVEN:
		System.out.println("PARTE DE EXCEPCION");
		
		doThrow(new Exception()).when(serviceMock).borrarPorId(as1.getId());
			//WHEN:
			ResponseEntity<List<Asignaturas>> re = restcontrollerMock.eliminarPorId(as1.getId());
			
			//THEN
			assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, re.getStatusCode(), "Excepcion");
				
		
	
		}
	

	@Test
	void testModificar_Null() throws Exception {
		
		//GIVEN:
		assertEquals(2, service.listar().size(), "Hay dos Asignaturas en BBDD");
		//WHEN:
		as1.setNombre("Prueba");
		as1.setId(null);
		ResponseEntity<Asignaturas> re = restController.modificar(as1);
		//THEN
		 assertEquals (HttpStatus.NOT_ACCEPTABLE , re.getStatusCode(), "Código not found, asignatura no encontrada");
	
		
	}
	
	@Test
	void testModificar() throws Exception {
		
		//GIVEN:
		assertEquals(2, service.listar().size(), "Hay dos Asignaturas en BBDD");
		//WHEN:
		as1.setNombre("Prueba");
		ResponseEntity<Asignaturas> re = restController.modificar(as1);
		//THEN
		 assertEquals (HttpStatus.OK, re.getStatusCode(), "Código OK, asignatura modificada");
		 assertEquals ("Prueba", re.getBody().getNombre(), "nombre modificado");
		 
	
		
	}

	
	@Test
	void testModificar_Exception() throws Exception {
		//GIVEN:
		when ( serviceMock.modificar(as1) ).thenThrow (new Exception());
		//WHEN:
		ResponseEntity<Asignaturas> re = restcontrollerMock.modificar(as1);
		//THEN
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, re.getStatusCode(), "Excepcion");
		
	}
	
	@Test
	void testInsertar_IdNotNull() throws Exception {
		
		//GIVEN:
				assertEquals(2, service.listar().size(), "Hay dos Asignaturas en BBDD");
				//WHEN:
				Asignaturas as3 = new Asignaturas();
				as3.setNombre("Prueba");
				as3.setId(2);
				as3.setDescripcion("Prueba id no null");
				as3.setCurso(2);
				ResponseEntity<Asignaturas> re = restController.insertar(as3);
				//THEN
				 assertEquals (HttpStatus.NOT_ACCEPTABLE , re.getStatusCode(), "Código no aceptado, asignatura con id no null");
			
		
	}
	
	
	@Test
	void testInsertar_nombreNull() throws Exception {
		
		//GIVEN:
				assertEquals(2, service.listar().size(), "Hay dos Asignaturas en BBDD");
				//WHEN:
				Asignaturas as3 = new Asignaturas();
				as3.setNombre(null);
				as3.setDescripcion("Prueba id no null");
				as3.setCurso(2);
				ResponseEntity<Asignaturas> re = restController.insertar(as3);
				//THEN
				 assertEquals (HttpStatus.NOT_ACCEPTABLE , re.getStatusCode(), "Código no aceptado, nombre null");
			
		
	}
	
	@Test
	void testInsertar_nombreVacio() throws Exception {
		
		//GIVEN:
				assertEquals(2, service.listar().size(), "Hay dos Asignaturas en BBDD");
				//WHEN:
				Asignaturas as3 = new Asignaturas();
				as3.setNombre("");
				as3.setDescripcion("Prueba id no null");
				as3.setCurso(2);
				ResponseEntity<Asignaturas> re = restController.insertar(as3);
				//THEN
				 assertEquals (HttpStatus.NOT_ACCEPTABLE , re.getStatusCode(), "Código no aceptado, nombre vacio");
			
		
	}
	
	@Test
	void testInsertar_nombreLargo() throws Exception {
		
		//GIVEN:
				assertEquals(2, service.listar().size(), "Hay dos Asignaturas en BBDD");
				//WHEN:
				Asignaturas as3 = new Asignaturas();
				as3.setNombre("Nombredemasiadolargomasde20caracteres");
				as3.setDescripcion("Prueba id no null");
				as3.setCurso(2);
				ResponseEntity<Asignaturas> re = restController.insertar(as3);
				//THEN
				 assertEquals (HttpStatus.NOT_ACCEPTABLE , re.getStatusCode(), "Código no aceptado, nombre demasiado largo");
			
		
	}
	
	@Test
	void testInsertar_descripcionNull() throws Exception {
		
		//GIVEN:
				assertEquals(2, service.listar().size(), "Hay dos Asignaturas en BBDD");
				//WHEN:
				Asignaturas as3 = new Asignaturas();
				as3.setNombre("Prueba");
				as3.setDescripcion(null);
				as3.setCurso(2);
				ResponseEntity<Asignaturas> re = restController.insertar(as3);
				//THEN
				 assertEquals (HttpStatus.NOT_ACCEPTABLE , re.getStatusCode(), "Código no aceptado, descripcion null");
			
		
	}
	
	@Test
	void testInsertar_descripcionVacio() throws Exception {
		
		//GIVEN:
				assertEquals(2, service.listar().size(), "Hay dos Asignaturas en BBDD");
				//WHEN:
				Asignaturas as3 = new Asignaturas();
				as3.setNombre("Prueba");
				as3.setDescripcion("");
				as3.setCurso(2);
				ResponseEntity<Asignaturas> re = restController.insertar(as3);
				//THEN
				 assertEquals (HttpStatus.NOT_ACCEPTABLE , re.getStatusCode(), "Código no aceptado, descripcion vacia");
			
		
	}
	
	@Test
	void testInsertar_descripcionLarga() throws Exception {
		
		//GIVEN:
				assertEquals(2, service.listar().size(), "Hay dos Asignaturas en BBDD");
				//WHEN:
				Asignaturas as3 = new Asignaturas();
				as3.setNombre("Prueba");
				as3.setDescripcion("Descripciondemasiadolargamasde50caracteresssssssssssssssssssssss");
				as3.setCurso(2);
				ResponseEntity<Asignaturas> re = restController.insertar(as3);
				//THEN
				 assertEquals (HttpStatus.NOT_ACCEPTABLE , re.getStatusCode(), "Código no aceptado, descripcion demasiado larga");
			
		
	}
	
	@Test
	void testInsertar() throws Exception {
		
		//GIVEN:
				assertEquals(2, service.listar().size(), "Hay dos Asignaturas en BBDD");
				//WHEN:
				Asignaturas as3 = new Asignaturas();
				as3.setNombre("Prueba");
				as3.setDescripcion("Descripcion");
				as3.setCurso(2);
				ResponseEntity<Asignaturas> re = restController.insertar(as3);
				//THEN
				 assertEquals (HttpStatus.CREATED , re.getStatusCode(), "Código  aceptado, Insertado");
			
		
	}

	
		@Test
		void testInsertar_Exception() throws Exception {
			//GIVEN:
			Asignaturas as3 = new Asignaturas();
			as3.setNombre("Prueba");
			as3.setDescripcion("Descripcion");
			as3.setCurso(2);
			when ( serviceMock.inserta(as3) ).thenThrow (new Exception());
			//WHEN:
			ResponseEntity<Asignaturas> re = restcontrollerMock.insertar(as3);
			//THEN
			assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, re.getStatusCode(), "Excepcion");
			
		}
	
	
	
	
	
	

}
