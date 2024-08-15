package tailor.tailor_net.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import tailor.tailor_net.model.Usuario;
import tailor.tailor_net.service.UsuarioService;

import org.springframework.ui.Model;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registrarUsuario")
    public String registrarUsuario(@ModelAttribute Usuario usuario) {
        usuarioService.guardarUsuario(usuario);
        return "redirect:/login";
    }

    @PostMapping("/iniciarSesion")
    public String iniciarSesion(@RequestParam("email") String emailUsuario, @RequestParam("clave") String contraseña, Model model) {
        Usuario usuario = usuarioService.buscarPorEmailUsuario(emailUsuario);

        if (usuario != null && usuario.getContraseña().equals(contraseña)) {
            // Comprueba el id_rol del usuario y redirige según sea el caso
            if (usuario.getIdRol() == 1) {
                return "dashboard"; // Redirige al template para administradores
            } else if (usuario.getIdRol() == 2) {
                return "redirect:/index"; // Redirige al template para usuarios regulares
            } else {
                // Gestiona otros roles o un estado por defecto aquí
                return "login"; // O una página de error/por defecto si el rol no es ni 1 ni 2
            }
        } else {
            model.addAttribute("loginError", "Nombre de usuario o contraseña incorrectos");
            return "login"; // Retorna al formulario de login si la autenticación falla
        }
    }



    @GetMapping("/login")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "login"; // Nombre de la vista del formulario de registro
    }
    @GetMapping("/d_usuarios")
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios_datos", usuarioService.listarTodosLosUsuarios());
        return "d_usuarios"; // Asegúrate de que esto coincida con el nombre de tu archivo HTML
    }

}
