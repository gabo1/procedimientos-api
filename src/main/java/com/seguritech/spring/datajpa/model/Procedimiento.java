package com.seguritech.spring.datajpa.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.seguritech.spring.datajpa.dto.EstatusEnum;
import com.seguritech.spring.datajpa.dto.ResultadoEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "procedimiento")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Procedimiento {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "titulo")
	private String titulo;

	@Column(name = "comentario")
	private String comentario;

	@Column(name = "estatus")
	@Enumerated(EnumType.ORDINAL)
	private EstatusEnum estatus;
	
	@Column(name = "resultado")
	@Enumerated(EnumType.ORDINAL)
	private ResultadoEnum resultado;
	
	@Column(name = "fechaCreacion")
	private Date fechaCreacion;
	
	@Column(name = "fechaActualizacion")
	private Date fechaActualizacion;
	
	@Column(name = "creadoPor")
	private Long creadoPor;
	
	@Column(name = "actualizadoPor")
	private Long actualizadoPor;
	
	@Column(name = "asignadoA")
	private Long asignadoA;

	public Procedimiento(String titulo, EstatusEnum estatus, ResultadoEnum resultado,  Date fechaCreacion,
			 Long creadoPor, Long asignadoA) {
		super();
		this.titulo = titulo;
		this.estatus = estatus;
		this.resultado = resultado;
		this.fechaCreacion = fechaCreacion;
		this.creadoPor = creadoPor;
		this.asignadoA = asignadoA;
	}
	
	
	

}
