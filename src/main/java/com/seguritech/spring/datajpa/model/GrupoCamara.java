package com.seguritech.spring.datajpa.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "grupo_camara")
@NoArgsConstructor
@AllArgsConstructor
@Data
@IdClass(GrupoCamaraId.class)
public class GrupoCamara {
	
//	@EmbeddedId
//	private GrupoCamaraId id;
	@Id
	@Column(name = "id_grupo")
	private Long idGrupo;
		
	@Id
	@Column(name = "id_camara")
	private String idCamara;
	
	@Column(name = "fechaCreacion")
	private Date fechaCreacion;
	
	@Column(name = "fechaActualizacion")
	private Date fechaActualizacion;
	
	@Column(name = "creadoPor")
	private Long creadoPor;
	
	@Column(name = "actualizadoPor")
	private Long actualizadoPor;
	
	@Column(name = "nombre_camara")
	private String nombreCamara;

}
