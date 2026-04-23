package cl.duoc.backend_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "items_carrito")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ItemCarrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El ID de usuario es obligatorio")
    @Positive(message = "El ID de usuario debe ser un número positivo")
    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @NotNull(message = "El ID de producto es obligatorio")
    @Positive(message = "El ID de producto debe ser un número positivo")
    @Column(name = "producto_id", nullable = false)
    private Long productoId;

    @NotBlank(message = "El nombre del producto es obligatorio")
    @Column(name = "nombre_producto", nullable = false)
    private String nombreProducto;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad mínima es 1")
    @Max(value = 99, message = "La cantidad máxima es 99")
    @Column(nullable = false)
    private Integer cantidad;

    @NotNull(message = "El precio unitario es obligatorio")
    @Positive(message = "El precio unitario debe ser mayor a 0")
    @Column(name = "precio_unitario", nullable = false)
    private Double precioUnitario;

    @Column(name = "fecha_agregado", updatable = false)
    private LocalDateTime fechaAgregado;

    @PrePersist
    public void prePersist() {
        this.fechaAgregado = LocalDateTime.now();
    }
}