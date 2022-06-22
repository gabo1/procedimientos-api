package com.seguritech.spring.datajpa.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bloqueo_camara")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BloqueoCamara {
	
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
	
	@Column(name = "idCamara")
	private String idCamara;
	
	@Column(name = "tiempoBloqueo")
	private int tiempoBloqueo;
	
	@Column(name = "fechaFinBloqueo")
	private Date fechaFinBloqueo;
	
	@Column(name = "activo")
	private Boolean activo;

	public BloqueoCamara(Date fechaCreacion, Date fechaActualizacion, Long creadoPor, Long actualizadoPor,
			String idCamara, int tiempoBloqueo, Date fechaFinBloqueo, Boolean activo) {
		super();
		this.fechaCreacion = fechaCreacion;
		this.fechaActualizacion = fechaActualizacion;
		this.creadoPor = creadoPor;
		this.actualizadoPor = actualizadoPor;
		this.idCamara = idCamara;
		this.tiempoBloqueo = tiempoBloqueo;
		this.fechaFinBloqueo = fechaFinBloqueo;
		this.activo = activo;
	}

}
