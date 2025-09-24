package co.empresa.libroservice.domain.service;


import co.empresa.libroservice.domain.model.entities.Libro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Interface que define los métodos que se pueden realizar sobre la entidad Producto
 */
public interface ILibroService {
    Libro save(Libro producto);
    void delete(Libro producto);
    Optional<Libro> findById(Long id);
    Libro update(Libro producto);
    List<Libro> findAll();
    Page<Libro> findAll(Pageable pageable);
}
