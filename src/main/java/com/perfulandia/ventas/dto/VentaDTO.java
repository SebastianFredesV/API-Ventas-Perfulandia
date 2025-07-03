package com.perfulandia.ventas.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.hateoas.RepresentationModel;

import org.hibernate.boot.jaxb.hbm.internal.RepresentationModeConverter;

@Data
public class VentaDTO extends RepresentationModel<VentaDTO>{
    private Integer idVenta;
    private Integer idCliente;
    private Integer idVendedor;
    private LocalDate fechaVenta;
    private BigDecimal total;
}
