package com.perfulandia.ventas.controller;

import com.perfulandia.ventas.dto.CuponDTO;
import com.perfulandia.ventas.services.CuponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/cupones")

public class CuponController {

    @Autowired
    private CuponService service;

    @PostMapping("/validar")
    public ResponseEntity<CuponDTO> validar(@RequestParam Integer codigo) {
        return service.obtenerPorCodigo(codigo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CuponDTO> crear(@RequestBody CuponDTO dto) {
        return ResponseEntity.ok(service.guardar(dto));
    }

    @GetMapping
    public ResponseEntity<List<CuponDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        return service.eliminar(id) 
                ? ResponseEntity.noContent().build() 
                : ResponseEntity.notFound().build();
    }



}
