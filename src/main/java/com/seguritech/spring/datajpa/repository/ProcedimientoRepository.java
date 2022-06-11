package com.seguritech.spring.datajpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seguritech.spring.datajpa.dto.EstatusEnum;
import com.seguritech.spring.datajpa.model.Procedimiento;

public interface ProcedimientoRepository extends JpaRepository<Procedimiento, Long> {
	List<Procedimiento> findByCreadoPor(Long creadoPor);
	List<Procedimiento> findByAsignadoAAndEstatus(Long asignadoA, EstatusEnum estatus);
	List<Procedimiento> findByTituloContaining(String title);
}
