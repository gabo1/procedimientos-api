package com.seguritech.spring.datajpa.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GrupoCamaraDTO {
	
//	private GrupoCamaraIdDTO id;
	private String idCamara;
	private Date fechaCreacion;
	private Date fechaActualizacion;
	private Long creadoPor;
	private Long actualizadoPor;
	private String nombreCamara;

}
