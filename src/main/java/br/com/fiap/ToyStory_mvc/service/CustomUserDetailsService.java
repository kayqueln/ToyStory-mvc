package br.com.fiap.ToyStory_mvc.service;


import br.com.fiap.ToyStory_mvc.model.Usuario;
import br.com.fiap.ToyStory_mvc.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario user = usuarioRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (String role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role));
        }

        return new User(user.getEmail(), user.getSenha(), grantedAuthorities);
    }
}
