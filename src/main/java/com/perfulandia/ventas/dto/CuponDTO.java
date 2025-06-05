package com.perfulandia.ventas.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data

public class CuponDTO {
    private Integer id;
    private String codigo;
    private BigDecimal descuento;
    private boolean activo;
    private LocalDate fechaExpiracion;


}
