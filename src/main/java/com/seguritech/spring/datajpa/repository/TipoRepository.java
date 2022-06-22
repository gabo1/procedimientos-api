package com.seguritech.spring.datajpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.seguritech.spring.datajpa.model.Tipo;

public interface TipoRepository extends JpaRepository<Tipo, Long> {
	
	List<Tipo> findAllById(Long id);
	
	@Query(value = "FROM Tipo t JOIN FETCH t.grupos g WHERE t.id=:id AND g.creadoPor=:idUser")
	List<Tipo> findAllByIdAndIdUser(@Param("id")Long id, @Param("idUser")Long idUser);
	
	@Query(value = "FROM Tipo t JOIN FETCH t.grupos g WHERE t.id=1 OR g.creadoPor=:idUser")
	List<Tipo> findAllByIdUser(@Param("idUser")Long idUser);

}
