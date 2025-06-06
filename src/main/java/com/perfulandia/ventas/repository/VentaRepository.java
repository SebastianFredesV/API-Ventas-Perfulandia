package com.perfulandia.ventas.repository;

import com.perfulandia.ventas.models.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VentaRepository extends JpaRepository<Venta, Integer> {
    List<Venta> findByIdCliente(Integer idCliente);
}
