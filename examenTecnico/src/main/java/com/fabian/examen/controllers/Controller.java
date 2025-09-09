package com.fabian.examen.controllers;

import java.util.List;
import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fabian.examen.dtos.AreaResponse;
import com.fabian.examen.dtos.ArregloResponse;
import com.fabian.examen.dtos.DeleteResponse;
import com.fabian.examen.entities.Persona;
import com.fabian.examen.repositories.PersonaRepository;
import com.fabian.examen.services.PersonaServiceImpl;

@RestController
public class Controller {
	
	private final PersonaServiceImpl service;
	private final PersonaRepository repository;

	public Controller(PersonaServiceImpl service, PersonaRepository repository) {
		this.service = service;
		this.repository = repository;
	}

	@GetMapping("/areaTriangulo/{base}/{altura}")
	public ResponseEntity<AreaResponse> areaTriangulo(@PathVariable int base, @PathVariable int altura) {
		if (base <= 0 || altura <= 0) {
	        return ResponseEntity.badRequest()
	            .body(new AreaResponse("La base y la altura deben ser positivas", 0));
	    }
		
	    int area = java.util.stream.IntStream.range(0, altura).map(i -> base).sum() / 2;
	    return ResponseEntity.ok(new AreaResponse("ok", area));
	}

	@PostMapping("/arreglo/{tamaño}")
	public ResponseEntity<ArregloResponse> generarArreglo(@PathVariable int tamaño) {
	    Random random = new Random();

	    List<Persona> personas = java.util.stream.IntStream.rangeClosed(1, tamaño)
	            .mapToObj(i -> new Persona("Persona" + random.nextInt(1000), 20 + random.nextInt(71)))
	            .toList();

	    personas.forEach(repository::save);
	    int promedio = (int) personas.stream().mapToInt(Persona::getEdad).average().orElse(0);
	    return ResponseEntity.status(HttpStatus.CREATED)
	            .body(new ArregloResponse("Arreglo generado de manera correcta", promedio, personas));
	}


	@DeleteMapping("/arreglo")
    public ResponseEntity<DeleteResponse> eliminarArreglo() {
        List<Persona> personas = service.obtenerArreglo();
        if (personas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new DeleteResponse("No se encontro informacion por eliminar"));
        }
        service.eliminarArreglo();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new DeleteResponse("Arreglo eliminado de manera correcta"));
    }

}
