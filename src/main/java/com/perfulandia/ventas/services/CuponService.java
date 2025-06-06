package com.perfulandia.ventas.services;

import com.perfulandia.ventas.dto.CuponDTO;
import com.perfulandia.ventas.models.Cupon;
import com.perfulandia.ventas.repository.CuponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CuponService {

    @Autowired
    private CuponRepository repository;

    public CuponDTO guardar(CuponDTO dto) {
        Cupon cupon = toEntity(dto);
        Cupon saved = repository.save(cupon);
        return toDTO(saved);
    }

    public List<CuponDTO> listar() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private Boolean esValido(Cupon cupon) {
        return cupon.getActivo() &&
            cupon.getFechaExpiracion().isAfter(LocalDate.now());
    }

    public Optional<CuponDTO> obtenerPorCodigo(String codigo) {
        return repository.findByCodigo(codigo)
                .filter(this::esValido)
                .map(this::toDTO);
    }

    public Boolean eliminar(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    //Entity a DTO
    private CuponDTO toDTO(Cupon cupon) {
        CuponDTO dto = new CuponDTO();
        dto.setIdCupon(cupon.getIdCupon());
        dto.setCodigo(cupon.getCodigo());
        dto.setDescuento(cupon.getDescuento());
        dto.setFechaExpiracion(cupon.getFechaExpiracion());
        dto.setActivo(cupon.getActivo());
        return dto;
    }

    //DTO a Entity
    private Cupon toEntity(CuponDTO dto) {
        Cupon cupon = new Cupon();
        cupon.setIdCupon(dto.getIdCupon());
        cupon.setCodigo(dto.getCodigo());
        cupon.setDescuento(dto.getDescuento());
        cupon.setFechaExpiracion(dto.getFechaExpiracion());
        cupon.setActivo(dto.getActivo());
        return cupon;
    }
}
