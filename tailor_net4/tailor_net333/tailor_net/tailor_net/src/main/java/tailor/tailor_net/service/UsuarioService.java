package tailor.tailor_net.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tailor.tailor_net.model.Usuario;
import tailor.tailor_net.repository.UsuarioRepository;
import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario guardarUsuario(Usuario usuario) {
        usuario.setIdRol(2); 
        return usuarioRepository.save(usuario);
    }
    public Usuario buscarPorEmailUsuario(String email) {
        return usuarioRepository.findByemail(email);
    }
    public List<Usuario> listarTodosLosUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();

        return usuarios;
    }
    
}

