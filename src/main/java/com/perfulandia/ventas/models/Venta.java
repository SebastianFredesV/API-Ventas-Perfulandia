package com.perfulandia.ventas.models;


import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.math.BigDecimal;


@Entity
@Table(name = "ventas")
@Data

public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private LocalDate fechaVenta;
    private Integer idVenta;
    private Integer idCliente;
    private Integer idVendedor;

    private BigDecimal total;

}
