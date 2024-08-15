package tailor.tailor_net;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import tailor.tailor_net.controller.AgendamientoController;
import tailor.tailor_net.model.Agendamiento;
import tailor.tailor_net.service.AgendamientoService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(AgendamientoController.class)
class AgendamientoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AgendamientoService agendamientoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testMostrarListaDeCitas() throws Exception {
        // Ajusta los datos simulados para que coincidan con los datos reales que se espera que devuelva el servicio
        Agendamiento cita1 = new Agendamiento(1L, LocalDate.of(2024, 6, 8), "cfghjklñ", "jojan", 1234567890L, LocalTime.of(14, 1));
        Agendamiento cita2 = new Agendamiento(2L, LocalDate.of(2024, 6, 29), "Vestido", "karen", 3105521734L, LocalTime.of(13, 0));
        Agendamiento cita3 = new Agendamiento(3L, LocalDate.of(2024, 6, 27), "saco", "diana delgado", 3224463163L, LocalTime.of(13, 0));
        Agendamiento cita4 = new Agendamiento(4L, LocalDate.of(2024, 8, 12), "Servicio de sastreria", "Jojan Stiven", 3184893875L, LocalTime.of(13, 0));
        Agendamiento cita5 = new Agendamiento(5L, LocalDate.of(2024, 8, 12), "Servicio de sastreria", "Jojan Stiven", 3184893875L, LocalTime.of(13, 0));
        
        List<Agendamiento> citas = Arrays.asList(cita1, cita2, cita3, cita4, cita5);

        // Simula el comportamiento del servicio
        when(agendamientoService.listarTodasLasCitas()).thenReturn(citas);

        // Realiza una petición GET a /agendamientoCitas
        mockMvc.perform(get("/agendamientoCitas"))
                .andExpect(status().isOk())
                .andExpect(view().name("agendamientoCitas"))
                .andExpect(model().attributeExists("citas"))
                .andExpect(model().attribute("citas", citas));

        // Verifica que el servicio fue llamado una vez
        verify(agendamientoService, times(1)).listarTodasLasCitas();
    }
}



