package com.nttdata.web.restController;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.web.repository.entity.Asignaturas;
import com.nttdata.web.service.AsignaturaService;

@RestController
@RequestMapping("/api/asignaturas")
public class AsignaturaRestControler {
	
	@Autowired
	AsignaturaService asignaturaService;
	
	@GetMapping
	@Cacheable(value="asiganturas")
	public ResponseEntity<List<Asignaturas>> consultarTodos(){
		try {
			HttpHeaders headers = new HttpHeaders();
			List<Asignaturas> list = asignaturaService.listar();
			if (list.isEmpty()) {
				headers.set("Message","La Lista está vacia");
				return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
			}
			else {
				headers.set("Message", "Mostrando todas las asignaturas");
				return new ResponseEntity<List<Asignaturas>>(list,headers,HttpStatus.OK);
			}
			
		} catch (Exception ex) {
			return new ResponseEntity<> (null,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Asignaturas> consultarPorId(@PathVariable("id") Integer id){
		try {
			HttpHeaders headers = new HttpHeaders();
			Asignaturas asi = asignaturaService.getById(id);
			if (asi == null) {
				headers.set("Message","No se ha encontrado la asignatura");
				return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
			}
			else {
				headers.set("Message", "Mostrando las asignatura seleccionada");
				return new ResponseEntity<Asignaturas>(asi,headers,HttpStatus.OK);
			}
			
		} catch (Exception ex) {
			return new ResponseEntity<> (null,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}
	
	@DeleteMapping
	@CacheEvict(value="asiganturas",allEntries = true)
	public ResponseEntity<Asignaturas> eliminarTodo(){
		try {
			HttpHeaders headers = new HttpHeaders();
				asignaturaService.borrarTodo();
				headers.set("Message","Se ha eliminado toda las asignaturas");
				return new ResponseEntity<>(headers, HttpStatus.OK);
			}
			
		 catch (Exception ex) {
			return new ResponseEntity<> (null,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}
	
	
	@DeleteMapping(value="/{id}")
	@CacheEvict(value="asiganturas",allEntries = true)
	public ResponseEntity<List<Asignaturas>> eliminarPorId(@PathVariable("id") Integer id){
		try {
				HttpHeaders headers = new HttpHeaders();
				if (asignaturaService.getById(id)==null) {
					headers.set("Message","La asignatura no se puede borrar puesto que no existe");
					return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
				}
				else {
					asignaturaService.borrarPorId(id);
					headers.set("Message","Se ha eliminado las asignatura");
					return new ResponseEntity<List<Asignaturas>>(asignaturaService.listar(),headers, HttpStatus.OK);
				}
				
			}
			
		 catch (Exception ex) {
			return new ResponseEntity<> (null,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}
	
	
	@PutMapping
	@CacheEvict(value="asiganturas",allEntries = true)
	public ResponseEntity<Asignaturas> modificar(@RequestBody Asignaturas asi){
		try {
				HttpHeaders headers = new HttpHeaders();
				if (asi.getId()==null) {
					headers.set("Message","La id no puede estar vacia");
					return new ResponseEntity<>(headers, HttpStatus.NOT_ACCEPTABLE);
				}
				
				else if (asi.getNombre() ==null || asi.getNombre().equals("") || asi.getNombre().length()>20
						|| asi.getDescripcion() == null || asi.getDescripcion().equals("") || asi.getDescripcion().length()>50) {
					
					headers.set("Message","El campo nombre y descripcion no puede estar vacio ni superar la longitud de 20 ni 50 respectivamente ");
					return new ResponseEntity<>(headers, HttpStatus.NOT_ACCEPTABLE);
				}
				else {
					headers.set("Message","Se ha eliminado las asignatura");
					return new ResponseEntity<Asignaturas>(asignaturaService.modificar(asi),headers, HttpStatus.OK);
				}
				
			}
			
		 catch (Exception ex) {
			return new ResponseEntity<> (null,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}
	
	@CacheEvict(value="asiganturas", allEntries=true)
	@PostMapping
	public ResponseEntity<Asignaturas> insertarEmpleado_v3 (@RequestBody Asignaturas asi) {
		try {
			HttpHeaders headers = new HttpHeaders();
			if (asi.getId()!=null) {
				headers.set("Message", "Para dar de alta una nueva asignatura, el ID debe llegar vacío");
				return new ResponseEntity<>(headers, HttpStatus.NOT_ACCEPTABLE);
			}
			else if (asi.getNombre() ==null || asi.getNombre().equals("") || asi.getNombre().length()>20
					|| asi.getDescripcion() == null || asi.getDescripcion().equals("") || asi.getDescripcion().length()>50)
				 {
					 headers.set("Message","El campo nombre y descripcion no puede estar vacio ni superar la longitud de 20 ni 50 respectivamente ");
						return new ResponseEntity<>(headers, HttpStatus.NOT_ACCEPTABLE);	
			}
			
			
			Asignaturas asi2 = asignaturaService.inserta(asi);
			URI newPath = new URI("/api/asignaturas/"+asi2.getId());
			headers.setLocation(newPath);
			headers.set("Message", "Asigantura insertada correctamente con id: "+asi2.getId());
			
			return new ResponseEntity<Asignaturas> (asi2, headers, HttpStatus.CREATED);
		}
		catch (Exception ex) {
			return new ResponseEntity<> (null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	
	
	
	
	
	
	
	

}
