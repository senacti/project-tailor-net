package tailor.tailor_net;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tailor.tailor_net.model.Venta;
import tailor.tailor_net.repository.VentaRepository;
import tailor.tailor_net.service.VentaService;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class VentaServiceTest {

    @Mock
    private VentaRepository ventaRepository;

    @InjectMocks
    private VentaService ventaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGuardarVenta() {
        Venta nuevaVenta = new Venta();
        nuevaVenta.setFechaVenta(LocalDate.now());
        nuevaVenta.setTotalVenta(100.0f);
        nuevaVenta.setIdUsuario(1);

        // Simula la acción del repositorio
        when(ventaRepository.save(any(Venta.class))).thenReturn(nuevaVenta);

        // Llama al método de servicio para guardar la venta
        ventaService.guardarVenta(nuevaVenta);

        // Verifica que el método save del repositorio fue llamado una vez
        verify(ventaRepository, times(1)).save(any(Venta.class));
    }
}

