package co.empresa.libroservice.delivery.rest;

import co.empresa.libroservice.domain.exception.NoHayLibrosException;
import co.empresa.libroservice.domain.exception.PaginaSinLibrosException;
import co.empresa.libroservice.domain.exception.LibroNoEncontradoException;
import co.empresa.libroservice.domain.exception.ValidationException;
import co.empresa.libroservice.domain.model.entities.Libro;
import co.empresa.libroservice.domain.service.ILibroService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/libro-service")
public class LibroRestController {

    // Declaramos como final el servicio para mejorar la inmutabilidad
    private final ILibroService libroService;

    // Constantes para los mensajes de respuesta
    private static final String MENSAJE = "mensaje";
    private static final String LIBRO = "libro";
    private static final String LIBROS = "libros";

    // Inyección de dependencia del servicio que proporciona servicios de CRUD
    public LibroRestController(ILibroService libroService) {
        this.libroService = libroService;
    }

    /**
     * Listar todos los libros.
     */

    @GetMapping("/libros")
    public ResponseEntity<Map<String, Object>> getLibros() {
        List<Libro> libros = libroService.findAll();
        if (libros.isEmpty()) {
            throw new NoHayLibrosException();
        }
        Map<String, Object> response = new HashMap<>();
        response.put(LIBROS, libros);
        return ResponseEntity.ok(response);
    }

    /**
     * Listar libros con paginación.
     */

    @GetMapping("/libro/page/{page}")
    public ResponseEntity<Object> index(@PathVariable Integer page) {
        Pageable pageable = PageRequest.of(page, 4);
        Page<Libro> libros = libroService.findAll(pageable);
        if (libros.isEmpty()) {
            throw new PaginaSinLibrosException(page);
        }
        return ResponseEntity.ok(libros);
    }

    /**
     * Crear un nuevo libro pasando el objeto en el cuerpo de la petición, usando validaciones
     */

    @PostMapping("/libros")
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody Libro libro, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(result);
        }
        Map<String, Object> response = new HashMap<>();
        Libro nuevoLibro = libroService.save(libro);
        response.put(MENSAJE, "El libro ha sido creado con éxito!");
        response.put(LIBRO, nuevoLibro);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    /**
     * Eliminar un libro pasando el objeto en el cuerpo de la petición.
     */

    @DeleteMapping("/libros")
    public ResponseEntity<Map<String, Object>> delete(@RequestBody Libro  libro) {
        libroService.findById(libro.getId())
            .orElseThrow(() -> new LibroNoEncontradoException(libro.getId()));
        libroService.delete(libro);
        Map<String, Object> response = new HashMap<>();
        response.put(MENSAJE, "El libro ha sido eliminado con éxito!");
        response.put(LIBRO, null);
        return ResponseEntity.ok(response);
    }

    /**
     * Actualizar un libro pasando el objeto en el cuerpo de la petición.
     * @param libro: Objeto Libro que se va a actualizar
     */


    @PutMapping("/s")
    public ResponseEntity<Map<String, Object>> update(@Valid @RequestBody Libro libro, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(result);
        }
        libroService.findById(libro.getId())
                .orElseThrow(() -> new LibroNoEncontradoException(libro.getId()));
        Map<String, Object> response = new HashMap<>();
        Libro libroActualizado = libroService.update(libro);
        response.put(MENSAJE, "El libro ha sido actualizado con éxito!");
        response.put(LIBRO, libroActualizado);
        return ResponseEntity.ok(response);
    }

    /**
     * Obtener un libro por su ID.
     */

    @GetMapping("/libros/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable Long id) {
        Libro libro = libroService.findById(id)
                .orElseThrow(() -> new LibroNoEncontradoException(id));
        Map<String, Object> response = new HashMap<>();
        response.put(MENSAJE, "El libro ha sido encontrado con éxito!");
        response.put(LIBRO, libro);
        return ResponseEntity.ok(response);
    }
}
