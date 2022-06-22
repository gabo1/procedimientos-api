package com.seguritech.spring.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seguritech.spring.datajpa.model.GrupoCamara;
import com.seguritech.spring.datajpa.model.GrupoCamaraId;

public interface GrupoCamaraRepository extends JpaRepository<GrupoCamara, GrupoCamaraId> {
	
	void deleteByIdGrupo(Long idGrupo);

}
