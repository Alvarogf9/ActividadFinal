package com.nttdata.web.repository.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AsignaturasTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		Asignaturas as = new Asignaturas();
		
		//Comprobar id
		as.setId(1);
		assertEquals(1, as.getId(), "Mismo id");
		
		//Comprobar nombre
		String nombre = "Prueba";
		as.setNombre(nombre);
		assertEquals(nombre, as.getNombre(), "Mismo nombre");
		
		//Comprobar nombre
		String descripcion = "Realizar pruebas Junit";
		as.setDescripcion(descripcion);
		assertEquals(descripcion, as.getDescripcion(), "Misma descripción");
		
		//Comprobar id
		as.setCurso(2);
		assertEquals(2, as.getCurso(), "Mismo curso");
		
		//Comprobar si 2 asignaturas son iguales
		Asignaturas as2 = new Asignaturas();
		as2.setId(1);
		as2.setNombre(nombre);
		as2.setDescripcion(descripcion);
		as2.setCurso(2);
		
		assertEquals(as.hashCode(), as2.hashCode(), "Mismo hash code");
		assertEquals(as, as2, "Mismo objeto");
		assertNotEquals(as, nombre, "Distinto objeto");
		
		
		
	}

}
