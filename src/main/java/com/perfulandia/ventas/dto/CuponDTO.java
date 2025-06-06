package com.perfulandia.ventas.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CuponDTO {
    private Integer idCupon;
    private String codigo;
    private BigDecimal descuento;
    private Boolean activo;
    private LocalDate fechaExpiracion;
}
