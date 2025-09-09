package com.fabian.examen.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fabian.examen.entities.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Integer>{

}
