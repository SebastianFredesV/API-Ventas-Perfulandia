package com.perfulandia.ventas.controller;

import com.perfulandia.ventas.dto.DetalleVentaDTO;
import com.perfulandia.ventas.services.DetalleVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/detalle-ventas")

public class DetalleVentaController {

    @Autowired
    private DetalleVentaService service;

    @PostMapping
    public ResponseEntity<DetalleVentaDTO> crear(@RequestBody DetalleVentaDTO dto) {
        return ResponseEntity.ok(service.guardar(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleVentaDTO> obtenerPorId(@PathVariable Integer id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/venta/{idVenta}")
    public ResponseEntity<List<DetalleVentaDTO>> obtenerPorVenta(@PathVariable Integer idVenta) {
        List<DetalleVentaDTO> detalles = service.obtenerPorVenta(idVenta);
        return detalles.isEmpty() 
                ? ResponseEntity.noContent().build() 
                : ResponseEntity.ok(detalles);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleVentaDTO> actualizar(@PathVariable Integer id, @RequestBody DetalleVentaDTO dto) {
        return service.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (service.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
