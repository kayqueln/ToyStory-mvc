package br.com.fiap.ToyStory_mvc.config;

import br.com.fiap.ToyStory_mvc.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/",
                                "/home",
                                "/brinquedo",
                                "/brinquedo/lancamentos",
                                "/contato",
                                "/usuario/novo",
                                "/usuario/cadastro",
                                "/login")
                        .permitAll()
                        .requestMatchers("/admin",
                                "/brinquedo/admin",
                                "/brinquedo/salvar",
                                "/brinquedo/novo",
                                "/brinquedo/editar/**",
                                "/brinquedo/excluir/**").hasRole("ADMIN")
                        .requestMatchers("/compras").hasRole("USER")
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll())
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
