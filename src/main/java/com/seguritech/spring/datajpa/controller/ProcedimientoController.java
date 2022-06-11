package com.seguritech.spring.datajpa.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seguritech.spring.datajpa.dto.EstatusEnum;
import com.seguritech.spring.datajpa.dto.ProcedimientoDTO;
import com.seguritech.spring.datajpa.dto.ResultadoEnum;
import com.seguritech.spring.datajpa.model.Procedimiento;
import com.seguritech.spring.datajpa.repository.ProcedimientoRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class ProcedimientoController {

	@Autowired
	ProcedimientoRepository procedimientoRepository;
	
	@Autowired
	ModelMapper modelMapper;

	@GetMapping("/procedimiento")
	public ResponseEntity<List<ProcedimientoDTO>> buscarProcedimiento(@RequestParam(required = false) String title) {
		try {
			List<Procedimiento> procedimientos = new ArrayList<Procedimiento>();

			if (title == null)
				procedimientoRepository.findAll().forEach(procedimientos::add);
			else
				procedimientoRepository.findByTituloContaining(title).forEach(procedimientos::add);

			if (procedimientos.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			List<ProcedimientoDTO> procedimientosDTO
		    = modelMapper.map(procedimientos, new TypeToken<List<ProcedimientoDTO>>() {}.getType());
			
			return new ResponseEntity<>(procedimientosDTO, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/procedimiento/{id}")
	public ResponseEntity<ProcedimientoDTO> buscarProcedimientoPorId(@PathVariable("id") long id) {
		Optional<Procedimiento> procedimiento = procedimientoRepository.findById(id);

		if (procedimiento.isPresent()) {
			ProcedimientoDTO procedimientoDTO = this.modelMapper.map(procedimiento.get(), ProcedimientoDTO.class);
			return new ResponseEntity<>(procedimientoDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/procedimiento/asignado/{id}")
	public ResponseEntity<List<ProcedimientoDTO>> buscarProcedimientoPorUsuarioAsignado(@PathVariable("id") long id) {
		List<Procedimiento> procedimientos  = procedimientoRepository.findByAsignadoAAndEstatus(id, EstatusEnum.ABIERTO);

		if (procedimientos.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		List<ProcedimientoDTO> procedimientosDTO
	    = modelMapper.map(procedimientos, new TypeToken<List<ProcedimientoDTO>>() {}.getType());
		
		return new ResponseEntity<>(procedimientosDTO, HttpStatus.OK);
	}

	@PostMapping("/procedimiento")
	public ResponseEntity<ProcedimientoDTO> crearProcedimiento(@RequestBody ProcedimientoDTO procedimientoDTO) {
		try {
			Procedimiento _procedimiento = procedimientoRepository
					.save(new Procedimiento(
							procedimientoDTO.getTitulo(), 
							EstatusEnum.ABIERTO, 
							null, 
							new Date(), 
							procedimientoDTO.getCreadoPor(), 
							procedimientoDTO.getAsignadoA()));
			
			procedimientoDTO = this.modelMapper.map(_procedimiento, ProcedimientoDTO.class);
			return new ResponseEntity<>(procedimientoDTO, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/procedimiento/{id}")
	public ResponseEntity<ProcedimientoDTO> actualizarProcedimiento(@PathVariable("id") long id, @RequestBody ProcedimientoDTO procedimientoDTO) {
		Optional<Procedimiento> procedimiento = procedimientoRepository.findById(id);

		if (procedimiento.isPresent()) {
			Procedimiento _procedimiento = procedimiento.get();
			_procedimiento.setActualizadoPor(procedimientoDTO.getActualizadoPor());
			_procedimiento.setComentario(procedimientoDTO.getComentario());
			_procedimiento.setResultado(procedimientoDTO.getResultado());
			_procedimiento.setEstatus(EstatusEnum.ABIERTO);
			_procedimiento.setFechaActualizacion(new Date());
			_procedimiento = procedimientoRepository.save(_procedimiento);
			
			procedimientoDTO = this.modelMapper.map(_procedimiento, ProcedimientoDTO.class);
			return new ResponseEntity<>(procedimientoDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/procedimientoFinalizar/{id}")
	public ResponseEntity<ProcedimientoDTO> actualizarProcedimientoFinalizar(@PathVariable("id") long id, @RequestBody ProcedimientoDTO procedimientoDTO) {
		Optional<Procedimiento> procedimiento = procedimientoRepository.findById(id);

		if (procedimiento.isPresent()) {
			Procedimiento _procedimiento = procedimiento.get();
			_procedimiento.setActualizadoPor(procedimientoDTO.getActualizadoPor());
			_procedimiento.setComentario(procedimientoDTO.getComentario());
			_procedimiento.setResultado(procedimientoDTO.getResultado());
			_procedimiento.setEstatus(EstatusEnum.CERRADO);
			_procedimiento.setFechaActualizacion(new Date());
			_procedimiento = procedimientoRepository.save(_procedimiento);
			
			procedimientoDTO = this.modelMapper.map(_procedimiento, ProcedimientoDTO.class);
			return new ResponseEntity<>(procedimientoDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}


}
