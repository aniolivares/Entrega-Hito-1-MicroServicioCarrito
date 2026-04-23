package cl.duoc.backend_api.repository;

import cl.duoc.backend_api.model.ItemCarrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ItemCarritoRepository extends JpaRepository<ItemCarrito, Long> {

    // Obtener todos los items de un usuario
    List<ItemCarrito> findByUsuarioId(Long usuarioId);

    // Verificar si un producto ya está en el carrito de un usuario
    boolean existsByUsuarioIdAndProductoId(Long usuarioId, Long productoId);

    // Eliminar todos los items del carrito de un usuario
    void deleteByUsuarioId(Long usuarioId);
}