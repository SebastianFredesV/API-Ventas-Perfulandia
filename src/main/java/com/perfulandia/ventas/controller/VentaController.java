package com.perfulandia.ventas.controller;

import com.perfulandia.ventas.dto.VentaDTO;
import com.perfulandia.ventas.services.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaService service;

    @PostMapping
    public ResponseEntity<VentaDTO> crear(@RequestBody VentaDTO dto) {
        return ResponseEntity.ok(service.guardar(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaDTO> obtenerPorId(@PathVariable Integer id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<VentaDTO>> obtenerPorCliente(@PathVariable Integer clienteId) {
        List<VentaDTO> ventas = service.obtenerPorCliente(clienteId);
        return ventas.isEmpty() 
                ? ResponseEntity.noContent().build() 
                : ResponseEntity.ok(ventas);
    }
    
    @GetMapping
    public ResponseEntity<List<VentaDTO>> listar() {
        List<VentaDTO> ventas = service.listar();
        return ResponseEntity.ok(ventas);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (service.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
