package com.seguritech.spring.datajpa.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TipoDTO {

	private Long id;
	private Date fechaCreacion;
	private Date fechaActualizacion;
	private Long creadoPor;
	private Long actualizadoPor;
	private String nombre;
	private List<GrupoDTO> grupos;

}
