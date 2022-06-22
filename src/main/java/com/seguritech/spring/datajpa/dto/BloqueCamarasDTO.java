package com.seguritech.spring.datajpa.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BloqueCamarasDTO {
	
	private Long id;
	private Date fechaCreacion;
	private Date fechaActualizacion;
	private Long creadoPor;
	private Long actualizadoPor;
	private String idCamara;
	private int tiempoBloqueo;
	private Date fechaFinBloqueo;
	private Boolean activo;

}
