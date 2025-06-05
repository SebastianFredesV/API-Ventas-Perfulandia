package com.perfulandia.ventas.models;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "cupones")
@Data

public class Cupon {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCupon;
    private String codigo;
    private BigDecimal descuento;
    private LocalDate fechaExpiracion;
    private Boolean activo;

    // Constructor, getters, setters, etc. pueden ser generados por Lombok

}
