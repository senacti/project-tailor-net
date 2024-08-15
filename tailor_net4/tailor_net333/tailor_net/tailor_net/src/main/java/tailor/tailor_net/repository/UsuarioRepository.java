package tailor.tailor_net.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tailor.tailor_net.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByemail(String email);
}

