package com.seguritech.spring.datajpa.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
//@Embeddable
public class GrupoCamaraId implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2327486701860843277L;

//	@Column(name = "id_grupo")
	private Long idGrupo;
	
//	@Column(name = "id_camara")
	private String idCamara;

//	@Override
//	public int hashCode() {
////		return Objects.hash(idCamara, idGrupo);
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((idCamara == null) ? 0 : idCamara.hashCode());
//		result = prime * result + ((idGrupo == null) ? 0 : idGrupo.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		GrupoCamaraId other = (GrupoCamaraId) obj;
//		if (idGrupo == null) {
//			if (other.idGrupo != null)
//				return false;
//		} else if (!idGrupo.equals(other.idGrupo))
//			return false;
//		if (idCamara == null) {
//			if (other.idCamara != null)
//				return false;
//		} else if (!idCamara.equals(other.idCamara))
//			return false;
//		return true;
////		return Objects.equals(idCamara, other.idCamara) && Objects.equals(idGrupo, other.idGrupo);
//	}

}
