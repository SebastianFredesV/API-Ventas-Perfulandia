package com.perfulandia.ventas.repository;

import com.ventas.models.Cupon;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public class CuponRepository extends JpaRepository<Cupon, Integer> {

    Optional<Cupon> findByCodigo(String codigo);


}
