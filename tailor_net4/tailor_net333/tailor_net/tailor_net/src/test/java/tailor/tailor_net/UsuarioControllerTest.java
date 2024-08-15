package tailor.tailor_net;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import tailor.tailor_net.controller.UsuarioController;
import tailor.tailor_net.model.Usuario;
import tailor.tailor_net.service.UsuarioService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@SpringBootTest
@AutoConfigureMockMvc
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

@Test
void testRegistrarUsuarioDirecto() {
    Usuario nuevoUsuario = new Usuario();
    nuevoUsuario.setNombre("Juan Perez");
    nuevoUsuario.setContraseña("password123");
    nuevoUsuario.setEmail("juan.perez@example.com");
    nuevoUsuario.setTelefono(1234567890L);
    nuevoUsuario.setIdRol(2);

    when(usuarioService.guardarUsuario(any(Usuario.class))).thenReturn(nuevoUsuario);

    String result = usuarioController.registrarUsuario(nuevoUsuario);

    verify(usuarioService, times(1)).guardarUsuario(any(Usuario.class));
    assertEquals("redirect:/login", result);
}
}
