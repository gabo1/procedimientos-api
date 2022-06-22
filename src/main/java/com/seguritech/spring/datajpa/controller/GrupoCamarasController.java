package com.seguritech.spring.datajpa.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seguritech.spring.datajpa.dto.GrupoCamaraRequestDTO;
import com.seguritech.spring.datajpa.dto.TipoDTO;
import com.seguritech.spring.datajpa.model.Grupo;
import com.seguritech.spring.datajpa.model.GrupoCamara;
import com.seguritech.spring.datajpa.model.Tipo;
import com.seguritech.spring.datajpa.repository.GrupoCamaraRepository;
import com.seguritech.spring.datajpa.repository.GrupoRepository;
import com.seguritech.spring.datajpa.repository.TipoRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class GrupoCamarasController {
	
	@Autowired
	private TipoRepository tipoRepository;
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private GrupoCamaraRepository grupoCamaraRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@GetMapping("/camaras/por-tipo")	
	public ResponseEntity<List<TipoDTO>> buscarPorTipo(
			@RequestParam(required = false) Long idTipo, 
			@RequestParam(required = true) Long idUsuario) {
		List<TipoDTO> resp;
		if (idTipo != null) {
			resp = tipoRepository.findAllByIdAndIdUser(idTipo, idUsuario).stream().map(this::toDtoAll).collect(Collectors.toList());
		} else {
			resp = tipoRepository.findAllByIdUser(idUsuario).stream().map(this::toDtoAll).collect(Collectors.toList());
		}		
		if (resp.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}
	
	@PostMapping("/camaras")
	@Transactional
	public ResponseEntity<?> crear(
			@RequestParam(required = false) Long idTipo,
			@RequestParam(required = true) String nombreGrupo,
			@RequestParam(required = true) Long idUsuario,
			@RequestBody GrupoCamaraRequestDTO camaras) {
		
		idTipo=idTipo!=null?idTipo:2;
		Optional<Tipo> tipo = tipoRepository.findById(idTipo);		
		if (!tipo.isPresent()) {
			return new ResponseEntity<>(null, HttpStatus.CONFLICT);
		}
		Optional<Grupo> grupo = grupoRepository.findByNombre(nombreGrupo);
		if (grupo.isPresent()) {
			return new ResponseEntity<>(null, HttpStatus.CONFLICT);
		}
		Grupo nuevoGrupo = grupoRepository.save(new Grupo(new Date(), null, idUsuario, null, nombreGrupo, tipo.get().getId()));
		List<GrupoCamara> listaCamaras = new ArrayList<>();
		camaras.getCamaras().forEach(x -> {
			listaCamaras.add(new GrupoCamara(nuevoGrupo.getId(), x.getIdCamara(), new Date(), null, idUsuario, null, x.getNombreCamara()));
		});
		grupoCamaraRepository.saveAll(listaCamaras);
		
		return new ResponseEntity<>(null,HttpStatus.CREATED);
	}
	
	@PutMapping("/camaras")
	@Transactional
	public ResponseEntity<?> actualizar(
			@RequestParam(required = false) Long idTipo,
			@RequestParam(required = true) Long idGrupo,
			@RequestParam(required = true) Long idUsuario,
			@RequestBody GrupoCamaraRequestDTO camaras) {
		idTipo=idTipo!=null?idTipo:2;
		Optional<Tipo> tipo = tipoRepository.findById(idTipo);		
		if (!tipo.isPresent()) {
			return new ResponseEntity<>(null, HttpStatus.CONFLICT);
		}
		Optional<Grupo> grupo = grupoRepository.findById(idGrupo);
		if (!grupo.isPresent()) {
			return new ResponseEntity<>(null, HttpStatus.CONFLICT);
		}
		grupoCamaraRepository.deleteByIdGrupo(idGrupo);
		List<GrupoCamara> listaCamaras = new ArrayList<>();
		camaras.getCamaras().forEach(x -> {
			listaCamaras.add(new GrupoCamara(grupo.get().getId(), x.getIdCamara(), new Date(), null, idUsuario, null, x.getNombreCamara()));
		});
		grupoCamaraRepository.saveAll(listaCamaras);
		
		return new ResponseEntity<>(null,HttpStatus.CREATED);
		
	}
	
	private TipoDTO toDtoAll(Tipo entity) {
		return new ModelMapper().map(entity, TipoDTO.class);
	}

}
