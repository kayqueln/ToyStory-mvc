package br.com.fiap.ToyStory_mvc.config;

import br.com.fiap.ToyStory_mvc.model.Usuario;
import br.com.fiap.ToyStory_mvc.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashSet;

@Configuration
public class DataInitializer {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostConstruct
    public void init() {
        if (usuarioRepository.findByEmail("admin@admin.com") == null) {
            Usuario admin = new Usuario();
            admin.setNome("Admin User");
            admin.setEmail("admin@admin.com");
            admin.setSenha(passwordEncoder.encode("admin123"));
            admin.setRoles(new HashSet<>(Collections.singletonList("ROLE_ADMIN")));
            usuarioRepository.save(admin);
        }
    }
}
