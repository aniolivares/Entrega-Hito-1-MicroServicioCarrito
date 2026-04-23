package cl.duoc.backend_api.controller;

import cl.duoc.backend_api.model.ItemCarrito;
import cl.duoc.backend_api.service.ItemCarritoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/carrito")
@RequiredArgsConstructor

public class ItemCarritoController {

    private final ItemCarritoService itemCarritoService;

    @PostMapping
    public ResponseEntity<ItemCarrito> agregar(@Valid @RequestBody ItemCarrito item) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemCarritoService.agregarItem(item));
    }

    @GetMapping
    public ResponseEntity<List<ItemCarrito>> obtenerTodos() {
        return ResponseEntity.ok(itemCarritoService.obtenerTodos());
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<ItemCarrito>> obtenerPorUsuario(@PathVariable long usuarioId) {
        return ResponseEntity.ok(itemCarritoService.obtenerPorUsuario(usuarioId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemCarrito> obtenerPorId(@PathVariable long id) {
        return ResponseEntity.ok(itemCarritoService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemCarrito> actualizar(@PathVariable long id,
                                                   @Valid @RequestBody ItemCarrito datosNuevos) {
        return ResponseEntity.ok(itemCarritoService.actualizarItem(id, datosNuevos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarItem(@PathVariable long id) {
        itemCarritoService.eliminarItem(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/usuario/{usuarioId}/vaciar")
    public ResponseEntity<Void> vaciarCarrito(@PathVariable long usuarioId) {
        itemCarritoService.vaciarCarrito(usuarioId);
        return ResponseEntity.noContent().build();
    }
}