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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tipo")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Tipo {
	
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
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_tipo")
	private List<Grupo> grupos;

}
