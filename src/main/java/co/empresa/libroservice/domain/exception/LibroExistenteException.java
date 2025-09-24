package co.empresa.libroservice.domain.exception;

public class LibroExistenteException extends RuntimeException {
    public LibroExistenteException(String nombre) {
        super("El producto con nombre '" + nombre + "' ya existe.");
    }
}
