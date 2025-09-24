package co.empresa.libroservice.domain.exception;

public class LibroNoEncontradoException extends RuntimeException {
    public LibroNoEncontradoException(Long id) {
        super("El producto con ID " + id + " no fue encontrado.");
    }
}