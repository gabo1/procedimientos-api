package com.seguritech.spring.datajpa.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GrupoCamaraRequestDTO {
	private List<GrupoCamaraDTO> camaras;
}
