package co.empresa.libroservice.domain.exception;

public class NoHayLibrosException extends RuntimeException {
    public NoHayLibrosException() {
        super("No hay productos en la base de datos.");
    }
}