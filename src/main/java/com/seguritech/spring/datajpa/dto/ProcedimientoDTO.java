package com.seguritech.spring.datajpa.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.seguritech.spring.datajpa.dto.ResultadoEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProcedimientoDTO {

	private Long id;
	private String titulo;
	private String comentario;
	private EstatusEnum estatus;
	private ResultadoEnum resultado;
	private Date fechaCreacion;
	private Date fechaActualizacion;
	private Long creadoPor;
	private Long actualizadoPor;
	private Long asignadoA;


}
