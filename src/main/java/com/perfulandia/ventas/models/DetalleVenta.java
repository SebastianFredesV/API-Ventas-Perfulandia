package com.perfulandia.ventas.models;

import jakarta.persistence.*;
import lombok.Data;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import java.math.BigDecimal;

@Entity
@Table(name = "detalleventa")
@Data

public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDetalleVenta;

    private Integer idVenta;
    private Integer idProducto;
    private Integer cantidad;
    private BigDecimal precioUnitario;
}
