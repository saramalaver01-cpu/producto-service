package co.empresa.libroservice.domain.service;

import co.empresa.libroservice.domain.model.entities.Libro;
import co.empresa.libroservice.domain.repository.ILibroRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Clase que implementa los métodos de la interfaz IProductoService
 * para realizar las operaciones de negocio sobre la entidad Producto
 */


@Service
public class LibroServiceImpl implements ILibroService {

    ILibroRepository productoRepository;

    public LibroServiceImpl(ILibroRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    @Transactional
    public Libro save(Libro producto) {
        return productoRepository.save(producto);
    }

    @Override
    @Transactional
    public void delete(Libro producto) {
        productoRepository.delete(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Libro> findById(Long id) {
        return productoRepository.findById(id);
    }

    @Override
    @Transactional
    public Libro update(Libro producto) {
        return productoRepository.save(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Libro> findAll() {
        return productoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Libro> findAll(Pageable pageable) {
        return productoRepository.findAll(pageable);
    }
}
