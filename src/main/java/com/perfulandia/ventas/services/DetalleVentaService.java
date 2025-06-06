package com.perfulandia.ventas.services;

import com.perfulandia.ventas.dto.DetalleVentaDTO;
import com.perfulandia.ventas.models.DetalleVenta;
import com.perfulandia.ventas.repository.DetalleVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DetalleVentaService {

    @Autowired
    private DetalleVentaRepository repository;

    public List<DetalleVentaDTO> listar() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public DetalleVentaDTO guardar(DetalleVentaDTO dto) {
        DetalleVenta detalle = toEntity(dto);
        DetalleVenta saved = repository.save(detalle);
        return toDTO(saved);
    }

    public Optional<DetalleVentaDTO> obtenerPorId(Integer id) {
        return repository.findById(id)
                .map(this::toDTO);
    }

    public List<DetalleVentaDTO> obtenerPorVenta(Integer idVenta) {
        return repository.findByIdVenta(idVenta).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<DetalleVentaDTO> actualizar(Integer id, DetalleVentaDTO dto) {
        return repository.findById(id).map(detalle -> {
            detalle.setIdVenta(dto.getIdVenta());
            detalle.setIdProducto(dto.getIdProducto());
            detalle.setCantidad(dto.getCantidad());
            detalle.setPrecioUnitario(dto.getPrecioUnitario());
            return toDTO(repository.save(detalle));
        });
    }

    public Boolean eliminar(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    //Entity a DTO
    private DetalleVentaDTO toDTO(DetalleVenta detalle) {
        DetalleVentaDTO dto = new DetalleVentaDTO();
        dto.setIdDetalleVenta(detalle.getIdDetalleVenta());
        dto.setIdVenta(detalle.getIdVenta());
        dto.setIdProducto(detalle.getIdProducto());
        dto.setCantidad(detalle.getCantidad());
        dto.setPrecioUnitario(detalle.getPrecioUnitario());
        return dto;
    }

    //DTO a Entity
    private DetalleVenta toEntity(DetalleVentaDTO dto) {
        DetalleVenta detalle = new DetalleVenta();
        detalle.setIdDetalleVenta(dto.getIdDetalleVenta());
        detalle.setIdVenta(dto.getIdVenta());
        detalle.setIdProducto(dto.getIdProducto());
        detalle.setCantidad(dto.getCantidad());
        detalle.setPrecioUnitario(dto.getPrecioUnitario());
        return detalle;
    }
}
