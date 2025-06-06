package com.perfulandia.ventas.repository;

import com.perfulandia.ventas.models.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Integer> {
    List<DetalleVenta> findByIdVenta(Integer idVenta);
}
