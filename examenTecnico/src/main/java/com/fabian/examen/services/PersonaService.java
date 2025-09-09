package com.fabian.examen.services;

import java.util.List;

import com.fabian.examen.entities.Persona;

public interface PersonaService {
	List<Persona> generarArreglo(int tamaño);
    void eliminarArreglo();
    List<Persona> obtenerArreglo();
}
