package com.rest.Entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Entity implementation class for Entity: Pendiente
 *
 */
@Entity
@Table(name="pendiente")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Pendiente implements Serializable {

	
 	private static final long serialVersionUID = 1L;
	
	@Id
 	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="nombre",nullable=false)
	private String nombre;
	
	@Column(name="descripcion")
	private String descripcion;


	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public Pendiente(String nombre, String descripcion) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public Pendiente() {
		super();
	}

	 
   
}
