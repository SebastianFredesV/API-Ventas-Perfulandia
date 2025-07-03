package com.perfulandia.ventas.services;

import com.perfulandia.ventas.dto.VentaDTO;
import com.perfulandia.ventas.models.Venta;
import com.perfulandia.ventas.repository.VentaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class VentaServiceTest {

    @Mock
    private VentaRepository ventaRepository;

    @InjectMocks
    private VentaService ventaService;

    public VentaServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGuardarVenta() {
        VentaDTO dto = new VentaDTO();
        dto.setIdCliente(1);
        dto.setIdVendedor(2);
        dto.setFechaVenta(LocalDate.of(2025, 6, 10));
        dto.setTotal(new BigDecimal("5000"));

        Venta venta = new Venta();
        venta.setIdVenta(1);
        venta.setIdCliente(1);
        venta.setIdVendedor(2);
        venta.setFechaVenta(dto.getFechaVenta());
        venta.setTotal(dto.getTotal());

        when(ventaRepository.save(org.mockito.ArgumentMatchers.any(Venta.class))).thenReturn(venta);

        VentaDTO resultado = ventaService.guardar(dto);

        assertEquals(dto.getIdCliente(), resultado.getIdCliente());
        assertEquals(dto.getIdVendedor(), resultado.getIdVendedor());
        assertEquals(dto.getFechaVenta(), resultado.getFechaVenta());
        assertEquals(dto.getTotal(), resultado.getTotal());
    }
}
