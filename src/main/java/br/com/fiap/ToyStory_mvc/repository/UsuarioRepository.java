package br.com.fiap.ToyStory_mvc.repository;

import br.com.fiap.ToyStory_mvc.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);
}
