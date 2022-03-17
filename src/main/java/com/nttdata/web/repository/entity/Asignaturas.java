package com.nttdata.web.repository.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
	
	@Entity
	@Table
	public class Asignaturas {
		

		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column
		private Integer id;
		
		@Column(length=20)
		private String nombre;
		
		@Column(length=50)
		private String descripcion;
		

		@Column
		private Integer curso;


		public Integer getId() {
			return id;
		}


		public void setId(Integer id) {
			this.id = id;
		}


		public String getNombre() {
			return nombre;
		}


		public void setNombre(String nombre) {
			this.nombre = nombre;
		}


		public String getDescripcion() {
			return descripcion;
		}


		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}


		public Integer getCurso() {
			return curso;
		}


		public void setCurso(Integer curso) {
			this.curso = curso;
		}


		@Override
		public int hashCode() {
			return Objects.hash(curso, descripcion, id, nombre);
		}


		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Asignaturas other = (Asignaturas) obj;
			return Objects.equals(curso, other.curso) && Objects.equals(descripcion, other.descripcion)
					&& Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre);
		}
		
		
		

}
