package co.empresa.libroservice.domain.exception;

public class PaginaSinLibrosException extends RuntimeException {
    public PaginaSinLibrosException(int page) {
        super("No hay productos en la página solicitada: " + page);
    }
}