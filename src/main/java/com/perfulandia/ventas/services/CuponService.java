package com.perfulandia.ventas.services;


import com.perfulandia.ventas.dto.CuponDTO;
import com.perfulandia.ventas.models.Cupon;
import com.perfulandia.ventas.repositories.CuponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CuponService {

    @Autowired
    private CuponRepository repository;

    public CuponDTO guardar(CuponDTO dto) {
        Cupon cupon = toEntity(dto);
        Cupon savedCupon = repository.save(cupon);
        return toDTO(savedCupon);
    }

    public List<CuponDTO> listar() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<CuponDTO> obtenerPorCodigo(Integer codigo) {
        return repository.findByCodigo(codigo)
                .filter(this::esValido)
                .map(this::toDTO);
    }

    public Boolean eliminar(Integer codigo) {
        if (repository.existsByCodigo(codigo)) {
            repository.deleteByCodigo(codigo);
            return true;
        }
        return false;
    }

    public Boolean eliminar(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    private Boolean eliminar(Cupon cupon) {
        return cupon.getActivo() &&
        cupon.getFechaExpiracion().isAfter(LocalDateTime.now());
    }

    //Entity a DTO
    private CuponDTO toDTO(Cupon cupon) {
        CuponDTO dto = new CuponDTO();
        dto.setId(cupon.getId());
        dto.setCodigo(cupon.getCodigo());
        dto.setDescuento(cupon.getDescuento());
        dto.setFechaExpiracion(cupon.getFechaExpiracion());
        dto.setActivo(cupon.getActivo());
        return dto;
    }

    //DTO a Entity
    private Cupon toEntity(CuponDTO dto) {
        Cupon cupon = new Cupon();
        cupon.setId(dto.getId());
        cupon.setCodigo(dto.getCodigo());
        cupon.setDescuento(dto.getDescuento());
        cupon.setFechaExpiracion(dto.getFechaExpiracion());
        cupon.setActivo(dto.getActivo());
        return cupon;
    }
    



}
