package br.com.fiap.ToyStory_mvc.service;

import br.com.fiap.ToyStory_mvc.dto.CadastroUsuarioDTO;
import br.com.fiap.ToyStory_mvc.model.Usuario;
import br.com.fiap.ToyStory_mvc.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public Usuario cadastrarUsuario(CadastroUsuarioDTO cadastroUsuarioDTO) throws Exception {
        if (usuarioRepository.findByEmail(cadastroUsuarioDTO.email()) != null) {
            throw new Exception("Esse usuário já possui um cadastro");

        }

        Usuario admin = new Usuario();
        admin.setNome(cadastroUsuarioDTO.nome());
        admin.setEmail(cadastroUsuarioDTO.email());
        admin.setSenha(passwordEncoder.encode(cadastroUsuarioDTO.senha()));
        admin.setRoles(new HashSet<>(Collections.singletonList("ROLE_USER")));

        return usuarioRepository.save(admin);
    }
}
