package com.fabian.examen.dtos;

import java.util.List;

import com.fabian.examen.entities.Persona;

public record ArregloResponse(
		String mensaje, 
		int promedio, 
		List<Persona> arreglo
		) {

}
