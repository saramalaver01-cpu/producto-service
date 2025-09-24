package co.empresa.libroservice.domain.repository;

import co.empresa.libroservice.domain.model.entities.Libro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Interface que hereda de JpaRepository para realizar las
 * operaciones de CRUD paginacion y ordenamiento sobre la entidad Producto
 */
public interface ILibroRepository extends JpaRepository<Libro, Long> {

    // Aquí podrías definir métodos personalizados, por ejemplo:
    // List<Producto> findByNombre(String nombre);
}
