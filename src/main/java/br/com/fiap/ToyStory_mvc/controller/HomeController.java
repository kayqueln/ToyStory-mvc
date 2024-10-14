package br.com.fiap.ToyStory_mvc.controller;


import br.com.fiap.ToyStory_mvc.model.Brinquedo;
import br.com.fiap.ToyStory_mvc.repository.UsuarioRepository;
import br.com.fiap.ToyStory_mvc.service.BrinquedoService;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private BrinquedoService brinquedoService;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @GetMapping
    public String home(Authentication authentication, Model model){
        List<Brinquedo> brinquedos = brinquedoService.listar();
        model.addAttribute("brinquedos", brinquedos);

        if(authentication != null ){
            String nome = usuarioRepository.findByEmail(authentication.getName()).getNome();
            model.addAttribute("authentication", nome);

            if(authentication.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"))){
                model.addAttribute("authenticationAdmin", authentication);
            }else{
                model.addAttribute("authenticationUser", authentication);
            }
        }

        return "home";
    }

    @GetMapping("/contato")
    public String paginaContato(Authentication authentication, Model model){
        if(authentication != null ){
            String nome = usuarioRepository.findByEmail(authentication.getName()).getNome();
            model.addAttribute("authentication", nome);

            if(authentication.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"))){
                model.addAttribute("authenticationAdmin", authentication);
            }else{
                model.addAttribute("authenticationUser", authentication);
            }
        }
        return "contato";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
