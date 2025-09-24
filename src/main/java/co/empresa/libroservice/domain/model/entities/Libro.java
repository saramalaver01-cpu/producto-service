package co.empresa.libroservice.domain.model.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message ="No puede estar vacio")
    @Size(min=2, max=20, message="El tamaño tiene que estar entre 2 y 20")
    @Column(nullable=false)
    private String nombre;

    @Size(max = 255, message = "La descripción no puede tener más de 255 caracteres")
    private String descripcion;

    @Min(value = 0, message = "El precio no puede ser negativo")
    @NotNull(message = "El precio es obligatorio")
    @Column(nullable = false)
    private Double precio;

    @NotNull(message = "El stock no puede ser nulo")
    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stock;

    @Size(max = 100, message = "El nombre del archivo no puede exceder los 100 caracteres")
    @Pattern(
            regexp = "^[\\w,\\s-]+\\.(jpg|jpeg|png|gif|bmp|webp)$",
            message = "El nombre del archivo debe ser válido y tener una extensión permitida: jpg, jpeg, png, gif, bmp, webp"
    )
    private String foto;
}