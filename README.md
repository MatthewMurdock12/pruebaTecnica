# Examen Técnico - API Spring Boot

## Descripción

Este proyecto es un **API desarrollado en Spring Boot** que cumple con los requerimientos de un examen técnico. Proporciona tres endpoints principales:

### 1. `GET /areaTriangulo/{base}/{altura}`
Calcula el área de un triángulo **sin utilizar el operador de multiplicación (*)**.

### 2. `POST /arreglo/{tamaño}`
Genera un arreglo de objetos `Persona` con:
- **ID** incremental generado por la base de datos H2.
- **Nombre** aleatorio.
- **Edad** aleatoria entre 20 y 90.

Devuelve el **promedio de edades** y la lista de personas generadas.

### 3. `DELETE /arreglo`
Elimina todos los registros de personas generados previamente.

- Devuelve código **201** con mensaje si se elimina el arreglo.  
- Devuelve código **204** con mensaje si no hay nada que eliminar, aunque técnicamente **HTTP 204 no debe tener cuerpo**, se incluyó el mensaje porque así lo solicitaba el enunciado del examen.

---

## Tecnologías utilizadas

- **Java 17**  
- **Spring Boot**  
- **H2 Database** (emulación de base de datos en memoria)  
- **Maven**  
- Programación funcional con **IntStream** y **Stream API**  

---

## Estructura del proyecto

- **Controller**: define los endpoints de la API.  
- **Service + Interface**: encapsula la lógica de negocio y el acceso a la base de datos.  
- **Entity**: clase `Persona` mapeada a la base de datos H2.  
- **DTOs**: clases para las respuestas de cada endpoint (`AreaResponse`, `ArregloResponse`, `DeleteResponse`).  
- **Repository**: interfaz que extiende `JpaRepository` para la entidad `Persona`.  

---

## Notas importantes

### Singleton

El patrón **Singleton** se refiere a tener una **única instancia** de una clase durante toda la ejecución de la aplicación.  

- En aplicaciones Spring Boot, los beans anotados con `@Service`, `@Repository` o `@Component` son **singleton por defecto**, por lo que no es necesario implementar el patrón manualmente.  
- Para fines de examen, se podría implementar manualmente usando un **constructor privado** y un método `getInstance()`, pero no es necesario en esta implementación.

### HTTP 204 No Content

- Según la especificación HTTP, el código **204** indica “No Content” y **no debe incluir cuerpo**.  
- En este proyecto, se incluyó un mensaje en el body para cumplir con lo solicitado en el enunciado del examen, aunque no es técnicamente correcto según el estándar.

---

## Ejemplos de uso

### GET /areaTriangulo/{base}/{altura}


Respuesta:

```json
{
    "mensaje": "ok",
    "area": 25
}

POST http://localhost:8080/arreglo/5

{
    "mensaje": "Arreglo generado de manera correcta",
    "promedio": 56,
    "arreglo": [
        { "id": 1, "nombre": "Persona123", "edad": 45 },
        { "id": 2, "nombre": "Persona456", "edad": 62 }
    ]
}

DELETE http://localhost:8080/arreglo

{
    "mensaje": "Arreglo eliminado de manera correcta"
}

{
    "mensaje": "No se encontro informacion por eliminar"
}

## Configuración de H2
- **Consola habilitada en:** [http://localhost:8080/h2-console](http://localhost:8080/h2-console)  
- **URL:** `jdbc:h2:mem:testdb`  
- **Usuario:** `sa`  
- **Contraseña:** (vacía)
