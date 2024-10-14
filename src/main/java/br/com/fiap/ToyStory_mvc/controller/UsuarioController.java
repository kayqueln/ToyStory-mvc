package br.com.fiap.ToyStory_mvc.controller;

import br.com.fiap.ToyStory_mvc.dto.CadastroUsuarioDTO;
import br.com.fiap.ToyStory_mvc.model.Usuario;
import br.com.fiap.ToyStory_mvc.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.servlet.http.HttpServletRequest;

@RequestMapping("/usuario")
@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/novo")
    public String novoUsuario(CadastroUsuarioDTO cadastroUsuarioDTO){
        return "usuario/cadastro-usuario";
    }

    @PostMapping("/cadastro")
    @Transactional
    public String cadastroUsuario(@ModelAttribute CadastroUsuarioDTO cadastroUsuarioDTO, HttpServletRequest request) throws Exception {
        Usuario usuario = usuarioService.cadastrarUsuario(cadastroUsuarioDTO);

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(cadastroUsuarioDTO.email(), cadastroUsuarioDTO.senha());
        Authentication authentication = authenticationManager.authenticate(authToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        HttpSession session = request.getSession(true);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

        return "redirect:/";
    }

}
