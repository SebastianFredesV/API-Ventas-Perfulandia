package com.perfulandia.ventas.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class DetalleVentaDTO {
    private Integer idDetalleVenta;
    private Integer idVenta;
    private Integer idProducto;
    private Integer cantidad;
    private BigDecimal precioUnitario;
}
