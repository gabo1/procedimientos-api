package com.seguritech.spring.datajpa.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "grupo")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Grupo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "fechaCreacion")
	private Date fechaCreacion;
	
	@Column(name = "fechaActualizacion")
	private Date fechaActualizacion;
	
	@Column(name = "creadoPor")
	private Long creadoPor;
	
	@Column(name = "actualizadoPor")
	private Long actualizadoPor;
	
	@Column(name = "nombre")
	private String nombre;
	
//	@ManyToOne
//    @JoinColumn(name="id_tipo")
	@Column(name = "id_tipo")
	private Long tipo;
	
	@OneToMany(mappedBy = "idGrupo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<GrupoCamara> camaras;

	public Grupo(Date fechaCreacion, Date fechaActualizacion, Long creadoPor, Long actualizadoPor, String nombre, Long tipo) {
		super();
		this.fechaCreacion = fechaCreacion;
		this.fechaActualizacion = fechaActualizacion;
		this.creadoPor = creadoPor;
		this.actualizadoPor = actualizadoPor;
		this.nombre = nombre;
		this.tipo = tipo;
//		this.camaras = camaras;
	}

}
