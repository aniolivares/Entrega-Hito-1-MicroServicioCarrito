package cl.duoc.backend_api.service;

import cl.duoc.backend_api.model.ItemCarrito;
import cl.duoc.backend_api.repository.ItemCarritoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemCarritoService {

    private final ItemCarritoRepository itemCarritoRepository;

    public ItemCarrito agregarItem(ItemCarrito item) {
        if (itemCarritoRepository.existsByUsuarioIdAndProductoId(
                item.getUsuarioId(), item.getProductoId())) {
            throw new RuntimeException("El producto ya está en el carrito de este usuario");
        }
        return itemCarritoRepository.save(item);
    }

    public List<ItemCarrito> obtenerTodos() {
        return itemCarritoRepository.findAll();
    }

    public List<ItemCarrito> obtenerPorUsuario(Long usuarioId) {
        List<ItemCarrito> items = itemCarritoRepository.findByUsuarioId(usuarioId);
        if (items.isEmpty()) {
            throw new RuntimeException("No se encontraron items para el usuario ID: " + usuarioId);
        }
        return items;
    }

    public ItemCarrito obtenerPorId(Long id) {
        return itemCarritoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item no encontrado con ID: " + id));
    }

    public ItemCarrito actualizarItem(Long id, ItemCarrito datosNuevos) {
        ItemCarrito existente = itemCarritoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item no encontrado con ID: " + id));
        existente.setNombreProducto(datosNuevos.getNombreProducto());
        existente.setCantidad(datosNuevos.getCantidad());
        existente.setPrecioUnitario(datosNuevos.getPrecioUnitario());
        return itemCarritoRepository.save(existente);
    }

    public void eliminarItem(Long id) {
        if (!itemCarritoRepository.existsById(id)) {
            throw new RuntimeException("Item no encontrado con ID: " + id);
        }
        itemCarritoRepository.deleteById(id);
    }

    public void vaciarCarrito(Long usuarioId) {
        List<ItemCarrito> items = itemCarritoRepository.findByUsuarioId(usuarioId);
        if (items.isEmpty()) {
            throw new RuntimeException("El carrito del usuario ID: " + usuarioId + " ya está vacío");
        }
        itemCarritoRepository.deleteByUsuarioId(usuarioId);
    }
}