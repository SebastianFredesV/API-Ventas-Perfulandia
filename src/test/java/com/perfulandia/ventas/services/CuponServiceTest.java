package com.perfulandia.ventas.services;

import com.perfulandia.ventas.dto.CuponDTO;
import com.perfulandia.ventas.models.Cupon;
import com.perfulandia.ventas.repository.CuponRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest
public class CuponServiceTest {

    @Mock
    private CuponRepository cuponRepository;

    @InjectMocks
    private CuponService cuponService;

    @Test
    public void testGuardarCupon() {
        CuponDTO dto = new CuponDTO();
        dto.setCodigo("PRUEBA10");
        dto.setDescuento(new BigDecimal("10.00"));
        dto.setActivo(true);
        dto.setFechaExpiracion(LocalDate.of(2025, 12, 31));

        Cupon cupon = new Cupon();
        //cupon.setId(1);
        cupon.setCodigo(dto.getCodigo());
        cupon.setDescuento(dto.getDescuento());
        cupon.setActivo(dto.getActivo());
        cupon.setFechaExpiracion(dto.getFechaExpiracion());

        when(cuponRepository.save(any(Cupon.class))).thenReturn(cupon);

        CuponDTO result = cuponService.guardar(dto);

        assertNotNull(result);
        assertEquals("PRUEBA10", result.getCodigo());
        assertEquals(new BigDecimal("10.00"), result.getDescuento());
    }
}
