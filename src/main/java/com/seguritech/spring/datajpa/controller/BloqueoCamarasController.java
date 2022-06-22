package com.seguritech.spring.datajpa.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seguritech.spring.datajpa.dto.BloqueCamarasDTO;
import com.seguritech.spring.datajpa.model.BloqueoCamara;
import com.seguritech.spring.datajpa.repository.BloqueoCamarasRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class BloqueoCamarasController {
	
	@Autowired
	BloqueoCamarasRepository bloqueoCamarasRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@PostMapping("/bloqueo")
	public ResponseEntity<?> crearBloqueo(@RequestBody BloqueCamarasDTO bloqueCamarasDTO) {
		try {
			
			Optional<BloqueoCamara> registro = bloqueoCamarasRepository.findByCreadoPorAndIdCamaraAndActivo(
					bloqueCamarasDTO.getCreadoPor(), bloqueCamarasDTO.getIdCamara(), Boolean.TRUE);
			if (registro.isPresent()) {
				return new ResponseEntity<>(null, HttpStatus.CONFLICT);
			}
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			Date initDate = cal.getTime();
			cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + bloqueCamarasDTO.getTiempoBloqueo());
			Date endDate = cal.getTime();
			
			BloqueoCamara bloqueoCamara = this.bloqueoCamarasRepository.save(
					new BloqueoCamara(
							initDate, 
							null, 
							bloqueCamarasDTO.getCreadoPor(), 
							null, 
							bloqueCamarasDTO.getIdCamara(), 
							bloqueCamarasDTO.getTiempoBloqueo(), 
							endDate, 
							Boolean.TRUE));
			bloqueCamarasDTO = this.modelMapper.map(bloqueoCamara, BloqueCamarasDTO.class);
			return new ResponseEntity<>(bloqueCamarasDTO, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/bloqueo/{id-usuario}/{id-camara}")
	public ResponseEntity<BloqueCamarasDTO> buscarProcedimientoPorUsuarioYCamara(
			@PathVariable("id-usuario") long idUsuario, 
			@PathVariable("id-camara") String idCamara) {
		
		Optional<BloqueoCamara> registro = bloqueoCamarasRepository.findByCreadoPorAndIdCamaraAndActivo(idUsuario, idCamara, Boolean.TRUE);
		if (registro.isPresent()) {
			BloqueCamarasDTO respuesta = this.modelMapper.map(registro.get(), BloqueCamarasDTO.class);
			return new ResponseEntity<>(respuesta, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/bloqueo/{id-usuario}/{id-camara}")
	public ResponseEntity<HttpStatus> desactivarBloqueo(@PathVariable("id-usuario") long idUsuario, 
			@PathVariable("id-camara") String idCamara) {
		try {
			Optional<BloqueoCamara> registro = bloqueoCamarasRepository.findByCreadoPorAndIdCamaraAndActivo(idUsuario, idCamara, Boolean.TRUE);
			if (registro.isPresent()) {
				registro.get().setActivo(Boolean.FALSE);
				registro.get().setActualizadoPor(idUsuario);
				registro.get().setFechaActualizacion(new Date());
				bloqueoCamarasRepository.save(registro.get());
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}			
	    } catch (Exception e) {
	    	return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

}
