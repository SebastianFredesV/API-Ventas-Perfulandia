package com.perfulandia.ventas.dto;


import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data

public class VentaDTO {
    private Integer idVenta;
    private Integer idCliente;
    private Integer idVendedor;
    private LocalDate fechaVenta;
    private BigDecimal total;

}
