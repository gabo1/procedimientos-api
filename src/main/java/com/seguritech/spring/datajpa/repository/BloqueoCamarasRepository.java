package com.seguritech.spring.datajpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seguritech.spring.datajpa.model.BloqueoCamara;

public interface BloqueoCamarasRepository extends JpaRepository<BloqueoCamara, Long> {
	
	Optional<BloqueoCamara> findByCreadoPorAndIdCamaraAndActivo(Long creadoPor, String idCamara, Boolean activo);
}
