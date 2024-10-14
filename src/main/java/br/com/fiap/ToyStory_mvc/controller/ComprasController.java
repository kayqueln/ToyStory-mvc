package br.com.fiap.ToyStory_mvc.controller;

import br.com.fiap.ToyStory_mvc.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/compras")
@Controller
public class ComprasController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public String compras(Model model, Authentication authentication){
        if(authentication != null ){
            String nome = usuarioRepository.findByEmail(authentication.getName()).getNome();
            model.addAttribute("authentication", nome);

            if(authentication.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"))){
                model.addAttribute("authenticationAdmin", authentication);
            }else{
                model.addAttribute("authenticationUser", authentication);
            }
        }
        return "compras/listar-compras";
    }
}
