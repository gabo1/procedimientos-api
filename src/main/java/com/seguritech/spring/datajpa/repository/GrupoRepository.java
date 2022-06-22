package com.seguritech.spring.datajpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seguritech.spring.datajpa.model.Grupo;

public interface GrupoRepository extends JpaRepository<Grupo, Long> {
	
	Optional<Grupo> findByNombre(String nombre);

}
