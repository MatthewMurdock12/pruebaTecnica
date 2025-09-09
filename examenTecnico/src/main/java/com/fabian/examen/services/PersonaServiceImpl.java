package com.fabian.examen.services;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.fabian.examen.entities.Persona;
import com.fabian.examen.repositories.PersonaRepository;
import java.util.stream.IntStream;

@Service
public class PersonaServiceImpl implements PersonaService{

	private final PersonaRepository repository;
    private Random random = new Random();

    public PersonaServiceImpl(PersonaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Persona> generarArreglo(int tamaño) {
        repository.deleteAll();
        IntStream.range(0, tamaño).forEach(i -> {
            String nombre = "Persona" + random.nextInt(1000);
            int edad = 20 + random.nextInt(71);
            repository.save(new Persona(nombre, edad));
        });
        return repository.findAll();
    }

    @Override
    public void eliminarArreglo() {
        repository.deleteAll();
    }

    @Override
    public List<Persona> obtenerArreglo() {
        return repository.findAll();
    }
    
}
