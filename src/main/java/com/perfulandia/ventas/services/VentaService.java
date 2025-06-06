package com.perfulandia.ventas.services;

import com.perfulandia.ventas.repository.VentaRepository;
import com.perfulandia.ventas.models.Venta;
import com.perfulandia.ventas.dto.VentaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VentaService {

    @Autowired
    private VentaRepository repository;

    public List<VentaDTO> listar() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<VentaDTO> obtenerPorId(Integer id) {
        return repository.findById(id)
                .map(this::toDTO);
    }

    public List<VentaDTO> obtenerPorCliente(Integer clienteId) {
        return repository.findByIdCliente(clienteId).stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    public VentaDTO guardar(VentaDTO dto) {
        Venta venta = toEntity(dto);
        Venta savedVenta = repository.save(venta);
        return toDTO(savedVenta);
    }

    public Optional<VentaDTO> actualizar(Integer id, VentaDTO dto) {
        return repository.findById(id).map(venta -> {
            venta.setIdCliente(dto.getIdCliente());
            venta.setIdVendedor(dto.getIdVendedor());
            venta.setFechaVenta(dto.getFechaVenta());
            venta.setTotal(dto.getTotal());
            return toDTO(repository.save(venta));
        });
    }

    public Boolean eliminar(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    private Venta toEntity(VentaDTO dto) {
        Venta venta = new Venta();
        venta.setIdVenta(dto.getIdVenta());
        venta.setIdCliente(dto.getIdCliente());
        venta.setIdVendedor(dto.getIdVendedor());
        venta.setFechaVenta(dto.getFechaVenta());
        venta.setTotal(dto.getTotal());
        return venta;
    }

    private Venta toDTO(Venta venta) {
        VentaDTO dto = new VentaDTO();
        dto.setIdVenta(venta.getIdVenta());
        dto.setIdCliente(venta.getIdCliente());
        dto.setIdVendedor(venta.getIdVendedor());
        dto.setFechaVenta(venta.getFechaVenta());
        dto.setTotal(venta.getTotal());
        return dto;
    }
}
