package com.perfulandia.ventas.repository;

import com.perfulandia.ventas.models.Cupon;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CuponRepository extends JpaRepository<Cupon, Integer> {
    Optional<Cupon> findByCodigo(String codigo);
}
