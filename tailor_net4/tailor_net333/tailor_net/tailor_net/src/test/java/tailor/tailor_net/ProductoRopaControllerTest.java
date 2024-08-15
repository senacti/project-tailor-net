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
import tailor.tailor_net.model.ProductoRopa;
import tailor.tailor_net.service.ProductoRopaService;
import tailor.tailor_net.controller.ProductoRopaController;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductoRopaController.class)
class ProductoRopaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoRopaService productoRopaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testEliminarProducto() throws Exception {
        int idProducto = 1;

        // Realiza una petición POST para eliminar un producto
        mockMvc.perform(post("/producto_eliminar")
                .param("idProducto", String.valueOf(idProducto)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/inventario"));

        // Verifica que el método eliminarProductoRopaPorId fue llamado una vez con el id correcto
        verify(productoRopaService, times(1)).eliminarProductoRopaPorId(idProducto);
    }
}
